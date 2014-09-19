package agroludos.integration.dao.db.mysql;

import org.hibernate.Session;

import agroludos.integration.dao.db.TipoUtenteDAO;
import agroludos.to.TipoUtenteTO;

public class MySqlTipoUtenteDAO extends MySqlAgroludosDAO<TipoUtenteTO>
		implements TipoUtenteDAO {

	public MySqlTipoUtenteDAO(Session session) {
		super(session);
		this.setClasse(TipoUtenteTO.class);
	}

}
