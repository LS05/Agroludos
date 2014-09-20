package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Session;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.StatoCompetizioneDAO;
import agroludos.to.StatoCompetizioneTO;

public class MySqlStatoCompetizioneDAO extends MySqlAgroludosDAO<StatoCompetizioneTO> implements StatoCompetizioneDAO {

	public MySqlStatoCompetizioneDAO(Session session) {
		super(session);
		this.setClasse(StatoCompetizioneTO.class);
	}

	@Override
	public StatoCompetizioneTO getStatoCmpAnnullata() throws DatabaseException {
		List<StatoCompetizioneTO> stato0 = super.executeQuery("getStatoCmpAnnullata");

		return stato0.get(0);
	}

}
