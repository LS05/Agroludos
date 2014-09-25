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

	private IscrizioneDAO getIscrizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getIscrizioneDAO();
	}

	@Override
	public boolean inserisciIscrizione(IscrizioneTO iscto)
			throws DatabaseException {
		boolean res = false;

		IscrizioneDAO iscDAO = getIscrizioneDAO();
		res = iscDAO.create(iscto);

		return res;
	}

	@Override
	public IscrizioneTO modificaIscrizione(IscrizioneTO iscto)
			throws DatabaseException {
		IscrizioneDAO iscDAO = getIscrizioneDAO();
		return iscDAO.update(iscto);
	}

	@Override
	public IscrizioneTO eliminaIscrizione(IscrizioneTO iscto)
			throws DatabaseException {
		

		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		IscrizioneDAO iscDAO = getIscrizioneDAO();
		StatoIscrizioneDAO daoScmp = dbDAOFact.getStatoIscrizioneDAO();
		StatoIscrizioneTO sito = daoScmp.getAll().get(0);
		iscto.setStatoIscrizione(sito);
		
		return iscDAO.annullaIscrizione(iscto);
	}

	@Override
	public List<IscrizioneTO> getAllIscrizione() throws DatabaseException {
		IscrizioneDAO daoMan = getIscrizioneDAO(); 
		return daoMan.getAll();
	}
}