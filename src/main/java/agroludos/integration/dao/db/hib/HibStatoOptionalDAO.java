package agroludos.integration.dao.db.hib;

import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.StatoOptionalDAO;
import agroludos.to.StatoOptionalTO;

public class HibStatoOptionalDAO extends HibAgroludosDAO<StatoOptionalTO> implements StatoOptionalDAO {

	public HibStatoOptionalDAO() {
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
