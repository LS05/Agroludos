package agroludos.integration.dao.db.mysql;

import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.StatoUtenteDAO;
import agroludos.to.StatoUtenteTO;

public class MySqlStatoUtenteDAO extends MySqlAgroludosDAO<StatoUtenteTO> implements StatoUtenteDAO {

	public MySqlStatoUtenteDAO() {
		this.setClasse(toFact.createStatoUtenteTO());
	}

	@Override
	public StatoUtenteTO getStatoAttivo() throws DatabaseException {
		return this.findOne(1);
	}

	@Override
	public StatoUtenteTO getStatoDisattivo() throws DatabaseException {
		return this.findOne(0);
	}

}