package agroludos.business.as.gestoreutente;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DBFactoryException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.UtenteTO;

class ASGestoreUtente extends AgroludosAS implements LUtente, SUtente{
	
	@Override
	public UtenteTO getUtente(UtenteTO uto) {
		String tipoDB = this.sysConf.getTipoDB();
		UtenteTO res = this.toFact.createUTO();
		DBDAOFactory dbDAOFact = null;
		ManagerDiCompetizioneDAO mdcDAO = null;
		ManagerDiSistemaDAO mdsDAO = null;
		try {
			dbDAOFact = this.dbFact.getDAOFactory(tipoDB);
			mdcDAO = dbDAOFact.getManagerDiCompetizioneDAO();
			mdsDAO = dbDAOFact.getManagerDiSistemaDAO();
		} catch (DBFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
}