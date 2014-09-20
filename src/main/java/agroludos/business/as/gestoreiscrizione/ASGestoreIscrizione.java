package agroludos.business.as.gestoreiscrizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.to.IscrizioneTO;

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
	public boolean eliminaIscrizione(IscrizioneTO iscto)
			throws DatabaseException {
		IscrizioneDAO iscDAO = getIscrizioneDAO();

		return iscDAO.annullaIscrizione(iscto);
	}

	@Override
	public List<IscrizioneTO> getAllIscrizione() throws DatabaseException {
		IscrizioneDAO daoMan = getIscrizioneDAO(); 
		return daoMan.getAll();
	}
}