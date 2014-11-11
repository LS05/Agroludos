package agroludos.business.as.gestorecompetizione;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.CmpDataAttiveException;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.UnModCompetizioneException;
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
import agroludos.utility.email.AgroludosMail;

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
 * <li>{@link LCompetizione.getCompetizioneByMdc}</li>
 * <li>{@link LCompetizione.getCompetizioniByTipo}</li>
 * <li>{@link LCompetizione.getCompetizioneAllCompetizione}</li>
 * <li>{@link LCompetizione.getCompetizioneById}</li>
 * <li>{@link LCompetizione.getCompetizioniAttive}</li> 
 * <li>{@link LCompetizione.getCompetizioniAperte}</li>
 * <li>{@link LCompetizione.getCompetizioneAttiveByMdc}</li>
 * </ul>
 * <b><i>Scrittura</i></b></br>
 * <ul>
 * <li>{@link SCompetizione.inserisciCompetizione}</li>
 * <li>{@link SCompetizione.modificaCompetizione}</li>
 * <li>{@link SCompetizione.annullaCompetizione}</li>
 * </ul></br>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */

class ASGestoreCompetizione extends AgroludosAS implements LCompetizione, SCompetizione{

	private AgroludosValidator validator;
	private AgroludosMail agroludosMail;

	ASGestoreCompetizione(AgroludosValidator validator, AgroludosMail agroludosMail){
		this.validator = validator;
		this.agroludosMail = agroludosMail;
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

	private StatoIscrizioneDAO getStatoIscrizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.getDBDaoFactory();
		return dbDAOFact.getStatoIscrizioneDAO();
	}

	@Override
	public CompetizioneTO inserisciCompetizione(CompetizioneTO cmpTO)
			throws DatabaseException, ValidationException {

		CompetizioneDAO daoCmp = getCompetizioneDAO();
		StatoCompetizioneDAO daoStatoCmp = getStatoCompetizioneDAO();
		cmpTO.setStatoCompetizione(daoStatoCmp.getStatoCmpAperta());

		this.validator.validate(cmpTO);

		this.checkCmpData(cmpTO);

		cmpTO = daoCmp.create(cmpTO);

		return cmpTO;
	}

	@Override
	public CompetizioneTO modificaCompetizione(CompetizioneTO cmpTO)
			throws DatabaseException, ValidationException {

		CompetizioneDAO daoCmp = getCompetizioneDAO();
		//se la competizione non è aperta alle iscrizioni
		//non è possibile effettuare modifiche
		//TODO Eliminare il confronto sugli id
		if(getStatoCmp(cmpTO).getId()==getStatoCompetizioneDAO().getStatoCmpAperta().getId()){

			this.validator.validate(cmpTO);

			this.checkCmpData(cmpTO);

			List<IscrizioneTO> listIscAttive = getIscrizioneDAO().getIscrizioniAttiveCmp(cmpTO);

			cmpTO = daoCmp.update(cmpTO);

			if(listIscAttive.size() > cmpTO.getNmax())
				eliminaIscrizioniInEsubero(listIscAttive, cmpTO);

		} else {
			throw new UnModCompetizioneException();
		}

		return cmpTO;
	}

	private List<IscrizioneTO> eliminaIscrizioniInEsubero(List<IscrizioneTO> listIsc, CompetizioneTO cmpTO) 
			throws DatabaseException {

		//TODO Effettuare una deep copy
		List<IscrizioneTO> listIscTemp = new ArrayList<IscrizioneTO>(listIsc);

		//ordinare la lista per data
		int lenght = listIsc.size() - 1;
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

		StatoIscrizioneDAO statoIscDao = this.getStatoIscrizioneDAO();
		IscrizioneDAO iscDao = getIscrizioneDAO();

		//elimino gli ultimi registrati e li avviso via mail
		while(listIsc.size() > cmpTO.getNmax()){
			IscrizioneTO iscTO = listIsc.get(listIsc.size()-1);

			listIsc.remove(listIsc.size()-1);

			iscTO.setStatoIscrizione(statoIscDao.getStatoDisattivo());
			iscDao.annullaIscrizione(iscTO);

			EmailTO mail = this.toFact.createEmailTO();

			String mailSubj = this.sysConf.getString("mailAnnullaIscrSubj");
			mail.setOggetto(mailSubj);

			String mailMsg = this.sysConf.getString("mailAnnullaIscrEsubMsg");
			String realMsg = MessageFormat.format(mailMsg, 
					iscTO.getPartecipante().getUsername(), 
					iscTO.getCompetizione().getNome());
			mail.setMessage(realMsg);

			mail.addDestinatario(iscTO.getPartecipante());

			this.agroludosMail.sendEmail(mail);
		}

		return listIsc;
	}

	@Override
	public CompetizioneTO annullaCompetizione(CompetizioneTO cmpTO)
			throws DatabaseException {

		CompetizioneDAO daoCmp = this.getCompetizioneDAO();
		StatoCompetizioneDAO daoScmp = this.getStatoCompetizioneDAO();
		IscrizioneDAO iscDao = this.getIscrizioneDAO();

		cmpTO.setStatoCompetizione(daoScmp.getStatoCmpAnnullata());
		daoCmp.annullaCompetizione(cmpTO);


		EmailTO mail = toFact.createEmailTO();

		String mailSubj = this.sysConf.getString("mailAnnullaCompSubj");
		mail.setOggetto(mailSubj);

		String mailMsg = this.sysConf.getString("mailAnnullaCompMsg");
		String realMsg = MessageFormat.format(mailMsg, cmpTO.getNome());
		mail.setMessage(realMsg);

		List<IscrizioneTO> listIsc = iscDao.getIscrizioniAttiveCmp(cmpTO);
		for(IscrizioneTO iscTO: listIsc){
			mail.addDestinatario(iscTO.getPartecipante());
		}

		this.agroludosMail.sendEmail(mail);

		iscDao.terminaIscrizioni(cmpTO);

		return cmpTO;
	}

	@Override
	public List<CompetizioneTO> getCompetizioneByMdc(ManagerDiCompetizioneTO mdcto)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		List<CompetizioneTO> listCmp = daoCmp.readByMdc(mdcto);
		setNiscritti(listCmp);
		return checkCmp(listCmp);
	}

	@Override
	public List<CompetizioneTO> getCompetizioneAttiveByMdc(ManagerDiCompetizioneTO mdcto)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		List<CompetizioneTO> listCmp = daoCmp.readAttiveByMdc(mdcto);
		setNiscritti(listCmp);
		return checkCmp(listCmp);
	}

	@Override
	public List<CompetizioneTO> getCompetizioniByTipo(TipoCompetizioneTO tcmto)
			throws DatabaseException {

		List<CompetizioneTO> listCmp = getCompetizioneDAO().getCompetizioniByTipo(tcmto);
		setNiscritti(listCmp);
		return checkCmp(listCmp);
	}

	@Override
	public List<CompetizioneTO> getAllCompetizione() throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		List<CompetizioneTO> listCmp = daoCmp.getAll();
		setNiscritti(listCmp);
		return checkCmp(listCmp);
	}

	@Override
	public CompetizioneTO getCompetizioneById(CompetizioneTO cmpTO)
			throws DatabaseException {

		CompetizioneDAO daoCmp = getCompetizioneDAO();
		CompetizioneTO cmp = daoCmp.readById(cmpTO.getId());
		setNiscritti(cmp);
		return checkCmp(cmp);
	}

	@Override
	public List<CompetizioneTO> getCompetizioniAperte()
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		List<CompetizioneTO> listCmpAperte = daoCmp.readCompetizioniAperte();
		setNiscritti(listCmpAperte);
		checkCmp(listCmpAperte);

		return listCmpAperte;
	}

	@Override
	public List<CompetizioneTO> getCompetizioniAttive()
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		List<CompetizioneTO> listCmpAttive = daoCmp.readCompetizioniAttive();
		setNiscritti(listCmpAttive);
		checkCmp(listCmpAttive);

		return listCmpAttive;
	}

	private void setNiscritti(List<CompetizioneTO> listCmp) throws DatabaseException {
		for(CompetizioneTO cmp: listCmp)
			setNiscritti(cmp);
	}
	private void setNiscritti(CompetizioneTO cmp) throws DatabaseException {
		IscrizioneDAO iscDao = getIscrizioneDAO();
		cmp.setNiscritti(iscDao.getIscrizioniAttiveCmp(cmp).size());
	}
	
	private List<CompetizioneTO> checkCmp(List<CompetizioneTO> listCmp) throws DatabaseException{
		for(CompetizioneTO cmp: listCmp)
			checkCmp(cmp);
		return listCmp;
	}

	//controlla le competizioni cambiando il loro stato confrontando la data di oggi
	//con la data della competizione
	//se termina termina le iscrizioni attive
	//se viene annullata per il non raggiungimento del numero minimo annulla la comp
	private CompetizioneTO checkCmp(CompetizioneTO cmp) throws DatabaseException{

		StatoCompetizioneDAO daoStatoCmp = this.getStatoCompetizioneDAO();

		DateTime today = new DateTime();
		today = DateTime.now();
		CompetizioneDAO cmpDao = getCompetizioneDAO();

		if(!(cmp.getStatoCompetizione().getId() == 0)
				&& !(cmp.getStatoCompetizione().getId() == 4)){
			DateTime dataCmp = new DateTime(cmp.getData());
			if(dataCmp.isEqualNow() 
					&& !(cmp.getStatoCompetizione().getId() == 2)){
				//se non si è raggiunti il numero minimo di partecipanti la 
				//competizione viene annullata
				List<IscrizioneTO> listIscCmp = getIscrizioneDAO().getIscrizioniAttiveCmp(cmp);
				if(listIscCmp.size() < cmp.getNmin())
					annullaCompetizione(cmp);
				else{
					cmp.setStatoCompetizione(daoStatoCmp.getStatoCmpChiusa());
					cmpDao.update(cmp);
				}
			}
			else if(dataCmp.isAfter(today) && dataCmp.isBefore(today.plusDays(2))
					&& !(cmp.getStatoCompetizione().getId() == 3)){
				//se non si è raggiunti il numero minimo di partecipanti la 
				//competizione viene annullata
				List<IscrizioneTO> listIscCmp = getIscrizioneDAO().getIscrizioniAttiveCmp(cmp);
				if(listIscCmp.size() < cmp.getNmin())
					annullaCompetizione(cmp);
				else{
					cmp.setStatoCompetizione(daoStatoCmp.getStatoCmpChiusa());
					cmpDao.update(cmp);
				}
			}
			else if (today.isAfter(dataCmp)
					&& !(cmp.getStatoCompetizione().getId() == 4)){

				cmp.setStatoCompetizione(daoStatoCmp.getStatoCmpTerminata());
				getIscrizioneDAO().terminaIscrizioni(cmp);
				cmpDao.update(cmp);
			}
		}
		return cmp;
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