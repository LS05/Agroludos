package agroludos.business.as.gestoreutente;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.UtenteDAO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.UtenteTO;

class ASGestoreUtente extends AgroludosAS implements LUtente, SUtente{

	@Override
	public UtenteTO getUtente(UtenteTO uto) throws DatabaseException {
		String tipoDB = this.sysConf.getTipoDB();
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(tipoDB);
		UtenteDAO utDAO = dbDAOFact.getUtenteDAO();			
		
		return null;
	}

}