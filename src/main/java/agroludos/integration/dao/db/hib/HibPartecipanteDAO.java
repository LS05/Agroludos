package agroludos.integration.dao.db.hib;

import java.util.ArrayList;
import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.to.PartecipanteTO;

class HibPartecipanteDAO extends HibUtenteDAO<PartecipanteTO> implements PartecipanteDAO {

	HibPartecipanteDAO() {
		this.setClasse(toFact.createPartecipanteTO());
	}

	@Override
	public PartecipanteTO readByCF(String cf) throws DatabaseException {

		List<String> param = new ArrayList<String>();
		param.add(cf);

		List<PartecipanteTO> list = super.executeParamQuery("getUtenteByCF", param);
		PartecipanteTO res = (PartecipanteTO)list.get(0);

		return res;
	}

	@Override
	public PartecipanteTO readByUsername(String username)
			throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(username);

		List<PartecipanteTO> list = super.executeParamQuery("getUtenteByUsername", param);
		PartecipanteTO res = (PartecipanteTO)list.get(0);

		return res;
	}

	@Override
	public List<PartecipanteTO> getAllPartecipante() throws DatabaseException {
		return super.executeQuery("getAllPartecipante");
	}


}