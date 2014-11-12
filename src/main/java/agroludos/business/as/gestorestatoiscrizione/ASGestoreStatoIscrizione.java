package agroludos.business.as.gestorestatoiscrizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.StatoIscrizioneDAO;
import agroludos.to.StatoIscrizioneTO;

public class ASGestoreStatoIscrizione extends AgroludosAS implements LStatoIscrizione,SStatoIscrizione{

	@Override
	public List<StatoIscrizioneTO> getAllStatoIscrizione() throws DatabaseException {
		StatoIscrizioneDAO daoTcm = getStatoIscrizioneDAO();
		return daoTcm.getAll();
	}

	private StatoIscrizioneDAO getStatoIscrizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getStatoIscrizioneDAO();
	}

	@Override
	public StatoIscrizioneTO getStatoAttivo() throws DatabaseException {
		StatoIscrizioneDAO iscDao = getStatoIscrizioneDAO();
		return iscDao.getStatoAttivo();
	}

}