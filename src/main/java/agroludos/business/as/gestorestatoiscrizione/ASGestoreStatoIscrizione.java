package agroludos.business.as.gestorestatoiscrizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.StatoIscrizioneDAO;
import agroludos.to.StatoIscrizioneTO;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore stati di Iscrizione.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare il funzionamento
 * dei servizi andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * Il gestore utilizza una serie di {@link AgroludosTO} (Transfer Object o Data Transfer Object
 * la cui tipologia dipende dal servizio) e sfrutta il {@link StatoIscrizioneDAO} 
 * (Data Access Object) per occuparsi della persistenza di tali oggetti.</br>
 * Il Gestore stati di Iscrizione espone la logica di business riguardante gli stati di una iscrizione 
 * tramite l'interfaccia {@link LStatoIscrizione} che fornisce i servizi 
 * di lettura.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public class ASGestoreStatoIscrizione extends AgroludosAS implements LStatoIscrizione{

	@Override
	public List<StatoIscrizioneTO> getAllStatoIscrizione() throws DatabaseException {
		StatoIscrizioneDAO daoTcm = getStatoIscrizioneDAO();
		return daoTcm.getAll();
	}

	/**
	 * 
	 * @return un'istanza di {@link StatoIscrizioneDAO}
	 * @throws DatabaseException
	 */
	private StatoIscrizioneDAO getStatoIscrizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getStatoIscrizioneDAO();
	}

	@Override
	public StatoIscrizioneTO getStatoAttivo() throws DatabaseException {
		StatoIscrizioneDAO iscDao = getStatoIscrizioneDAO();
		return iscDao.getStatoAttivo();
	}

}