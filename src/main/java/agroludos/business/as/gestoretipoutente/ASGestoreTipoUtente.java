package agroludos.business.as.gestoretipoutente;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.TipoUtenteDAO;
import agroludos.to.TipoUtenteTO;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore tipi Utente.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare il funzionamento
 * dei servizi andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * Il gestore utilizza una serie di {@link AgroludosTO} (Transfer Object o Data Transfer Object
 * la cui tipologia dipende dal servizio) e sfrutta il {@link TipoUtenteDAO} 
 * (Data Access Object) per occuparsi della persistenza di tali oggetti.</br>
 * Il Gestore tipi Utente espone la logica di business riguardante i tipi di utente
 * tramite l'interfaccia {@link LTipoUtente}che fornisce i servizi 
 * di lettura.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ASGestoreTipoUtente extends AgroludosAS implements LTipoUtente{
	
	/**
	 * 
	 * @return un'istanza di {@link TipoUtenteDAO}
	 * @throws DatabaseException
	 */
	private TipoUtenteDAO getTipoUtenteDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoUtenteDAO();
	}

	@Override
	public List<TipoUtenteTO> getAllTipoUtente()
			throws DatabaseException {
		TipoUtenteDAO daoTUt = getTipoUtenteDAO();
		return daoTUt.getAll();
	}
}