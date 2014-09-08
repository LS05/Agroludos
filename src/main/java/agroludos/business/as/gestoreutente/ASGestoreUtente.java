package agroludos.business.as.gestoreutente;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.UtenteDAO;
import agroludos.to.UtenteTO;

class ASGestoreUtente extends AgroludosAS implements LUtente, SUtente{

	@Override
	public UtenteTO autencazioneUtente(UtenteTO uto) throws DatabaseException {
		UtenteDAO udao = this.getUtenteDAO();
		return udao.autenticazione(uto);
	}
	
	private UtenteDAO getUtenteDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getUtenteDAO();
	}

}