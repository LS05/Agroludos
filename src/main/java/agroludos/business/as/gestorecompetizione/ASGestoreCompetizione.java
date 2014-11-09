package agroludos.business.as.gestorecompetizione;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.CmpDataAttiveException;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.ValidationException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.integration.dao.db.StatoCompetizioneDAO;
import agroludos.integration.dao.db.StatoIscrizioneDAO;
import agroludos.to.CompetizioneTO;
import agroludos.to.EmailTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.StatoCompetizioneTO;
import agroludos.to.TipoCompetizioneTO;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore di Competizione.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare il funzionamento
 * dei servizi andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * Il gestore utilizza una serie di {@link AgroludosTO} (Transfer Object o Data Transfer Object
 * la cui tipologia dipende dal servizio) e sfrutta il {@link CompetizioneDAO} 
 * (Data Access Object) per occuparsi della persistenza di tali oggetti.</br>
 * Il Gestore della Competizione espone la logica di business riguardante le competizioni 
 * tramite due interfacce. L'interfaccia {@link LCompetizione} fornisce i servizi 
 * di lettura, mentre l'interfaccia {@link SCompetizione} offre i servizi di scrittura.</br>
 * La classe espone i seguenti servizi:</br>
 * <b><i>Lettura</i></b></br>
 * <ul>
 * <li>{@link LChiusura.getCompetizioneByMdc}</li>
 * <li>{@link LChiusura.getCompetizioniByTipo}</li>
 * <li>{@link LChiusura.getCompetizioneAllCompetizione}</li>
 * <li>{@link LChiusura.getCompetizioneById}</li>
 * <li>{@link LChiusura.getCompetizioniAttive}</li>
 * </ul>
 * <b><i>Scrittura</i></b></br>
 * <ul>
 * <li>{@link SCompetizione.inserisciCompetizione}</li>
 * <li>{@link LChiusura.modificaCompetizione}</li>
 * <li>{@link LChiusura.annullaCompetizione}</li>
 * </ul></br>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */

class ASGestoreCompetizione extends AgroludosAS implements LCompetizione, SCompetizione{

	private AgroludosValidator validator;

	ASGestoreCompetizione(AgroludosValidator validator){
		this.validator = validator;
	}


	private DBDAOFactory getDBDaoFactory() throws DatabaseException{
		return this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
	}

	private CompetizioneDAO getCompetizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.getDBDaoFactory();
		return dbDAOFact.getCompetizioneDAO();
	}

	private StatoCompetizioneDAO getStatoCompetizioneDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.getDBDaoFactory();
		return dbDAOFact.getStatoCompetizioneDAO();
	}

	private IscrizioneDAO getIscrizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.getDBDaoFactory();
		return dbDAOFact.getIscrizioneDAO();
	}

	@Override
	public CompetizioneTO inserisciCompetizione(CompetizioneTO cmpto)
			throws DatabaseException, ValidationException {

		CompetizioneDAO daoCmp = getCompetizioneDAO();
		StatoCompetizioneDAO daoStatoCmp = getStatoCompetizioneDAO();
		cmpto.setStatoCompetizione(daoStatoCmp.getStatoCmpAperta());

		this.validator.validate(cmpto);

		this.checkCmpData(cmpto);

		cmpto = daoCmp.create(checkCmp(cmpto));

		return checkCmp(cmpto);

	}

	@Override
	public CompetizioneTO modificaCompetizione(CompetizioneTO cmpto)
			throws DatabaseException, ValidationException {

		CompetizioneDAO daoCmp = getCompetizioneDAO();

		this.validator.validate(cmpto);
		this.checkCmpData(cmpto);

		List<IscrizioneTO> listIscAttive = getIscrizioneDAO().getIscrizioniAttiveCmp(cmpto);
		if(listIscAttive.size() > cmpto.getNmax())
			eliminaIscrizioniInEsubero(listIscAttive);
		cmpto = daoCmp.update(checkCmp(cmpto));

		return checkCmp(cmpto);
	}

	private List<IscrizioneTO> eliminaIscrizioniInEsubero(
			List<IscrizioneTO> listIsc) throws DatabaseException {
		CompetizioneTO cmp = listIsc.get(0).getCompetizione();

		//TODO testare 

		//ordinare la lista per data
		int lenght = listIsc.size()-1;
		while(lenght > 0){
			for(int i = 0; i < lenght; i++){
				if(listIsc.get(i).getData().after(listIsc.get(i+1).getData())){
					IscrizioneTO isc1 = listIsc.get(i);
					IscrizioneTO isc2 = listIsc.get(i+1);
					listIsc.set(i, isc2);
					listIsc.set(i+1, isc1);
				}
				lenght = lenght - 1;
			}	
		}

		StatoIscrizioneDAO statoIscDao = this.dbFact.getDAOFactory(this.sysConf.getTipoDB()).getStatoIscrizioneDAO();
		IscrizioneDAO iscDao = getIscrizioneDAO();

		//elimino gli ultimi registrati e li avviso via mail
		while(listIsc.size() > cmp.getNmax()){
			IscrizioneTO iscTO = listIsc.get(listIsc.size()-1);

			listIsc.remove(listIsc.size()-1);

			iscTO.setStatoIscrizione(statoIscDao.getAll().get(0));
			iscDao.annullaIscrizione(iscTO);

			//TODO GestoreCompetizione - Invio mail 
			EmailTO mail = toFact.createEmailTO();
			mail.setOggetto("Iscrizione annullata");
			mail.setMessage(iscTO.getPartecipante().getUsername() + " abbiamo annullato l'iscrizione "
					+ "alla competizione " + iscTO.getCompetizione().getNome()
					+ " in quanto il numero massimo di partecipanti si è ridotto"
					+ " e stiamo annullando le iscrizioni a partire dall'ultimo iscritto"
					+ " fino al raggiungimento del numero massimo.");

			mail.addDestinatario(iscTO.getPartecipante());
		}
		return listIsc;
	}

	@Override
	public CompetizioneTO annullaCompetizione(CompetizioneTO cmpto)
			throws DatabaseException {

		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		CompetizioneDAO daoCmp = dbDAOFact.getCompetizioneDAO();
		StatoCompetizioneDAO daoScmp = dbDAOFact.getStatoCompetizioneDAO();
		cmpto.setStatoCompetizione(daoScmp.getStatoCmpAnnullata());
		daoCmp.annullaCompetizione(cmpto);
		IscrizioneDAO iscDao = getIscrizioneDAO();
		

		//TODO invia mail
		EmailTO mail = toFact.createEmailTO();
		mail.setOggetto(cmpto.getNome() + " annullata.");
		mail.setMittente(cmpto.getManagerDiCompetizione());
		mail.setMessage("La competizione " + cmpto.getNome() + "  è stata annullata.");

		List<IscrizioneTO> listIsc = iscDao.getIscrizioniAttiveCmp(cmpto);
		for(IscrizioneTO iscTO: listIsc){
			mail.addDestinatario(iscTO.getPartecipante());
		}

		
		iscDao.terminaIscrizioni(cmpto);
		
		return cmpto;
	}

	@Override
	public List<CompetizioneTO> getCompetizioneByMdc(ManagerDiCompetizioneTO mdcto)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return checkCmp(daoCmp.readByMdc(mdcto));
	}

	@Override
	public List<CompetizioneTO> getCompetizioneAttiveByMdc(ManagerDiCompetizioneTO mdcto)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		checkCmp(daoCmp.readAttiveByMdc(mdcto));
		return daoCmp.readAttiveByMdc(mdcto);
	}

	@Override
	public List<CompetizioneTO> getCompetizioniByTipo(TipoCompetizioneTO tcmto)
			throws DatabaseException {
		
		List<CompetizioneTO> res = getCompetizioneDAO().getCompetizioniByTipo(tcmto);

		return checkCmp(res);
	}

	@Override
	public List<CompetizioneTO> getAllCompetizione() throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return checkCmp(daoCmp.getAll());
	}

	@Override
	public CompetizioneTO getCompetizioneById(CompetizioneTO cmpto)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();

		return checkCmp(daoCmp.readById(cmpto.getId()));
	}

	@Override
	public List<CompetizioneTO> getCompetizioniAperte()
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		List<CompetizioneTO> listCmpAperte = daoCmp.readCompetizioniAperte();
		checkCmp(listCmpAperte);

		return daoCmp.readCompetizioniAperte();
	}

	@Override
	public List<CompetizioneTO> getCompetizioniAttive()
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		List<CompetizioneTO> listCmpAttive = daoCmp.readCompetizioniAttive();
		checkCmp(listCmpAttive);

		return daoCmp.readCompetizioniAttive();
	}

	private List<CompetizioneTO> checkCmp(List<CompetizioneTO> listCmp) throws DatabaseException{

		StatoCompetizioneDAO daoStatoCmp = this.getStatoCompetizioneDAO();

		DateTime today = new DateTime();
		today = DateTime.now();
		CompetizioneDAO cmpDao = getCompetizioneDAO();

		for(CompetizioneTO cmp: listCmp){
			if(!(cmp.getStatoCompetizione().getId() == 0)
					|| !(cmp.getStatoCompetizione().getId() == 4)){
				DateTime dataCmp = new DateTime(cmp.getData());
				if(dataCmp.isEqualNow() 
						&& !(cmp.getStatoCompetizione().getId() == 2)){
					cmp.setStatoCompetizione(daoStatoCmp.getStatoCmpInCorso());
					cmpDao.update(cmp);
				}
				else if(dataCmp.isAfter(today) && dataCmp.isBefore(today.plusDays(2))
						&& !(cmp.getStatoCompetizione().getId() == 3)){
					cmp.setStatoCompetizione(daoStatoCmp.getStatoCmpChiusa());
					cmpDao.update(cmp);
				}
				else if (today.isAfter(dataCmp)
						&& !(cmp.getStatoCompetizione().getId() == 4)){
					cmp.setStatoCompetizione(daoStatoCmp.getStatoCmpTerminata());
					IscrizioneDAO iscDao = getIscrizioneDAO();
					iscDao.terminaIscrizioni(cmp);
					cmpDao.update(cmp);
				}
			}
		}
		return listCmp;
	}

	private CompetizioneTO checkCmp(CompetizioneTO Cmp) throws DatabaseException{

		List<CompetizioneTO> listCmp = new ArrayList<CompetizioneTO>();
		listCmp.add(Cmp);
		return checkCmp(listCmp).get(0);
	}

	private CompetizioneTO checkCmpData(CompetizioneTO cmp) 
			throws DatabaseException, CmpDataAttiveException{

		CompetizioneDAO daoCmp = getCompetizioneDAO();

		//controllo se esiste una competizione attiva nella data inserita
		List<CompetizioneTO> cmpList = daoCmp.readCompetizioniAttive();
		for(CompetizioneTO compet : cmpList){
			if(compet.getId()!=cmp.getId())
				if(compet.getData().compareTo(cmp.getData()) == 0)
					throw new CmpDataAttiveException();
		}

		return cmp;
	}



	public StatoCompetizioneTO getStatoCmp(CompetizioneTO cmp)
			throws DatabaseException {
		return getCompetizioneById(cmp).getStatoCompetizione();
	}
}