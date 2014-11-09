package agroludos.integration.dao.db.mysql;

import agroludos.integration.dao.db.StatoOptionalDAO;
import agroludos.to.StatoOptionalTO;

public class MySqlStatoOptionalDAO extends MySqlAgroludosDAO<StatoOptionalTO> implements StatoOptionalDAO {

	public MySqlStatoOptionalDAO() {
		this.setClasse(toFact.createStatoOptionalTO());
	}

}
