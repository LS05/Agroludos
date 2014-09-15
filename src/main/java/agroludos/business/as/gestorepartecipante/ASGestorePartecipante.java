package agroludos.business.as.gestorepartecipante;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.to.PartecipanteTO;

class ASGestorePartecipante extends AgroludosAS implements LPartecipante, SPartecipante{

	private PartecipanteDAO getPartecipanteDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getPartecipanteDAO();
	}

	@Override
	public boolean inserisciPartecipante(PartecipanteTO parto)
			throws DatabaseException {
		boolean res = false;

		PartecipanteDAO daoPar = getPartecipanteDAO();
		res = daoPar.crea(parto);

		return res;
	}

	@Override
	public boolean modificaPartecipante(PartecipanteTO parto)
			throws DatabaseException {
		PartecipanteDAO daoPar = getPartecipanteDAO();
		return daoPar.update(parto);
	}

	@Override
	public PartecipanteTO getPartecipante(PartecipanteTO parto)
			throws DatabaseException {
		PartecipanteDAO daoPar = getPartecipanteDAO();
		return daoPar.getByUsername(parto.getUsername());
	}

	@Override
	public List<PartecipanteTO> getAllPartecipante() throws DatabaseException {
		PartecipanteDAO daoPar = getPartecipanteDAO();
		return daoPar.readAll();
	}

	@Override
	public PartecipanteTO getPartecipanteById(PartecipanteTO parto)
			throws DatabaseException {
		PartecipanteDAO daoPar = getPartecipanteDAO();
		return daoPar.readByID(parto.getId());
	}
}