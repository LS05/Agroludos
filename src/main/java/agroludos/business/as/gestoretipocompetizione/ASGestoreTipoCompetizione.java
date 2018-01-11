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

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore tipi di Competizione.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare il funzionamento
 * dei servizi andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * Il gestore utilizza una serie di {@link AgroludosTO} (Transfer Object o Data Transfer Object
 * la cui tipologia dipende dal servizio) e sfrutta il {@link TipoCompetizioneDAO} 
 * (Data Access Object) per occuparsi della persistenza di tali oggetti.</br>
 * Il Gestore tipo della Competizione espone la logica di business riguardante i tipi delle competizioni 
 * tramite due interfacce. L'interfaccia {@link LTipoCompetizione} fornisce i servizi 
 * di lettura, mentre l'interfaccia {@link STipoCompetizione} offre i servizi di scrittura.</br>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ASGestoreTipoCompetizione extends AgroludosAS implements LTipoCompetizione, STipoCompetizione{

	private AgroludosValidator validator;

	/**
	 * Il costruttore inizilizza la variabile validator
	 * @param validator
	 */
	ASGestoreTipoCompetizione(AgroludosValidator validator){
		this.validator = validator;
	}

	/**
	 * 
	 * @return un'istanza di {@link TipoCompetizioneDAO}
	 * @throws DatabaseException
	 */
	private TipoCompetizioneDAO getTipoCompetizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoCompetizioneDAO();
	}

	/**
	 * Il metodo dopo aver vaidato il tipo competizione in input e controllato che non esistesse già
	 * (sollevando {@link TipoCmpExistException}) inserisce nella sorgente dati il tipo di competizione
	 * tramite {@link TipoCompetizioneDAO}
	 */
	@Override
	public TipoCompetizioneTO inserisciTipoCompetizione(TipoCompetizioneTO tcmto)
			throws DatabaseException, ValidationException {

		TipoCompetizioneDAO daoTcm = getTipoCompetizioneDAO();

		this.validator.validate(tcmto);
		List<TipoCompetizioneTO> listTcmp = daoTcm.getAll();

		for(TipoCompetizioneTO tcmp: listTcmp){
			if(tcmp.getNome().compareTo(tcmto.getNome())==0){
				throw new TipoCmpExistException();
			}
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