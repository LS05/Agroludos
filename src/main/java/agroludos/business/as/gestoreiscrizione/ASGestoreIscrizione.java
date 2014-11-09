package agroludos.business.as.gestoreiscrizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.ChiuseIscrizioniException;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.IscrizioneEsistenteException;
import agroludos.exceptions.NmaxRaggiuntoException;
import agroludos.exceptions.ValidationException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.integration.dao.db.StatoIscrizioneDAO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.StatoIscrizioneTO;

class ASGestoreIscrizione extends AgroludosAS implements LIscrizione, SIscrizione{

	private AgroludosValidator validator;

	ASGestoreIscrizione(AgroludosValidator validator){
		this.validator = validator;
	}

	private DBDAOFactory getDBDaoFactory() throws DatabaseException{
		return this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
	}

	private IscrizioneDAO getIscrizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.getDBDaoFactory();
		return dbDAOFact.getIscrizioneDAO();
	}

	private StatoIscrizioneDAO getStatoIscrizioneDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.getDBDaoFactory();
		return dbDAOFact.getStatoIscrizioneDAO();
	}

	private CompetizioneDAO getCompetizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.getDBDaoFactory();
		return dbDAOFact.getCompetizioneDAO();
	}

	@Override
	public IscrizioneTO inserisciIscrizione(IscrizioneTO iscTO)
			throws DatabaseException, ValidationException {

		IscrizioneDAO iscDAO = getIscrizioneDAO();
		CompetizioneDAO cmpDAO = getCompetizioneDAO();

		CompetizioneTO cmp = cmpDAO.readById(iscTO.getCompetizione().getId());	

		//pseudocodice
		//getCompetizioneById() dal dao
		// 1.competizione aperta alle iscrizioni 
		//private boolean isCompetizioneAperta(CompetizioneTO cmp) throw new ChiuseIscrizioniException();
		// 2.iscrizione esistente 
		//private boolean iscrizioneEsistente(IscrizioneTO isc) throw new IscrizioneEsistenteException();
		// 3.numero massimo raggiunto
		//private boolean numeroMaxRaggiunto(CompetizioneTO cmp) throw new NumeroMaxRaggiuntoException();
		//validator ?
		//setStatoIscrizione a 1
		//crea
		if(isCompetizioneAperta(cmp)){
			if(!iscrizioneEsistente(iscTO)){
				if(!numeroMaxRaggiunto(cmp)){
					this.validator.validate(iscTO);
					
					StatoIscrizioneDAO statoIscDAO = getStatoIscrizioneDAO();

					List<StatoIscrizioneTO> listSI = statoIscDAO.getAll();

					iscTO.setStatoIscrizione(listSI.get(1));

					iscTO = iscDAO.create(iscTO);
				}
			}
		}
		return iscTO;
	}

	@Override
	public IscrizioneTO modificaIscrizione(IscrizioneTO iscTO)
			throws DatabaseException {
		IscrizioneDAO iscDAO = getIscrizioneDAO();
		return iscDAO.update(iscTO);
	}

	@Override
	public IscrizioneTO eliminaIscrizione(IscrizioneTO iscTO)
			throws DatabaseException {

		IscrizioneDAO iscDAO = getIscrizioneDAO();

		StatoIscrizioneDAO statoIscDAO = getStatoIscrizioneDAO();
		StatoIscrizioneTO siTO = statoIscDAO.getAll().get(0);

		iscTO.setStatoIscrizione(siTO);

		return iscDAO.annullaIscrizione(iscTO);
	}

	@Override
	public List<IscrizioneTO> getAllIscrizione() throws DatabaseException {
		IscrizioneDAO daoMan = getIscrizioneDAO(); 
		return daoMan.getAll();
	}

	@Override
	public List<IscrizioneTO> getAllIscrizioniAttive(PartecipanteTO parTO) throws DatabaseException {
		IscrizioneDAO daoMan = getIscrizioneDAO(); 
		return daoMan.getAllIscrizioniAttive(parTO);
	}

	private boolean isCompetizioneAperta(CompetizioneTO cmp) throws ChiuseIscrizioniException {
		boolean res = true;
		if(cmp.getStatoCompetizione().getId()!=1)
			throw new ChiuseIscrizioniException();

		return res;
	}

	private boolean iscrizioneEsistente(IscrizioneTO isc) throws DatabaseException, IscrizioneEsistenteException {
		boolean res = false;
		List<IscrizioneTO> listIscCmp = getIscrizioneDAO().getIscrizioniAttiveCmp(isc.getCompetizione());
		
		for(IscrizioneTO tempIsc: listIscCmp){
			if(tempIsc.getPartecipante().getId()==isc.getPartecipante().getId())
				throw new IscrizioneEsistenteException();
		}
		return res;
	}
	
	private boolean numeroMaxRaggiunto(CompetizioneTO cmp) throws DatabaseException, NmaxRaggiuntoException {
		boolean res = false;
		List<IscrizioneTO> listIscCmp = getIscrizioneDAO().getIscrizioniAttiveCmp(cmp);
		
		if(listIscCmp.size()==cmp.getNmax())
			throw new NmaxRaggiuntoException();

		return res;
	}

	@Override
	public List<IscrizioneTO> getAllIscrizioniAttiveByCmp(CompetizioneTO cmpTO)
			throws DatabaseException {
		
		List<IscrizioneTO> listIscCmp = getIscrizioneDAO().getIscrizioniAttiveCmp(cmpTO);
		return listIscCmp;
	}

	@Override
	public List<IscrizioneTO> getAllIscrizioniPartecipante(PartecipanteTO parTO)
			throws DatabaseException {
		List<IscrizioneTO> listIscCmp = getIscrizioneDAO().getAllIscrizioniPartecipante(parTO);
		return listIscCmp;
	}
}