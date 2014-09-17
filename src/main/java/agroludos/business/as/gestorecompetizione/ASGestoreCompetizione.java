package agroludos.business.as.gestorecompetizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.to.CompetizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TipoCompetizioneTO;
/**
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ASGestoreCompetizione extends AgroludosAS implements LCompetizione, SCompetizione{

	private CompetizioneDAO getCompetizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getCompetizioneDAO();
	}

	@Override
	public boolean inserisciCompetizione(CompetizioneTO cmpto)
			throws DatabaseException {
		boolean res = false;

		CompetizioneDAO daoCmp = getCompetizioneDAO();
		res = daoCmp.create(cmpto);

		return res;
	}

	@Override
	public CompetizioneTO modificaCompetizione(CompetizioneTO cmpto)
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
	public List<CompetizioneTO> getCompetizioneByMdc(ManagerDiCompetizioneTO mdcto)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.readByMdc(mdcto.getId());
	}

	@Override
	public List<CompetizioneTO> getCompetizioniByTipo(TipoCompetizioneTO tcmto)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.readByTipo(tcmto.getId());
	}

	@Override
	public List<CompetizioneTO> getAllCompetizione() throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.getAll();
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