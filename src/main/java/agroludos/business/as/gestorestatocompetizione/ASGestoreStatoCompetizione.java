package agroludos.business.as.gestorestatocompetizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.StatoCompetizioneDAO;
import agroludos.to.StatoCompetizioneTO;

public class ASGestoreStatoCompetizione extends AgroludosAS implements LStatoCompetizione, SStatoCompetizione{

	@Override
	public List<StatoCompetizioneTO> getAllStatoCompetizione()
			throws DatabaseException {
		StatoCompetizioneDAO daoTcm = getStatoCompetizioneDAO();
		return daoTcm.getAll();
	}

	private StatoCompetizioneDAO getStatoCompetizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getStatoCompetizioneDAO();
	}

	@Override
	public StatoCompetizioneTO getStatoCmpAnnullata() throws DatabaseException {
		StatoCompetizioneDAO daoTcm = getStatoCompetizioneDAO();
		return daoTcm.getStatoCmpAnnullata();
	}

}
