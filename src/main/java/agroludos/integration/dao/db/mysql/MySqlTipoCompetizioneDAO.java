package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.TipoCompetizioneDAO;
import agroludos.to.TipoCompetizioneTO;

class MySqlTipoCompetizioneDAO extends MySqlAgroludosDAO<TipoCompetizioneTO> implements TipoCompetizioneDAO {
	
	MySqlTipoCompetizioneDAO(SessionFactory sessionFactory){
		super(sessionFactory);
	}

	@Override
	public boolean crea(TipoCompetizioneTO tcpto) throws DatabaseException {
		return super.create(tcpto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoCompetizioneTO> readAll() throws DatabaseException {
		List<?> list = super.executeQuery("getAllTipoCompetizione");
		return (List<TipoCompetizioneTO>)list;
	}
}