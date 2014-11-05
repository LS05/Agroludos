package agroludos.business.as.gestoreiscrizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.ValidationException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.integration.dao.db.StatoIscrizioneDAO;
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
		boolean checkPart=false;

		for(IscrizioneTO isc : iscTO.getCompetizione().getAllIscrizioniAttive()){
			if(isc.getPartecipante().getId() == iscTO.getPartecipante().getId())
				checkPart = true;
		}
		if(checkPart){
			//TODO eccezione partecipante già iscritto
		}else if(iscTO.getPartecipante().getDataSRC().after(iscTO.getCompetizione().getData())){
			this.validator.validate(iscTO);

			StatoIscrizioneDAO statoIscDAO = getStatoIscrizioneDAO();

			StatoIscrizioneTO siTO = statoIscDAO.getAll().get(1);
			List<StatoIscrizioneTO> listSI = statoIscDAO.getAll();
			
			iscTO.setStatoIscrizione(listSI.get(1));
		}else{
			//TODO eccezione la data di scadenza è antecedente l'inizio della competizione
		}
		return iscDAO.create(iscTO);
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