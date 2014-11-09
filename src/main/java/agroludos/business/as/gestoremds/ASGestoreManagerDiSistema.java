package agroludos.business.as.gestoremds;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.MdsNotFoundException;
import agroludos.exceptions.UtenteEsistenteException;
import agroludos.exceptions.ValidationException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.integration.dao.db.StatoUtenteDAO;
import agroludos.integration.dao.db.TipoUtenteDAO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.StatoUtenteTO;
import agroludos.to.TipoUtenteTO;
import agroludos.to.UtenteTO;
import agroludos.utility.PasswordEncryption;

class ASGestoreManagerDiSistema extends AgroludosAS implements LManagerDiSistema, SManagerDiSistema{
	private PasswordEncryption pwdEnc;
	private AgroludosValidator validator;

	ASGestoreManagerDiSistema(PasswordEncryption pwdEnc, AgroludosValidator validator){
		this.pwdEnc = pwdEnc;
		this.validator = validator;
	}

	private DBDAOFactory getDBDAOFactory() throws DatabaseException{
		return this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
	}

	private ManagerDiSistemaDAO getManagerDiSistemaDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getManagerDiSistemaDAO();
	}

	@Override
	public ManagerDiSistemaTO nuovoManagerDiSistema(ManagerDiSistemaTO mdsTO) 
			throws DatabaseException, ValidationException {

		ManagerDiSistemaDAO mdsDao = this.getManagerDiSistemaDAO();

		if( !mdsDao.esisteEmail(mdsTO) && !mdsDao.esisteUsername(mdsTO) ){ 

			this.validator.validate(mdsTO);

			String inputPassword = mdsTO.getPassword();
			mdsTO.setPassword(this.pwdEnc.encryptPassword(inputPassword));

			TipoUtenteDAO tipoUtenteDao = this.getDBDAOFactory().getTipoUtenteDAO();
			List<TipoUtenteTO> tipoUtente = tipoUtenteDao.executeQuery("getTipoUtenteMds");

			StatoUtenteDAO statoUtenteDao = this.getDBDAOFactory().getStatoUtenteDAO();
			List<StatoUtenteTO> statoUtente = statoUtenteDao.executeQuery("getStatoAttivo");

			mdsTO.setTipoUtente(tipoUtente.get(0));
			mdsTO.setStatoUtente(statoUtente.get(0));
		} else{
			throw new UtenteEsistenteException();
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