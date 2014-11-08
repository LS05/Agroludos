package agroludos.business.as.gestoreconfigurazione;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.ValidationException;
import agroludos.integration.dao.file.FConfigurazioneDAO;
import agroludos.integration.dao.file.FileDAOFactory;
import agroludos.integration.dao.file.FileFactory;
import agroludos.system.SystemConf;
import agroludos.to.DatabaseTO;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore di Competizione.</b><br /> 
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ASGestoreConfigurazione extends AgroludosAS implements LConfigurazione, SConfigurazione{

	private FileDAOFactory fileDaoFact;

	private FConfigurazioneDAO fileConf;

	private SystemConf sysConf;

	private AgroludosValidator validator;

	ASGestoreConfigurazione(SystemConf sysConf, FileFactory filefact, AgroludosValidator validator){
		this.sysConf = sysConf;
		this.validator = validator;
		this.fileDaoFact = filefact.getDAOFactory(this.sysConf.getTipoConf());
		this.fileConf = this.fileDaoFact.getConfigurazioneDAO();
	}

	@Override
	public boolean inserisciConfigurazione(DatabaseTO dbto) throws DatabaseException, ValidationException {
		boolean res = false;

		this.validator.validate(dbto);

		if(this.fileConf.creaConfigurazione(dbto)){
			res = true;
		}

		return res;
	}

	@Override
	public boolean checkConfigurazione() {
		boolean res = false;
		String tipoDB = this.sysConf.getTipoDB();

		if(tipoDB.length() != 0){
			res = true;
		}

		return res;
	}

	@Override
	public boolean testConnessioneDB() throws DatabaseException {
		boolean res = false;

		this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		res = true;

		return res;
	}
}