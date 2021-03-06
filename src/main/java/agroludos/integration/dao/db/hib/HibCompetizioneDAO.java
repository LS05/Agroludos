package agroludos.integration.dao.db.hib;

import java.util.ArrayList;
import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.to.CompetizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TipoCompetizioneTO;

class HibCompetizioneDAO extends HibAgroludosDAO<CompetizioneTO> implements CompetizioneDAO {

	/**
	 * Il costruttore inizializza l'entità competizione da utilizzare in tutte le operazioni
	 * di questo DAO, utilizzando il metodo setClasse()
	 * 
	 * @see agroludos.integration.dao.db.hib.HibAgroludosDAO#setClasse()
	 */
	HibCompetizioneDAO() {
		this.setClasse(toFact.createCompetizioneTO());
	}
	
	@Override
	public CompetizioneTO readById(Integer id) throws DatabaseException{

		CompetizioneTO res = super.findOne(id);

		return res;
	}

	@Override
	public List<CompetizioneTO> readCompetizioniAttive() throws DatabaseException { 
		return super.executeQuery("getCompetizioniAttive");
	}
	@Override
	public List<CompetizioneTO> readCompetizioniAperte()
			throws DatabaseException {
		return super.executeQuery("getCompetizioniAperte");
	}

	@Override
	public CompetizioneTO annullaCompetizione(CompetizioneTO cmpto)
			throws DatabaseException {
		return super.update(cmpto);	

	}

	@Override
	public List<CompetizioneTO> readByMdc(ManagerDiCompetizioneTO mdc) throws DatabaseException {
		List<ManagerDiCompetizioneTO> param = new ArrayList<ManagerDiCompetizioneTO>();
		param.add(mdc);

		List<CompetizioneTO> res = super.executeParamQuery("getCompetizioneByMdc", param);


		return res;

	}

	@Override
	public List<CompetizioneTO> readAttiveByMdc(ManagerDiCompetizioneTO mdc)
			throws DatabaseException {
		List<ManagerDiCompetizioneTO> param = new ArrayList<ManagerDiCompetizioneTO>();
		param.add(mdc);

		List<CompetizioneTO> res = super.executeParamQuery("getCompetizioneAttiveByMdc", param);


		return res;
	}

	@Override
	public List<CompetizioneTO> getCompetizioniByTipo(TipoCompetizioneTO tcmto) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(tcmto.getNome());

		List<CompetizioneTO> res = super.executeParamQuery("getCompetizioniByTipo", param);


		return res;
	}

}