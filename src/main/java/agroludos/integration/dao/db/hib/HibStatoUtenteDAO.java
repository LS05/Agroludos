package agroludos.integration.dao.db.hib;

import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.StatoUtenteDAO;
import agroludos.to.StatoUtenteTO;

public class HibStatoUtenteDAO extends HibAgroludosDAO<StatoUtenteTO> implements StatoUtenteDAO {

	public HibStatoUtenteDAO() {
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