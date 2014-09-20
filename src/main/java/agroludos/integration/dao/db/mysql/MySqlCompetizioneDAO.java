package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.to.CompetizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TipoCompetizioneTO;

class MySqlCompetizioneDAO extends MySqlAgroludosDAO<CompetizioneTO> implements CompetizioneDAO {

	MySqlCompetizioneDAO(Session session) {
		super(session);
		this.setClasse(CompetizioneTO.class);
	}

	@Override
	public List<CompetizioneTO> readByTipo(TipoCompetizioneTO tipo) throws DatabaseException{

		List<TipoCompetizioneTO> param = new ArrayList<TipoCompetizioneTO>();
		param.add(tipo);

		List<CompetizioneTO> res = super.executeParamQuery("getCompetizioneByTipo", param);

		return res;
	}

	@Override
	public List<CompetizioneTO> readByMdc(ManagerDiCompetizioneTO mdc) throws DatabaseException {
		List<ManagerDiCompetizioneTO> param = new ArrayList<ManagerDiCompetizioneTO>();
		param.add(mdc);

		List<CompetizioneTO> res = super.executeParamQuery("getCompetizioneByMdc", param);


		return res;

	}

	@Override
	public CompetizioneTO readById(Integer id) throws DatabaseException{

		CompetizioneTO res = super.findOne(id);

		return res;
	}

	@Override
	public List<CompetizioneTO> readCompetizioniAttive() throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(1);

		List<CompetizioneTO> list = super.executeParamQuery("getCompetizioniAttive", param);



		return list;
	}

	@Override
	public CompetizioneTO annullaCompetizione(CompetizioneTO cmpto)
			throws DatabaseException {
//		TransferObjectFactory fact = new TransferObjectFactory();
//		StatoCompetizioneTO scto = fact.createStatoCompetizioneTO();
//		scto.setId(0);
//		scto.setNome("annullata");
		return super.update(cmpto);	
	}

}