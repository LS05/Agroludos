package agroludos.business.as.gestorestatooptional;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.StatoOptionalDAO;
import agroludos.to.StatoOptionalTO;

public class ASGestoreStatoOptional extends AgroludosAS implements LStatoOptional,SStatoOptional{

	@Override
	public List<StatoOptionalTO> getAllStatoOptional() throws DatabaseException {
		StatoOptionalDAO daoTcm = getStatoOptionalDAO();
		return daoTcm.getAll();
	}

	private StatoOptionalDAO getStatoOptionalDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getStatoOptionalDAO();
	}

}
