package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import agroludos.exceptions.DatabaseException;
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
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TipoUtenteTO;
import agroludos.to.TransferObjectFactory;
import agroludos.to.UtenteTO;

public class MySqlDAOFactory implements DBDAOFactory {

	private MySqlDAOUtil daoUtil;

	private Session session;

	public MySqlDAOFactory(){ }

	public MySqlDAOFactory(MySqlDAOUtil daoUtil) throws DatabaseException{ 
		this.daoUtil = daoUtil;
		this.session = this.daoUtil.buildSessionFactory().openSession();
	}

	public boolean testConnection() throws DatabaseException{
		boolean res = false;

		try{
			res = this.session.isConnected() && this.session.isOpen();
		}catch(HibernateException e){
			throw new DatabaseException(e.getMessage());
		}

		return res;
	}

	@Override
	public ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO() {
		return new MySqlManagerDiCompetizioneDAO(this.session);
	}

	@Override
	public ManagerDiSistemaDAO getManagerDiSistemaDAO() {
		return new MySqlManagerDiSistemaDAO(this.session);
	}

	@Override
	public CompetizioneDAO getCompetizioneDAO() {
		return new MySqlCompetizioneDAO(this.session);
	}

	@Override
	public PartecipanteDAO getPartecipanteDAO() {
		return new MySqlPartecipanteDAO(this.session);
	}

	@Override
	public TipoCompetizioneDAO getTipoCompetizioneDAO() {
		return new MySqlTipoCompetizioneDAO(this.session);
	}

	@Override
	public TipoOptionalDAO getTipoOptionalDAO() {
		return new MySqlTipoOptionalDAO(this.session);
	}

	@Override
	public OptionalDAO getOptionalDAO() {
		return new MySqlOptionalDAO(this.session);
	}

	@Override
	public UtenteDAO<UtenteTO> getUtenteDAO() {
		return new MySqlUtenteDAO<UtenteTO>(this.session);
	}

	@Override
	public IscrizioneDAO getIscrizioneDAO() {
		return new MySqlIscrizioneDAO(this.session);
	}

	@Override
	public StatoCompetizioneDAO getStatoCompetizioneDAO() {
		return new MySqlStatoCompetizioneDAO(this.session);
	}

	@Override
	public StatoIscrizioneDAO getStatoIscrizioneDAO() {
		return new MySqlStatoIscrizioneDAO(this.session);
	}

	@Override
	public StatoOptionalDAO getStatoOptionalDAO() {
		return new MySqlStatoOptionalDAO(this.session);
	}

	@Override
	public StatoUtenteDAO getStatoUtenteDAO() {
		return new MySqlStatoUtenteDAO(this.session);
	}

	@Override
	public TipoUtenteDAO getTipoUtenteDAO() {
		return new MySqlTipoUtenteDAO(this.session);
	}

	public static void main(String args[]){
		TransferObjectFactory fact = new TransferObjectFactory();
		MySqlDAOUtil daoUtil = new MySqlDAO();


		try {
			MySqlDAOFactory daoFact = new MySqlDAOFactory(daoUtil);
			daoFact.testConnection();
			TipoUtenteDAO cmpDao = daoFact.getTipoUtenteDAO();
			List<TipoUtenteTO> tuto = cmpDao.getAll();
			tuto.toString();
			
		} catch (DatabaseException e) {
			e.printStackTrace();
		}	
	}


}