package agroludos.business.as.gestorestatoutente;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.StatoUtenteDAO;
import agroludos.to.StatoUtenteTO;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore stati Utente.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare il funzionamento
 * dei servizi andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * Il gestore utilizza una serie di {@link AgroludosTO} (Transfer Object o Data Transfer Object
 * la cui tipologia dipende dal servizio) e sfrutta il {@link StatoUtenteDAO} 
 * (Data Access Object) per occuparsi della persistenza di tali oggetti.</br>
 * Il Gestore stati Utente espone la logica di business riguardante gli stati di un utente 
 * tramite l'interfaccia {@link LStatoUtente} che fornisce i servizi 
 * di lettura.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public class ASGestoreStatoUtente extends AgroludosAS implements LStatoUtente{

	@Override
	public List<StatoUtenteTO> getAllStatoUtente() throws DatabaseException {
		StatoUtenteDAO daoTcm = getStatoUtenteDAO();
		return daoTcm.getAll();
	}

	/**
	 * 
	 * @return un'istanza di {@link StatoUtenteDAO}
	 * @throws DatabaseException
	 */
	private StatoUtenteDAO getStatoUtenteDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getStatoUtenteDAO();
	}

}
