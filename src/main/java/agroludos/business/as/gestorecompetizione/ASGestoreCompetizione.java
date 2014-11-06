package agroludos.business.as.gestorecompetizione;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.CompetizioneDataExistException;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.ValidationException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.StatoCompetizioneDAO;
import agroludos.integration.dao.db.TipoCompetizioneDAO;
import agroludos.to.CompetizioneTO;
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
 * <li>{@link LCompetizione.getCompetizioneByMdc}</li>
 * <li>{@link LCompetizione.getCompetizioniByTipo}</li>
 * <li>{@link LCompetizione.getCompetizioneAllCompetizione}</li>
 * <li>{@link LCompetizione.getCompetizioneById}</li>
 * <li>{@link LCompetizione.getCompetizioniAttive}</li>
 * </ul>
 * <b><i>Scrittura</i></b></br>
 * <ul>
 * <li>{@link SCompetizione.inserisciCompetizione}</li>
 * <li>{@link LCompetizione.modificaCompetizione}</li>
 * <li>{@link LCompetizione.annullaCompetizione}</li>
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

	private CompetizioneDAO getCompetizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getCompetizioneDAO();
	}

	private TipoCompetizioneDAO getTipoCompetizioneDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoCompetizioneDAO();
	}

	private StatoCompetizioneDAO getStatoCompetizioneDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getStatoCompetizioneDAO();
	}

	@Override
	public CompetizioneTO inserisciCompetizione(CompetizioneTO cmpto)
			throws DatabaseException, ValidationException {

		boolean checkData = false;
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		StatoCompetizioneDAO daoStatoCmp = getStatoCompetizioneDAO();
		cmpto.setStatoCompetizione(daoStatoCmp.getStatoCmpAperta());

		this.validator.validate(cmpto);

		//controllo se esiste una competizione attiva nella data inserita
		List<CompetizioneTO> cmpList = daoCmp.readCompetizioniAttive();
		for(CompetizioneTO compet : cmpList){
			if(compet.getData().compareTo(cmpto.getData()) == 0)
				checkData = true;
		}
		if(!checkData){
			cmpto = daoCmp.create(checkCmp(cmpto));
		}else
			throw new CompetizioneDataExistException();

		return checkCmp(cmpto);

	}

	@Override
	public CompetizioneTO modificaCompetizione(CompetizioneTO cmpto)
			throws DatabaseException, ValidationException {
		boolean checkData = false;

		CompetizioneDAO daoCmp = getCompetizioneDAO();

		this.validator.validate(cmpto);

		//controllo se esiste una competizione attiva nella data inserita
		List<CompetizioneTO> cmpList = daoCmp.readCompetizioniAttive();
		for(CompetizioneTO compet : cmpList){
			if(!compet.equals(cmpto))
				if(compet.getData().compareTo(cmpto.getData()) == 0)
					checkData = true;
		}
		if(!checkData){
			cmpto = daoCmp.update(checkCmp(cmpto));
		}else
			throw new CompetizioneDataExistException();

		return checkCmp(cmpto);
	}

	@Override
	public CompetizioneTO annullaCompetizione(CompetizioneTO cmpto)
			throws DatabaseException {

		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		CompetizioneDAO daoCmp = dbDAOFact.getCompetizioneDAO();
		StatoCompetizioneDAO daoScmp = dbDAOFact.getStatoCompetizioneDAO();
		cmpto.setStatoCompetizione(daoScmp.getStatoCmpAnnullata());
		return daoCmp.annullaCompetizione(cmpto);
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
		return checkCmp(daoCmp.readAttiveByMdc(mdcto));
	}

	@Override
	public List<CompetizioneTO> getCompetizioniByTipo(TipoCompetizioneTO tcmto)
			throws DatabaseException {
		TipoCompetizioneDAO daoTipo = this.getTipoCompetizioneDAO();
		List<TipoCompetizioneTO> listaTipi = daoTipo.getAll();
		List<CompetizioneTO> res = null;

		for(TipoCompetizioneTO item : listaTipi){
			if(item.getNome().equals(tcmto.getNome())){
				res = item.getAllCompetizioni();
				break;
			}
		}

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
	public List<TipoCompetizioneTO> getAllTipoCompetizione()
			throws DatabaseException {
		TipoCompetizioneDAO daoTipo = this.getTipoCompetizioneDAO();
		return daoTipo.getAll();
	}

	@Override
	public List<CompetizioneTO> getCompetizioneByTipo(TipoCompetizioneTO tcmto)
			throws DatabaseException {
		TipoCompetizioneDAO tcDao = this.getTipoCompetizioneDAO();
		List<TipoCompetizioneTO> tipiComp = tcDao.getAll();
		List<CompetizioneTO> res = null;

		for(TipoCompetizioneTO tipo : tipiComp){
			if(tipo.getNome().equals(tcmto.getNome())){
				res = tipo.getAllCompetizioni();
				break;
			}
		}

		return checkCmp(res);
	}


	@Override
	public List<CompetizioneTO> getCompetizioniAperte()
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		List<CompetizioneTO> listCmpAperte = daoCmp.readCompetizioniAperte();


		return checkCmp(listCmpAperte);
	}

	@Override
	public List<CompetizioneTO> getCompetizioniAttive()
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		List<CompetizioneTO> listCmpAttive = daoCmp.readCompetizioniAttive();
		
		
		return checkCmp(listCmpAttive);
	}

	private List<CompetizioneTO> checkCmp(List<CompetizioneTO> listCmp) throws DatabaseException{

		StatoCompetizioneDAO daoStatoCmp = this.getStatoCompetizioneDAO();

		DateTime today = new DateTime();
		today = DateTime.now();

		for(CompetizioneTO cmp: listCmp){
			if(!(cmp.getStatoCompetizione().getId() == 0)){
				DateTime dataCmp = new DateTime(cmp.getData());
				if(dataCmp.isEqualNow())
					cmp.setStatoCompetizione(daoStatoCmp.getStatoCmpInCorso());
				else if(dataCmp.isAfter(today) && dataCmp.isBefore(today.plusDays(2)))
					cmp.setStatoCompetizione(daoStatoCmp.getStatoCmpChiusa());
				else if (today.isAfter(dataCmp))
					cmp.setStatoCompetizione(daoStatoCmp.getStatoCmpTerminata());
				else 
					cmp.setStatoCompetizione(daoStatoCmp.getStatoCmpAperta());
			}
		}
		return listCmp;
	}
	
	private CompetizioneTO checkCmp(CompetizioneTO Cmp) throws DatabaseException{

		List<CompetizioneTO> listCmp = new ArrayList<CompetizioneTO>();
		listCmp.add(Cmp);
		return checkCmp(listCmp).get(0);
	}

}