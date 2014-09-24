package agroludos.business.as.gestoretipocompetizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.TipoCompetizioneDAO;
import agroludos.to.TipoCompetizioneTO;

class ASGestoreTipoCompetizione extends AgroludosAS implements LTipoCompetizione, STipoCompetizione{
	
	private TipoCompetizioneDAO getTipoCompetizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoCompetizioneDAO();
	}

	@Override
	public boolean inserisciTipoCompetizione(TipoCompetizioneTO tcmto)
			throws DatabaseException {
		boolean res = false;

		TipoCompetizioneDAO daoTcm = getTipoCompetizioneDAO();
		res = daoTcm.create(tcmto);

		return res;
	}

	@Override
	public List<TipoCompetizioneTO> getAllTipoCompetizione()
			throws DatabaseException {
		TipoCompetizioneDAO daoTcm = getTipoCompetizioneDAO();
		return daoTcm.getAll();
	}
}