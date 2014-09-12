package agroludos.business.as.gestorecompetizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.to.CompetizioneTO;
/**
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
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
	public List<CompetizioneTO> getCompetizioniByMdc(CompetizioneTO cmpto)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.readByMdc(cmpto.getMdc());
	}

	@Override
	public List<CompetizioneTO> getCompetizioniByTipo(CompetizioneTO cmpto)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.readByTipo(cmpto.getTipo());
	}

	@Override
	public List<CompetizioneTO> getAllCompetizione() throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.readAll();
	}

	@Override
	public CompetizioneTO getCompetizioneById(CompetizioneTO cmpto)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.readById(cmpto.getId());
	}

	@Override
	public List<CompetizioneTO> getCompetizioniAttive()
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.readCompetizioniAttive();
	}

}
