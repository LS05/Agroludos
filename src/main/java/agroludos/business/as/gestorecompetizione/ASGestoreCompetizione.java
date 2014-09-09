package agroludos.business.as.gestorecompetizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.to.CompetizioneTO;

class ASGestoreCompetizione extends AgroludosAS implements LCompetizione, SCompetizione{
	
	private CompetizioneDAO getCompetizioneDAO() {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getCompetizioneDAO();
	}
	
	@Override
	public boolean inserisciCompetizione(CompetizioneTO cmpto)
			throws DatabaseException {
		boolean res = false;

		CompetizioneDAO daoCmp = getCompetizioneDAO();
		res = daoCmp.crea(cmpto);

		return res;
	}

	@Override
	public boolean modificaCompetizione(CompetizioneTO cmpto)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.update(cmpto);
	}

	@Override
	public boolean annullaCompetizione(CompetizioneTO cmpto)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.annullaCompetizione(cmpto);
	}

	@Override
	public <T> List<CompetizioneTO> getCompetizioniByMdc(T username)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.readByMdc(username);
	}

	@Override
	public <T> List<CompetizioneTO> getCompetizioniByTipo(T tipo)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.readByTipo(tipo);
	}

	@Override
	public List<CompetizioneTO> getAllCompetizione() throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.readAll();
	}

	@Override
	public <T> CompetizioneTO getCompetizioneById(T id)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.readById(id);
	}

	@Override
	public <T> List<CompetizioneTO> getCompetizioniAttive()
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.readCompetizioniAttive();
	}

}
