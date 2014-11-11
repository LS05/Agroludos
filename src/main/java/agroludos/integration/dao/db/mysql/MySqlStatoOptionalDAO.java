package agroludos.integration.dao.db.mysql;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.StatoOptionalDAO;
import agroludos.to.StatoOptionalTO;

public class MySqlStatoOptionalDAO extends MySqlAgroludosDAO<StatoOptionalTO> implements StatoOptionalDAO {

	public MySqlStatoOptionalDAO() {
		this.setClasse(toFact.createStatoOptionalTO());
	}

	@Override
	public StatoOptionalTO getStatoDisattivo() throws DatabaseException {
		return this.findOne(0);
	}

	@Override
	public StatoOptionalTO getStatoAttivo() throws DatabaseException {
		return this.findOne(0);
	}

}
