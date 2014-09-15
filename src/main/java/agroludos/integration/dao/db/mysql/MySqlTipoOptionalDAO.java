package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.to.TipoOptionalTO;

class MySqlTipoOptionalDAO extends MySqlAgroludosDAO<TipoOptionalTO> implements TipoOptionalDAO {

	MySqlTipoOptionalDAO(SessionFactory sessionFactory){
		super(sessionFactory);
	}

	@Override
	public boolean crea(TipoOptionalTO tipoOptTO) throws DatabaseException {
		return super.create(tipoOptTO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoOptionalTO> readAll() throws DatabaseException {
		List<?> list = super.executeQuery("getAllTipoOptional");
		return (List<TipoOptionalTO>)list;
	}
}