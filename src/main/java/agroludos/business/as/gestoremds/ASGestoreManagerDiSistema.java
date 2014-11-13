package agroludos.business.as.gestoremds;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.business.MdsNotFoundException;
import agroludos.exceptions.business.UtenteEsistenteException;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.integration.dao.db.StatoUtenteDAO;
import agroludos.integration.dao.db.TipoUtenteDAO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.UtenteTO;
import agroludos.utility.PasswordEncryption;

class ASGestoreManagerDiSistema extends AgroludosAS implements LManagerDiSistema, SManagerDiSistema{
	private PasswordEncryption pwdEnc;
	private AgroludosValidator validator;

	ASGestoreManagerDiSistema(PasswordEncryption pwdEnc, AgroludosValidator validator){
		this.pwdEnc = pwdEnc;
		this.validator = validator;
	}

	private ManagerDiSistemaDAO getManagerDiSistemaDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getManagerDiSistemaDAO();
	}

	private TipoUtenteDAO getTipoUtenteDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoUtenteDAO();
	}

	private StatoUtenteDAO getStatoUtenteDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getStatoUtenteDAO();
	}

	@Override
	public ManagerDiSistemaTO nuovoManagerDiSistema(ManagerDiSistemaTO mdsTO) 
			throws DatabaseException, ValidationException {

		ManagerDiSistemaDAO mdsDao = this.getManagerDiSistemaDAO();

		if( !mdsDao.esisteEmail(mdsTO) && !mdsDao.esisteUsername(mdsTO) ){ 

			this.validator.validate(mdsTO);

			String inputPassword = mdsTO.getPassword();
			mdsTO.setPassword(this.pwdEnc.encryptPassword(inputPassword));

			TipoUtenteDAO tipoUtenteDao = this.getTipoUtenteDAO();
			StatoUtenteDAO statoUtenteDao = this.getStatoUtenteDAO();

			mdsTO.setTipoUtente(tipoUtenteDao.getTipoUtenteMds());
			mdsTO.setStatoUtente(statoUtenteDao.getStatoAttivo());
		} else{
			if(mdsDao.esisteEmail(mdsTO)){
				throw new UtenteEsistenteException("Email già esistente!");
			}else{
				throw new UtenteEsistenteException("Username già esistente");
			}
		}

		return this.getManagerDiSistemaDAO().create(mdsTO);
	}

	@Override
	public ManagerDiSistemaTO getManagerDiSistema(ManagerDiSistemaTO mdsto) throws DatabaseException {
		UtenteTO uTO = this.getManagerDiSistemaDAO().getByUsername(mdsto.getUsername());
		return (ManagerDiSistemaTO) uTO;
	}

	@Override
	public boolean checkMds() throws DatabaseException, MdsNotFoundException {
		boolean res = false;

		if(this.getManagerDiSistemaDAO().checkMds()){
			res = true;
		} else {
			throw new MdsNotFoundException();
		}

		return res;
	}
}