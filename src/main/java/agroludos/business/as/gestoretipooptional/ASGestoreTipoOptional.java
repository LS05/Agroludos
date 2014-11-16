package agroludos.business.as.gestoretipooptional;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.business.TipoOptExistException;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.to.TipoOptionalTO;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore tipi Optional.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare il funzionamento
 * dei servizi andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * Il gestore utilizza una serie di {@link AgroludosTO} (Transfer Object o Data Transfer Object
 * la cui tipologia dipende dal servizio) e sfrutta il {@link TipoOptionalDAO} 
 * (Data Access Object) per occuparsi della persistenza di tali oggetti.</br>
 * Il Gestore tipi Optional espone la logica di business riguardante i tipi di optional 
 * tramite due interfacce. L'interfaccia {@link LTipoOptional} fornisce i servizi 
 * di lettura, mentre l'interfaccia {@link STipoOptional} offre i servizi di scrittura.</br>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ASGestoreTipoOptional extends AgroludosAS implements LTipoOptional, STipoOptional{
	private AgroludosValidator validator;

	/**
	 * Il costruttore inizializza la varabile validator
	 * @param validator
	 */
	ASGestoreTipoOptional(AgroludosValidator validator){
		this.validator = validator;
	}

	/**
	 * 
	 * @return un'istanza di {@link TipoOptionalDAO}
	 * @throws DatabaseException
	 */
	private TipoOptionalDAO getTipoOptionalDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoOptionalDAO();
	}

	/**
	 * Il metodo dopo aver vaidato il tipo di optional in input e controllato che non esistesse già
	 * (sollevando {@link TipoOptExistException}) inserisce nella sorgente dati il tipo di optional
	 * tramite {@link TipoOptionalDAO}
	 */
	@Override
	public TipoOptionalTO inserisciTipoOptional(TipoOptionalTO tipoOptTO)
			throws DatabaseException, ValidationException {

		TipoOptionalDAO daoTop = getTipoOptionalDAO();
		this.validator.validate(tipoOptTO);
		List<TipoOptionalTO> listTopt = daoTop.getAll();

		for(TipoOptionalTO topt: listTopt){
			if(topt.getNome().compareTo(tipoOptTO.getNome())==0){
				throw new TipoOptExistException();
			}
		}

		return daoTop.create(tipoOptTO);
	}

	@Override
	public List<TipoOptionalTO> getAllTipoOptional() throws DatabaseException {
		TipoOptionalDAO daoTop = getTipoOptionalDAO();
		return daoTop.getAll();
	}
}