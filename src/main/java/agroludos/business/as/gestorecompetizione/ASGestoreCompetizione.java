package agroludos.business.as.gestorecompetizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.to.CompetizioneTO;

class ASGestoreCompetizione extends AgroludosAS implements LCompetizione, SCompetizione{

	@Override
	public boolean inserisciCompetizione(CompetizioneTO cmpto)
			throws DatabaseException {
		boolean res = false;

		CompetizioneDAO daoCmp = getCompetizioneDAO();
		res = daoCmp.crea(cmpto);

		return res;
	}

	private CompetizioneDAO getCompetizioneDAO() {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getCompetizioneDAO();
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
		return daoCmp.readByMdc(cmpto);
	}

	@Override
	public List<CompetizioneTO> getCompetizioniByTipo(CompetizioneTO cmpto)
			throws DatabaseException {
		CompetizioneDAO daoCmp = getCompetizioneDAO();
		return daoCmp.readByTipo(cmpto);
	}

}
