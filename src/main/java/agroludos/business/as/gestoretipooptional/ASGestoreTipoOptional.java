package agroludos.business.as.gestoretipooptional;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.ValidationException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.to.TipoOptionalTO;

class ASGestoreTipoOptional extends AgroludosAS implements LTipoOptional, STipoOptional{
	private AgroludosValidator validator;
	
	ASGestoreTipoOptional(AgroludosValidator validator){
		this.validator = validator;
	}
	
	private TipoOptionalDAO getTipoOptionalDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoOptionalDAO();
	}

	@Override
	public TipoOptionalTO inserisciTipoOptional(TipoOptionalTO tipoOptTO)
			throws DatabaseException, ValidationException {

		TipoOptionalDAO daoTop = getTipoOptionalDAO();
		this.validator.validate(tipoOptTO);
		
		return daoTop.create(tipoOptTO);
	}

	@Override
	public List<TipoOptionalTO> getAllTipoOptional() throws DatabaseException {
		TipoOptionalDAO daoTop = getTipoOptionalDAO();
		return daoTop.getAll();
	}
}