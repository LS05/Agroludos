package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.PartecipanteTO;

class MySqlPartecipanteDAO extends MySqlUtenteDAO<PartecipanteTO> implements PartecipanteDAO {

	MySqlPartecipanteDAO(Session session) {
		super(session);
		this.setClasse(PartecipanteTO.class);
	}

	@Override
	public PartecipanteTO readByCF(String cf) throws DatabaseException {

		List<String> param = new ArrayList<String>();
		param.add(cf);

		List<PartecipanteTO> list = super.executeParamQuery("getPartecipanteByCF", param);
		PartecipanteTO res = (PartecipanteTO)list.get(0);

		return res;
	}

	@Override
	public PartecipanteTO readByUsername(String username)
			throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(username);

		List<PartecipanteTO> list = super.executeParamQuery("getPartecipanteByUsername", param);
		PartecipanteTO res = (PartecipanteTO)list.get(0);

		return res;
	}


}