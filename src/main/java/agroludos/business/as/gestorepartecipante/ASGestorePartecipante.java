package agroludos.business.as.gestorepartecipante;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.to.PartecipanteTO;

class ASGestorePartecipante extends AgroludosAS implements LPartecipante, SPartecipante{

	@Override
	public boolean inserisciPartecipante(PartecipanteTO parto)
			throws DatabaseException {
		boolean res = false;

		PartecipanteDAO daoPar = getPartecipanteDAO();
		res = daoPar.crea(parto);

		return res;
	}

	private PartecipanteDAO getPartecipanteDAO() {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getPartecipanteDAO();
	}

	@Override
	public boolean modificaPartecipante(PartecipanteTO parto)
			throws DatabaseException {
		PartecipanteDAO daoPar = getPartecipanteDAO();
		return daoPar.update(parto);
	}

}
