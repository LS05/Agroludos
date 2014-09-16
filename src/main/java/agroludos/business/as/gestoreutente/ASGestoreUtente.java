package agroludos.business.as.gestoreutente;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.UserNotFoundException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.UtenteDAO;
import agroludos.to.UtenteTO;
import agroludos.utility.PasswordEncryption;

class ASGestoreUtente extends AgroludosAS implements LUtente, SUtente{
	PasswordEncryption pwdEnc;

	ASGestoreUtente(PasswordEncryption pwdEnc){
		this.pwdEnc = pwdEnc;
	}
	
	private UtenteDAO<UtenteTO> getUtenteDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getUtenteDAO();
	}

	@Override
	public UtenteTO autenticazioneUtente(UtenteTO uto) throws DatabaseException, UserNotFoundException {
		UtenteDAO<UtenteTO> udao = this.getUtenteDAO();

		String inputPassword = uto.getPassword();
		uto.setPassword(this.pwdEnc.encryptPassword(inputPassword));

		UtenteTO res = udao.getUtente(uto);

		if(res.getUsername() == "")
			throw new UserNotFoundException("Username e/o Password errati!");

		return res;
	}

	@Override
	public boolean nuovaRegistrazione() throws DatabaseException {
		return true;
	}
}