package agroludos.integration.dao.db.mysql;

import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.integration.dao.db.OptionalDAO;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.integration.dao.db.StatoCompetizioneDAO;
import agroludos.integration.dao.db.StatoIscrizioneDAO;
import agroludos.integration.dao.db.StatoOptionalDAO;
import agroludos.integration.dao.db.StatoUtenteDAO;
import agroludos.integration.dao.db.TipoCompetizioneDAO;
import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.integration.dao.db.TipoUtenteDAO;
import agroludos.integration.dao.db.UtenteDAO;
import agroludos.to.UtenteTO;

public class MySqlDAOFactory implements DBDAOFactory {

	
	MySqlDAOFactory() throws DatabaseException{
		MySqlDAOUtil.buildSessionFactory();
	}
	
	@Override
	public ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO() {
		return new MySqlManagerDiCompetizioneDAO();
	}

	@Override
	public ManagerDiSistemaDAO getManagerDiSistemaDAO() {
		return new MySqlManagerDiSistemaDAO();
	}

	@Override
	public CompetizioneDAO getCompetizioneDAO() {
		return new MySqlCompetizioneDAO();
	}

	@Override
	public PartecipanteDAO getPartecipanteDAO() {
		return new MySqlPartecipanteDAO();
	}

	@Override
	public TipoCompetizioneDAO getTipoCompetizioneDAO() {
		return new MySqlTipoCompetizioneDAO();
	}

	@Override
	public TipoOptionalDAO getTipoOptionalDAO() {
		return new MySqlTipoOptionalDAO();
	}

	@Override
	public OptionalDAO getOptionalDAO() {
		return new MySqlOptionalDAO();
	}

	@Override
	public UtenteDAO<UtenteTO> getUtenteDAO() {
		return new MySqlUtenteDAO<UtenteTO>();
	}

	@Override
	public IscrizioneDAO getIscrizioneDAO() {
		return new MySqlIscrizioneDAO();
	}

	@Override
	public StatoCompetizioneDAO getStatoCompetizioneDAO() {
		return new MySqlStatoCompetizioneDAO();
	}

	@Override
	public StatoIscrizioneDAO getStatoIscrizioneDAO() {
		return new MySqlStatoIscrizioneDAO();
	}

	@Override
	public StatoOptionalDAO getStatoOptionalDAO() {
		return new MySqlStatoOptionalDAO();
	}

	@Override
	public StatoUtenteDAO getStatoUtenteDAO() {
		return new MySqlStatoUtenteDAO();
	}

	@Override
	public TipoUtenteDAO getTipoUtenteDAO() {
		return new MySqlTipoUtenteDAO();
	}
}