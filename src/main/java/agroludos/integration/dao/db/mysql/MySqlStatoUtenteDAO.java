package agroludos.integration.dao.db.mysql;

import org.hibernate.Session;

import agroludos.integration.dao.db.StatoUtenteDAO;
import agroludos.to.StatoUtenteTO;

public class MySqlStatoUtenteDAO extends MySqlAgroludosDAO<StatoUtenteTO>
		implements StatoUtenteDAO {

	public MySqlStatoUtenteDAO(Session session) {
		super(session);
		this.setClasse(StatoUtenteTO.class);
	}

}
