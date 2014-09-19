package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.to.CompetizioneTO;

class MySqlCompetizioneDAO extends MySqlAgroludosDAO<CompetizioneTO> implements CompetizioneDAO {

	MySqlCompetizioneDAO(Session session) {
		super(session);
		this.setClasse(CompetizioneTO.class);
	}

	@Override
	public List<CompetizioneTO> readByTipo(Integer tipo) throws DatabaseException{

		List<Integer> param = new ArrayList<Integer>();
		param.add(tipo);

		List<CompetizioneTO> res = super.executeParamQuery("getCompetizioneByTipo", param);

		for(CompetizioneTO cmp: res){
			this.setNomeStatoComp(cmp);
			this.setNomeTipoComp(cmp);
		}

		return res;
	}

	@Override
	public List<CompetizioneTO> readByMdc(Integer mdc) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(mdc);

		List<CompetizioneTO> res = super.executeParamQuery("getCompetizioneByMdc", param);

		for(CompetizioneTO cmp: res){
			this.setNomeStatoComp(cmp);
			this.setNomeTipoComp(cmp);
		}

		return res;

	}

	@Override
	public CompetizioneTO readById(Integer id) throws DatabaseException{

		CompetizioneTO res = super.findOne(id);

		this.setNomeStatoComp(res);
		this.setNomeTipoComp(res);


		return res;
	}

	@Override
	public List<CompetizioneTO> readCompetizioniAttive() throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(1);

		List<CompetizioneTO> list = super.executeParamQuery("getCompetizioniAttive", param);

		for(CompetizioneTO cmp: list){
			this.setNomeStatoComp(cmp);
			this.setNomeTipoComp(cmp);
		}


		return list;
	}

	@Override
	public CompetizioneTO annullaCompetizione(CompetizioneTO cmpto)
			throws DatabaseException {
			cmpto.setStato(0);
			return super.update(cmpto);	
	}

	private void setNomeTipoComp(CompetizioneTO cmpto) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(cmpto.getTipo());
		String tipo = super.<String>executeValParamQuery("getNomeTipoComp", param);
		cmpto.setNomeTipo(tipo);
	}

	private void setNomeStatoComp(CompetizioneTO cmpto) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(cmpto.getStato());
		String stato = super.<String>executeValParamQuery("getNomeStatoComp", param);
		cmpto.setNomeStato(stato);
	}
}