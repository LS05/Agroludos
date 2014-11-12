package agroludos.business.as.gestoretipocompetizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.business.TipoCmpExistException;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.TipoCompetizioneDAO;
import agroludos.to.TipoCompetizioneTO;

class ASGestoreTipoCompetizione extends AgroludosAS implements LTipoCompetizione, STipoCompetizione{
	
	private AgroludosValidator validator;
	
	ASGestoreTipoCompetizione(AgroludosValidator validator){
		this.validator = validator;
	}
	
	private TipoCompetizioneDAO getTipoCompetizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoCompetizioneDAO();
	}

	@Override
	public TipoCompetizioneTO inserisciTipoCompetizione(TipoCompetizioneTO tcmto)
			throws DatabaseException, ValidationException {

		TipoCompetizioneDAO daoTcm = getTipoCompetizioneDAO();
		
		this.validator.validate(tcmto);
		List<TipoCompetizioneTO> listTcmp = daoTcm.getAll();
		for(TipoCompetizioneTO tcmp: listTcmp){
			if(tcmp.getNome().compareTo(tcmto.getNome())==0)
				throw new TipoCmpExistException();
		}
		
		return daoTcm.create(tcmto);

	}

	@Override
	public List<TipoCompetizioneTO> getAllTipoCompetizione()
			throws DatabaseException {
		TipoCompetizioneDAO daoTcm = getTipoCompetizioneDAO();
		return daoTcm.getAll();
	}
}