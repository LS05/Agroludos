package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;

class MySqlIscrizioneDAO extends MySqlAgroludosDAO<IscrizioneTO> implements IscrizioneDAO{

	MySqlIscrizioneDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public boolean crea(IscrizioneTO iscto) throws DatabaseException {
		return super.create(iscto);
	}

	@Override
	public boolean update(IscrizioneTO iscto) throws DatabaseException {
		return super.update(iscto);
	}

	@Override
	public boolean annullaIscrizione(IscrizioneTO iscto) throws DatabaseException {
		iscto.setStato(0);
		return super.update(iscto);
	}

	@Override
	public List<IscrizioneTO> getAllIscrizioni() throws DatabaseException {
		List<IscrizioneTO> res = super.executeQuery("getAllIscrizioni");
		
		int index=0;
		for(Object isc: res){
			this.setNomeStato(res.get(index));
			index++;
		}

		return  res;
		
	}

	@Override
	public void setNomeStato(IscrizioneTO iscto) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(iscto.getStato());
		iscto.setNomeStato(super.executeParamStringQuery("getNomeStatoIscr", param));
	}

}
