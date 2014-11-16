package agroludos.business.as.gestorestatooptional;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.StatoOptionalDAO;
import agroludos.to.StatoOptionalTO;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore Stati degli Optional</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare le operazioni
 * sugli stati degli optional attraverso il DAO StatoOptionalDAO
 * Il gestore utilizza una serie di {@link AgroludosTO} (Transfer Object o Data Transfer Object
 * la cui tipologia dipende dal servizio) e sfrutta il {@link StatoOptionalDAO} 
 * (Data Access Object) per occuparsi della persistenza di tali oggetti.</br>
 * Il Gestore degli Stati di un Optional espone la logica di business riguardante gli stati
 * tramite due interfacce. L'interfaccia {@link LStatoOptional} fornisce i servizi 
 * di lettura, mentre l'interfaccia {@link SStatoOptional} offre i servizi di scrittura.</br>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see agroludos.integration.dao.db.StatoOptionalDAO
 */
public class ASGestoreStatoOptional extends AgroludosAS implements LStatoOptional{

	@Override
	public List<StatoOptionalTO> getAllStatoOptional() throws DatabaseException {
		StatoOptionalDAO daoTcm = getStatoOptionalDAO();
		return daoTcm.getAll();
	}

	/**
	 * Il metodo restituisce un'istanza di StatoOptionalDAO utilizzando il DAO DBDAOFactory
	 * @return
	 * @throws DatabaseException
	 * @see agroludos.integration.dao.db.StatoOptionalDAO
	 * @see agroludos.integration.dao.db.DBDAOFactory
	 */
	private StatoOptionalDAO getStatoOptionalDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getStatoOptionalDAO();
	}

}
