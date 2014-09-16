package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.to.CompetizioneTO;

class MySqlCompetizioneDAO extends MySqlAgroludosDAO<CompetizioneTO> implements CompetizioneDAO {

	MySqlCompetizioneDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public boolean crea(CompetizioneTO cmpto) throws DatabaseException {
		return super.create(cmpto);
	}

	@Override
	public boolean update(CompetizioneTO cmpto) throws DatabaseException {
		return super.update(cmpto);
	}

	@Override
	public boolean annullaCompetizione(CompetizioneTO cmpto) throws DatabaseException {
		cmpto.setStato(0);
		return super.update(cmpto);
	}

	@Override
	public List<CompetizioneTO> readAll() throws DatabaseException {
		List<CompetizioneTO> res = super.executeQuery("getAllCompetizione");

		for(CompetizioneTO cmp: res){
			this.setNomeStatoComp(cmp);
			this.setNomeTipoComp(cmp);
		}

		return  res;
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

		List<CompetizioneTO> res = super.executeParamQuery("getCompetizioniByMdc", param);

		for(CompetizioneTO cmp: res){
			this.setNomeStatoComp(cmp);
			this.setNomeTipoComp(cmp);
		}
		
		return res;

	}

	@Override
	public CompetizioneTO readById(Integer id) throws DatabaseException{
		CompetizioneTO res = null;
		List<Integer> param = new ArrayList<Integer>();
		param.add(id);

		List<CompetizioneTO> list = super.executeParamQuery("getCompetizioneById", param);
		res = list.get(0);

		//setto il nome dello stato e del tipo
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
	public void setNomeTipoComp(CompetizioneTO cmpto) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(cmpto.getTipo());
		cmpto.setNomeTipo(super.executeParamStringQuery("getNomeTipoComp", param));
	}

	@Override
	public void setNomeStatoComp(CompetizioneTO cmpto) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(cmpto.getStato());
		cmpto.setNomeStato(super.executeParamStringQuery("getNomeStatoComp", param));
	}

}
