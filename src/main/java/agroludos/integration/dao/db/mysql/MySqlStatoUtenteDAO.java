package agroludos.integration.dao.db.mysql;

import agroludos.integration.dao.db.StatoUtenteDAO;
import agroludos.to.StatoUtenteTO;

public class MySqlStatoUtenteDAO extends MySqlAgroludosDAO<StatoUtenteTO> implements StatoUtenteDAO {

	public MySqlStatoUtenteDAO() {
		this.setClasse(StatoUtenteTO.class);
	}

}
