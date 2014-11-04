package agroludos.business.as.gestoreiscrizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.integration.dao.db.StatoIscrizioneDAO;
import agroludos.to.IscrizioneTO;
import agroludos.to.StatoIscrizioneTO;

class ASGestoreIscrizione extends AgroludosAS implements LIscrizione, SIscrizione{

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
			throws DatabaseException {

		IscrizioneDAO iscDAO = getIscrizioneDAO();

		StatoIscrizioneDAO statoIscDAO = getStatoIscrizioneDAO();
		
		//TODO se il certificato è scaduto,
		//non fa l'iscrizione
		//TODO partecipante già iscritto
		//TODO usare un validator??????
		StatoIscrizioneTO siTO = statoIscDAO.getAll().get(1);

		iscTO.setStatoIscrizione(siTO);
		
		for(IscrizioneTO checkIsc: iscTO.getCompetizione().getAllIscrizioniAttive()){
			if(checkIsc.equals(iscTO)){
				//TODO solleva eccezione utente già iscritto
			}
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