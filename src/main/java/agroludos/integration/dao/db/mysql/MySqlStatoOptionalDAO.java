package agroludos.integration.dao.db.mysql;

import org.hibernate.Session;

import agroludos.integration.dao.db.StatoOptionalDAO;
import agroludos.to.StatoOptionalTO;

public class MySqlStatoOptionalDAO extends MySqlAgroludosDAO<StatoOptionalTO>
		implements StatoOptionalDAO {

	public MySqlStatoOptionalDAO(Session session) {
		super(session);
		this.setClasse(StatoOptionalTO.class);
	}

}
