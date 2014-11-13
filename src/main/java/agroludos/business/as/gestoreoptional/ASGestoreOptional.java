package agroludos.business.as.gestoreoptional;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.business.OptionalCmpAttiveException;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.OptionalDAO;
import agroludos.integration.dao.db.StatoOptionalDAO;
import agroludos.to.CompetizioneTO;
import agroludos.to.OptionalTO;
import agroludos.to.StatoOptionalTO;
import agroludos.to.TipoOptionalTO;

class ASGestoreOptional extends AgroludosAS implements LOptional, SOptional{

	private AgroludosValidator validator;

	ASGestoreOptional(AgroludosValidator validator){
		this.validator = validator;
	}

	private OptionalDAO getOptionalDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getOptionalDAO();
	}

	private void validate(OptionalTO optTO) throws ValidationException{
		this.validator.validate(optTO);
	}

	@Override
	public OptionalTO inserisciOptional(OptionalTO optto) 
			throws DatabaseException, ValidationException {

		OptionalDAO daoOpt = getOptionalDAO();
		validate(optto);
		return daoOpt.create(optto);

	}

	@Override
	public OptionalTO modificaOptional(OptionalTO optto)
			throws DatabaseException, ValidationException {

		OptionalDAO daoOpt = getOptionalDAO();
		validate(optto);

		return daoOpt.update(optto);

	}

	@Override
	public OptionalTO disattivaOptional(OptionalTO optto) 
			throws DatabaseException, ValidationException {

		OptionalDAO daoOpt = getOptionalDAO();
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		StatoOptionalDAO daoStatoOpt = dbDAOFact.getStatoOptionalDAO();
		StatoOptionalTO stato = daoStatoOpt.getStatoDisattivo();
		optto.setStatoOptional(stato);
		validate(optto);

		return daoOpt.disattivaOptional(optto);
	}

	@Override
	public OptionalTO checkOptCmpAttive(OptionalTO optto) throws DatabaseException, OptionalCmpAttiveException{
		CompetizioneDAO daoCmp = this.dbFact.getDAOFactory(this.sysConf.getTipoDB()).getCompetizioneDAO();
		for(CompetizioneTO cmpTO: daoCmp.readCompetizioniAttive()){
			for(OptionalTO checkOpt: cmpTO.getAllOptionals()){
				if(checkOpt.getId() == optto.getId()){
					throw new OptionalCmpAttiveException();
				}
			}
		}
		return optto;
	}

	@Override
	public List<OptionalTO> getOptionalAttiviByTipo(TipoOptionalTO toptTO) throws DatabaseException{

		List<OptionalTO> res = getOptionalDAO().getOptionalAttiviByTipo(toptTO);

		return res;
	}

	@Override
	public List<OptionalTO> getOptionalByTipo(TipoOptionalTO toptTO)
			throws DatabaseException {

		List<OptionalTO> res = getOptionalDAO().getOptionalByTipo(toptTO);

		return res;
	}

	@Override
	public List<OptionalTO> getAllOptional() throws DatabaseException {
		OptionalDAO daoOpt = getOptionalDAO();
		return daoOpt.getAll();
	}
}