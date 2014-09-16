package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.to.IscrizioneTO;

class MySqlIscrizioneDAO extends MySqlAgroludosDAO<IscrizioneTO> implements IscrizioneDAO{

	MySqlIscrizioneDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
		this.setClazz(IscrizioneTO.class);
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
	public IscrizioneTO annullaIscrizione(IscrizioneTO iscto) throws DatabaseException {
		iscto.setStato(0);
		return super.update(iscto);
	}

//	@Override
//	public List<IscrizioneTO> getAllIscrizioni() throws DatabaseException {
//		List<IscrizioneTO> res = super.executeQuery("getAllIscrizioni");
//		
//		for(IscrizioneTO isc: res){
//			this.setNomeStato(isc);
//		}
//
//		return  res;
//		
//	}

	@Override
	public void setNomeStato(IscrizioneTO iscto) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(iscto.getStato());
		String stato = super.<String>executeValParamQuery("getNomeStatoIscr", param);
		iscto.setNomeStato(stato);
	}

}
