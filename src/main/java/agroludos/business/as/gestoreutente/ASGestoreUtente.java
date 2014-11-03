package agroludos.business.as.gestoreutente;

import java.io.IOException;

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
	public UtenteTO autenticazioneUtente(UtenteTO uTO) throws DatabaseException, UserNotFoundException {
		UtenteDAO<UtenteTO> daoUtente = this.getUtenteDAO();

		String inputPassword = uTO.getPassword();
		uTO.setPassword(this.pwdEnc.encryptPassword(inputPassword));

		UtenteTO res = daoUtente.getUtente(uTO);

		if(res.getId() == -1)
			throw new UserNotFoundException();

		return res;
	}

	@Override
	public UtenteTO getUtenteByUsername(UtenteTO uTO)
			throws DatabaseException, UserNotFoundException, IOException {

		UtenteDAO<UtenteTO> daoUtente = this.getUtenteDAO();
		UtenteTO user = daoUtente.getByUsername(uTO.getUsername());

		return user;
	}

}