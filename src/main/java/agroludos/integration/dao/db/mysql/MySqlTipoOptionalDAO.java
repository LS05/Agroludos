package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Query;

import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.to.TipoOptionalTO;

class MySqlTipoOptionalDAO extends MySqlAgroludosDAO implements TipoOptionalDAO {

	@Override
	public boolean crea(TipoOptionalTO topto) {
		boolean res = false;
		// TODO Aggiungere gestione eccezioni hibernate
		this.session.beginTransaction();
		this.session.save(topto);
		res = true;
		this.session.getTransaction().commit();
		return res;
	}

	@Override
	public List<TipoOptionalTO> readAll() {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getAllTipoOptional");
		List<TipoOptionalTO> list = query.list();
		return list;
	}

}
