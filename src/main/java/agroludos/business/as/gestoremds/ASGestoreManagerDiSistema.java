package agroludos.business.as.gestoremds;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.utility.PasswordEncryption;

class ASGestoreManagerDiSistema extends AgroludosAS implements LManagerDiSistema, SManagerDiSistema{
	PasswordEncryption pwdEnc;
	
	ASGestoreManagerDiSistema(PasswordEncryption pwdEnc){
		this.pwdEnc = pwdEnc;
	}
	
	@Override
	public boolean nuovoManagerDiSistema(ManagerDiSistemaTO mdsto) throws DatabaseException {
		// TODO Auto-generated catch block
		// Catturare l'eccezione del DB perchè un mancato inserimento del MDS significherebbe
		// resettare il tipo di DB nel file xml
		String inputPassword = mdsto.getPassword();
		mdsto.setPassword(this.pwdEnc.encryptPassword(inputPassword));
		boolean res = getManagerDiSistemaDAO().crea(mdsto);
		return res;
	}

	@Override
	public ManagerDiSistemaTO getManagerDiSistema(ManagerDiSistemaTO mdsto) throws DatabaseException {
		return getManagerDiSistemaDAO().readByUsername(mdsto.getUsername());
	}
	
	private ManagerDiSistemaDAO getManagerDiSistemaDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getManagerDiSistemaDAO();
	}

}