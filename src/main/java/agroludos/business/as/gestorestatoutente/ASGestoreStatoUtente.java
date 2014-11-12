package agroludos.business.as.gestorestatoutente;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.StatoUtenteDAO;
import agroludos.to.StatoUtenteTO;

public class ASGestoreStatoUtente extends AgroludosAS implements LStatoUtente,
		SStatoUtente {

	@Override
	public List<StatoUtenteTO> getAllStatoUtente() throws DatabaseException {
		StatoUtenteDAO daoTcm = getStatoUtenteDAO();
		return daoTcm.getAll();
	}

	private StatoUtenteDAO getStatoUtenteDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getStatoUtenteDAO();
	}

}
