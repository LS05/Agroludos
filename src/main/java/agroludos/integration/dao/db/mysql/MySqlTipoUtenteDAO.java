package agroludos.integration.dao.db.mysql;

import agroludos.integration.dao.db.TipoUtenteDAO;
import agroludos.to.TipoUtenteTO;

public class MySqlTipoUtenteDAO extends MySqlAgroludosDAO<TipoUtenteTO> implements TipoUtenteDAO {

	public MySqlTipoUtenteDAO() {
		this.setClasse(toFact.createTipoUtenteTO());
	}

}
