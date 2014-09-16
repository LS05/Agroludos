package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.to.PartecipanteTO;

class MySqlPartecipanteDAO extends MySqlUtenteDAO<PartecipanteTO> implements PartecipanteDAO {

	MySqlPartecipanteDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public PartecipanteTO readByCF(String cf) throws DatabaseException {

		List<String> param = new ArrayList<String>();
		param.add(cf);

		List<PartecipanteTO> list = super.executeParamQuery("getPartecipanteByCF", param);
		PartecipanteTO res = (PartecipanteTO)list.get(0);

		this.setNomeRuolo(res);
		this.setNomeStatoUtente(res);

		return res;
	}
}