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

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore degli Optional.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare il funzionamento
 * dei servizi andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * Il gestore utilizza una serie di {@link AgroludosTO} (Transfer Object o Data Transfer Object
 * la cui tipologia dipende dal servizio) e sfrutta il {@link OptionalDAO} 
 * (Data Access Object) per occuparsi della persistenza di tali oggetti.</br>
 * Il Gestore degli Optional espone la logica di business riguardante gli Optional 
 * tramite due interfacce. L'interfaccia {@link LOptional} fornisce i servizi 
 * di lettura, mentre l'interfaccia {@link SOptional} offre i servizi di scrittura.</br>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ASGestoreOptional extends AgroludosAS implements LOptional, SOptional{

	private AgroludosValidator validator;

	/**
	 * Il costruttore inizializza la variabile validator
	 * @param validator
	 */
	ASGestoreOptional(AgroludosValidator validator){
		this.validator = validator;
	}

	/**
	 * Il metodo restituisce un'istanza di {@link OptionalDAO}
	 * @return {@link OptionalDAO}
	 * @throws DatabaseException
	 */
	private OptionalDAO getOptionalDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getOptionalDAO();
	}

	/**
	 * Il metodo effettua la validazione dell'optional in input
	 * @param optTO
	 * @throws ValidationException
	 */
	private void validate(OptionalTO optTO) throws ValidationException{
		this.validator.validate(optTO);
	}

	/**
	 * Il metodo dopo aver validato l'optiona in input lo inserisce nella sorgente dati tramite
	 * il DAO {@link OptionalDAO}
	 */
	@Override
	public OptionalTO inserisciOptional(OptionalTO optto) 
			throws DatabaseException, ValidationException {

		OptionalDAO daoOpt = getOptionalDAO();
		validate(optto);
		return daoOpt.create(optto);

	}

	/**
	 * Il metodo dopo aver validato l'optiona in input inserisce le modifiche nella sorgente dati tramite
	 * il DAO {@link OptionalDAO}
	 */
	@Override
	public OptionalTO modificaOptional(OptionalTO optto)
			throws DatabaseException, ValidationException {

		OptionalDAO daoOpt = getOptionalDAO();
		validate(optto);

		return daoOpt.update(optto);

	}

	/**
	 * Il metodo disattiva l'optional in input cambiando il suo stato in "disattivo" 
	 * tramite i DAO {@link OptionalDAO} e {@link StatoOptionalDAO}
	 */
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

	/**
	 * Il metodo controlla che se un optional fa parte di competizioni attive, se si solleva l'eccezione
	 * {@link OptionalCmpAttiveException} altrimenti restituisce l'optional in input
	 */
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

	/**
	 * Il metodo restituisce una lista di optional attivi in base al tipo degli optional preso in input.
	 * Utilizza il DAO {@link OptionalDAO}
	 */
	@Override
	public List<OptionalTO> getOptionalAttiviByTipo(TipoOptionalTO toptTO) throws DatabaseException{

		List<OptionalTO> res = getOptionalDAO().getOptionalAttiviByTipo(toptTO);

		return res;
	}

	/**
	 * Il metodo restituisce tutti gli optional (sia attivi che disattivi) di uno specificato tipo preso 
	 * in input
	 */
	@Override
	public List<OptionalTO> getOptionalByTipo(TipoOptionalTO toptTO)
			throws DatabaseException {

		List<OptionalTO> res = getOptionalDAO().getOptionalByTipo(toptTO);

		return res;
	}

	/**
	 * Il metodo restituisce una lisa con tutti gli optional trovati nella sorgente dati tramite il
	 * DAO {@link OptionalDAO}
	 */
	@Override
	public List<OptionalTO> getAllOptional() throws DatabaseException {
		OptionalDAO daoOpt = getOptionalDAO();
		return daoOpt.getAll();
	}
}