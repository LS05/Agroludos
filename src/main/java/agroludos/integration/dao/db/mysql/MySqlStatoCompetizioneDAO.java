package agroludos.integration.dao.db.mysql;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.StatoCompetizioneDAO;
import agroludos.to.StatoCompetizioneTO;

public class MySqlStatoCompetizioneDAO extends MySqlAgroludosDAO<StatoCompetizioneTO> implements StatoCompetizioneDAO {

	public MySqlStatoCompetizioneDAO() {
		this.setClasse(toFact.createStatoCompetizioneTO());
	}

	@Override
	public StatoCompetizioneTO getStatoCmpAnnullata() throws DatabaseException {
		List<StatoCompetizioneTO> stato0 = super.executeQuery("getStatoCmpAnnullata");

		return stato0.get(0);
	}
	@Override
	public StatoCompetizioneTO getStatoCmpAperta() throws DatabaseException {
		List<StatoCompetizioneTO> stato1 = super.executeQuery("getStatoCmpAperta");

		return stato1.get(0);
	}

	@Override
	public StatoCompetizioneTO getStatoCmpInCorso() throws DatabaseException {
		List<StatoCompetizioneTO> stato2 = super.executeQuery("getStatoCmpInCorso");

		return stato2.get(0);
	}
	@Override
	public StatoCompetizioneTO getStatoCmpChiusa() throws DatabaseException {
		List<StatoCompetizioneTO> stato3 = super.executeQuery("getStatoCmpChiusa");

		return stato3.get(0);
	}
	@Override
	public StatoCompetizioneTO getStatoCmpTerminata() throws DatabaseException {
		List<StatoCompetizioneTO> stato4 = super.executeQuery("getStatoCmpTerminata");

		return stato4.get(0);
	}
}
