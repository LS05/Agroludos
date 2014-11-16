package agroludos.business.as.gestorestatocompetizione;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.StatoCompetizioneDAO;
import agroludos.to.StatoCompetizioneTO;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore Stati di Competizione.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare il funzionamento
 * dei servizi andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * Il gestore utilizza una serie di {@link AgroludosTO} (Transfer Object o Data Transfer Object
 * la cui tipologia dipende dal servizio) e sfrutta il {@link StatoCompetizioneDAO} 
 * (Data Access Object) per occuparsi della persistenza di tali oggetti.</br>
 * Il Gestore degli stati di una competizione espone la logica di business riguardante gli stati di 
 * una competizione  
 * tramite l'interfaccia {@link LStatoCompetizione} che fornisce i servizi 
 * di lettura.
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public class ASGestoreStatoCompetizione extends AgroludosAS implements LStatoCompetizione{

	@Override
	public List<StatoCompetizioneTO> getAllStatoCompetizione()
			throws DatabaseException {
		StatoCompetizioneDAO daoTcm = getStatoCompetizioneDAO();
		return daoTcm.getAll();
	}

	/**
	 * 
	 * @return un'istanza di {@link StatoCompetizioneDAO}
	 * @throws DatabaseException
	 */
	private StatoCompetizioneDAO getStatoCompetizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getStatoCompetizioneDAO();
	}

	@Override
	public StatoCompetizioneTO getStatoCmpAnnullata() throws DatabaseException {
		StatoCompetizioneDAO daoTcm = getStatoCompetizioneDAO();
		return daoTcm.getStatoCmpAnnullata();
	}
}
