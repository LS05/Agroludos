package agroludos.business.as.gestoreiscrizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.CSRSScadutoException;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.IscrizioneEsistenteException;
import agroludos.exceptions.NmaxRaggiuntoException;
import agroludos.exceptions.ValidationException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.integration.dao.db.StatoIscrizioneDAO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
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

	@Override
	public IscrizioneTO inserisciIscrizione(IscrizioneTO iscTO)
			throws DatabaseException, ValidationException {

		IscrizioneDAO iscDAO = getIscrizioneDAO();
		CompetizioneTO cmp = iscTO.getCompetizione();	
		
		boolean checkPart=false;
		
		if(cmp.getAllIscrizioniAttive().size() <= cmp.getNmax()){
			for(IscrizioneTO isc : iscTO.getCompetizione().getAllIscrizioniAttive()){
				if(isc.getPartecipante().getId() == iscTO.getPartecipante().getId())
					checkPart = true;
			}
			if(checkPart){
				throw new IscrizioneEsistenteException();
			}else if(iscTO.getPartecipante().getDataSRC().after(iscTO.getCompetizione().getData())){
				this.validator.validate(iscTO);

				StatoIscrizioneDAO statoIscDAO = getStatoIscrizioneDAO();

				List<StatoIscrizioneTO> listSI = statoIscDAO.getAll();

				iscTO.setStatoIscrizione(listSI.get(1));
				
				iscTO = iscDAO.create(iscTO);
			}else{
				throw new CSRSScadutoException();
			}
		}else
		{
			cmp.setStatoCompetizione(this.getDBDaoFactory().getStatoCompetizioneDAO().getStatoCmpChiusa());
			throw new NmaxRaggiuntoException();
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

}