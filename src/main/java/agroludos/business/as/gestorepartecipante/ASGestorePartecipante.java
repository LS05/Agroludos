package agroludos.business.as.gestorepartecipante;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.to.PartecipanteTO;
import agroludos.to.UtenteTO;

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
		res = daoPar.create(parto);

		return res;
	}

	@Override
	public PartecipanteTO modificaPartecipante(PartecipanteTO parto)
			throws DatabaseException {
		PartecipanteDAO daoPar = getPartecipanteDAO();
		PartecipanteTO part = (PartecipanteTO)daoPar.update(parto);
		return part;
	}

	@Override
	public PartecipanteTO getPartecipante(PartecipanteTO parto)
			throws DatabaseException {
		PartecipanteDAO daoPar = getPartecipanteDAO();
		UtenteTO user = daoPar.getByUsername(parto.getUsername());
		return (PartecipanteTO)user;
	}

	@Override
	public List<PartecipanteTO> getAllPartecipante() throws DatabaseException {
		PartecipanteDAO daoPar = getPartecipanteDAO();
		return daoPar.getAll();
	}

	@Override
	public PartecipanteTO getPartecipanteById(PartecipanteTO parto)
			throws DatabaseException {
		PartecipanteDAO daoPar = getPartecipanteDAO();
		UtenteTO user = daoPar.getByID(parto.getId());
		return (PartecipanteTO)user;
	}
}