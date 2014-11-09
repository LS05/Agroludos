package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.to.CompetizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TipoCompetizioneTO;

class MySqlCompetizioneDAO extends MySqlAgroludosDAO<CompetizioneTO> implements CompetizioneDAO {

	MySqlCompetizioneDAO() {
		this.setClasse(toFact.createCompetizioneTO());
	}

	@Override
	public List<CompetizioneTO> readByTipo(TipoCompetizioneTO tipo) throws DatabaseException{

		List<TipoCompetizioneTO> param = new ArrayList<TipoCompetizioneTO>();
		param.add(tipo);

		List<CompetizioneTO> res = super.executeParamQuery("getCompetizioneByTipo", param);

		return res;
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
		List<TipoCompetizioneTO> param = new ArrayList<TipoCompetizioneTO>();
		param.add(tcmto);

		List<CompetizioneTO> res = super.executeParamQuery("getCompetizioniByTipo", param);


		return res;
	}

}