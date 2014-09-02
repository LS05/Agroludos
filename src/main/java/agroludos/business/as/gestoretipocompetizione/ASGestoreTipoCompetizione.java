package agroludos.business.as.gestoretipocompetizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.TipoCompetizioneDAO;
import agroludos.to.TipoCompetizioneTO;

class ASGestoreTipoCompetizione extends AgroludosAS implements LTipoCompetizione, STipoCompetizione{

	@Override
	public boolean inserisciTipoCompetizione(TipoCompetizioneTO tcmto)
			throws DatabaseException {
		boolean res = false;

		TipoCompetizioneDAO daoTcm = getTipoCompetizioneDAO();
		res = daoTcm.crea(tcmto);

		return res;
	}

	private TipoCompetizioneDAO getTipoCompetizioneDAO() {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoCompetizioneDAO();
	}

	@Override
	public List<TipoCompetizioneTO> getAllTipoCompetizioneTOs()
			throws DatabaseException {
		TipoCompetizioneDAO daoTcm = getTipoCompetizioneDAO();
		return daoTcm.readAll();
	}


}