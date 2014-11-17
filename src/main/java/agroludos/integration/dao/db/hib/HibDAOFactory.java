package agroludos.integration.dao.db.hib;

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

class HibDAOFactory implements DBDAOFactory {

	HibDAOFactory() throws DatabaseException{
		HibDAOUtil.buildSessionFactory();
	}
	
	@Override
	public ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO() {
		return new HibManagerDiCompetizioneDAO();
	}

	@Override
	public ManagerDiSistemaDAO getManagerDiSistemaDAO() {
		return new HibManagerDiSistemaDAO();
	}

	@Override
	public CompetizioneDAO getCompetizioneDAO() {
		return new HibCompetizioneDAO();
	}

	@Override
	public PartecipanteDAO getPartecipanteDAO() {
		return new HibPartecipanteDAO();
	}

	@Override
	public TipoCompetizioneDAO getTipoCompetizioneDAO() {
		return new HibTipoCompetizioneDAO();
	}

	@Override
	public TipoOptionalDAO getTipoOptionalDAO() {
		return new HibTipoOptionalDAO();
	}

	@Override
	public OptionalDAO getOptionalDAO() {
		return new HibOptionalDAO();
	}

	@Override
	public UtenteDAO<UtenteTO> getUtenteDAO() {
		return new HibUtenteDAO<UtenteTO>();
	}

	@Override
	public IscrizioneDAO getIscrizioneDAO() {
		return new HibIscrizioneDAO();
	}

	@Override
	public StatoCompetizioneDAO getStatoCompetizioneDAO() {
		return new HibStatoCompetizioneDAO();
	}

	@Override
	public StatoIscrizioneDAO getStatoIscrizioneDAO() {
		return new HibStatoIscrizioneDAO();
	}

	@Override
	public StatoOptionalDAO getStatoOptionalDAO() {
		return new HibStatoOptionalDAO();
	}

	@Override
	public StatoUtenteDAO getStatoUtenteDAO() {
		return new HibStatoUtenteDAO();
	}

	@Override
	public TipoUtenteDAO getTipoUtenteDAO() {
		return new HibTipoUtenteDAO();
	}
}