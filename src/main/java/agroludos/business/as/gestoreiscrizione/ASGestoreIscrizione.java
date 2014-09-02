package agroludos.business.as.gestoreiscrizione;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.to.IscrizioneTO;

class ASGestoreIscrizione extends AgroludosAS implements LIscrizione, SIscrizione{

	@Override
	public boolean inserisciIscrizione(IscrizioneTO iscto)
			throws DatabaseException {
		boolean res = false;

		IscrizioneDAO daoIsc = getIscrizioneDAO();
		res = daoIsc.crea(iscto);

		return res;
	}

	private IscrizioneDAO getIscrizioneDAO() {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getIscrizioneDAO();
	}

	@Override
	public boolean modificaIscrizione(IscrizioneTO iscto)
			throws DatabaseException {
		IscrizioneDAO daoIsc = getIscrizioneDAO();
		return daoIsc.update(iscto);
	}

	@Override
	public boolean eliminaIscrizione(IscrizioneTO iscto)
			throws DatabaseException {
		IscrizioneDAO daoIsc = getIscrizioneDAO();
		return daoIsc.annullaIscrizione(iscto);
	}


}
