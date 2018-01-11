package agroludos.integration.dao.db.hib;


import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.StatoIscrizioneDAO;
import agroludos.to.StatoIscrizioneTO;

public class HibStatoIscrizioneDAO extends HibAgroludosDAO<StatoIscrizioneTO> implements StatoIscrizioneDAO{

	public HibStatoIscrizioneDAO() {
		this.setClasse(toFact.createStatoIscrizioneTO());
	}

	@Override
	public StatoIscrizioneTO getStatoAttivo() throws DatabaseException {
		return this.findOne(1);
	}

	@Override
	public StatoIscrizioneTO getStatoDisattivo() throws DatabaseException {
		return this.findOne(0);
	}
	
	@Override
	public StatoIscrizioneTO getStatoIscrizioneTerminato() throws DatabaseException {
		return this.findOne(2);
	}

}