package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.to.IscrizioneTO;

class MySqlIscrizioneDAO extends MySqlAgroludosDAO<IscrizioneTO> implements IscrizioneDAO{

	MySqlIscrizioneDAO(Session session) {
		super(session);
		this.setClasse(IscrizioneTO.class);
	}

	@Override
	public List< IscrizioneTO > getAll() throws DatabaseException{
		List< IscrizioneTO > res = super.getAll();

		for(IscrizioneTO iscr : res){
			this.setNomeStato(iscr);
		}

		return res;
	}

	@Override
	public boolean annullaIscrizione(IscrizioneTO iscto) throws DatabaseException {
		boolean res = false;
		iscto.setStato(0);
		
		if(super.update(iscto) != null){
			res = true;
		} else {
			res = false;
		}

		return res;
	}

	@Override
	public void setNomeStato(IscrizioneTO iscto) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(iscto.getStato());
		String stato = super.<String>executeValParamQuery("getNomeStatoIscr", param);
		iscto.setNomeStato(stato);
	}
}