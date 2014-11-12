package agroludos.integration.dao.db.mysql;

import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.TipoUtenteDAO;
import agroludos.to.TipoUtenteTO;

public class MySqlTipoUtenteDAO extends MySqlAgroludosDAO<TipoUtenteTO> implements TipoUtenteDAO {

	public MySqlTipoUtenteDAO() {
		this.setClasse(toFact.createTipoUtenteTO());
	}

	@Override
	public TipoUtenteTO getTipoUtenteMds() throws DatabaseException {
		return this.findOne(0);
	}

	@Override
	public TipoUtenteTO getTipoUtenteMdc() throws DatabaseException {
		return this.findOne(1);
	}

	@Override
	public TipoUtenteTO getTipoUtentePart() throws DatabaseException {
		return this.findOne(2);
	}

}
