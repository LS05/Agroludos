package agroludos.business.as.gestoreiscrizione;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.to.IscrizioneTO;

class ASGestoreIscrizione extends AgroludosAS implements LIscrizione, SIscrizione{
	
	private IscrizioneDAO getIscrizioneDAO() {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getIscrizioneDAO();
	}
	
	@Override
	public boolean inserisciIscrizione(IscrizioneTO iscto)
			throws DatabaseException {
		boolean res = false;

		IscrizioneDAO iscDAO = getIscrizioneDAO();
		res = iscDAO.crea(iscto);

		return res;
	}

	@Override
	public boolean modificaIscrizione(IscrizioneTO iscto)
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


}
