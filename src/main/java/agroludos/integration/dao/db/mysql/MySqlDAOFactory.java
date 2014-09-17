package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.integration.dao.db.OptionalDAO;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.integration.dao.db.TipoCompetizioneDAO;
import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.integration.dao.db.UtenteDAO;
import agroludos.to.IscrizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TransferObjectFactory;
import agroludos.to.UtenteTO;

public class MySqlDAOFactory implements DBDAOFactory {

	private SessionFactory sessionFactory;
	
	private MySqlDAOUtil daoUtil;
	
	public MySqlDAOFactory(){ }
	
	public MySqlDAOFactory(MySqlDAOUtil daoUtil) throws DatabaseException{ 
		this.daoUtil = daoUtil;
		this.sessionFactory = this.daoUtil.buildSessionFactory();
	}

	public boolean testConnection() throws DatabaseException{
		boolean res = false;
		
		try{
			this.sessionFactory.openSession();
			res = true;
		}catch(HibernateException e){
			throw new DatabaseException(e.getMessage());
		}
			
		return res;
	}

	@Override
	public ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO() {
		return new MySqlManagerDiCompetizioneDAO(this.sessionFactory);
	}

	@Override
	public ManagerDiSistemaDAO getManagerDiSistemaDAO() {
		return new MySqlManagerDiSistemaDAO(this.sessionFactory);
	}

	@Override
	public CompetizioneDAO getCompetizioneDAO() {
		return new MySqlCompetizioneDAO(this.sessionFactory);
	}

	@Override
	public PartecipanteDAO getPartecipanteDAO() {
		return new MySqlPartecipanteDAO(this.sessionFactory);
	}

	@Override
	public TipoCompetizioneDAO getTipoCompetizioneDAO() {
		return new MySqlTipoCompetizioneDAO(this.sessionFactory);
	}

	@Override
	public TipoOptionalDAO getTipoOptionalDAO() {
		return new MySqlTipoOptionalDAO(this.sessionFactory);
	}

	@Override
	public OptionalDAO getOptionalDAO() {
		return new MySqlOptionalDAO(this.sessionFactory);
	}

	@Override
	public UtenteDAO<UtenteTO> getUtenteDAO() {
		return new MySqlUtenteDAO<UtenteTO>(this.sessionFactory);
	}

	@Override
	public IscrizioneDAO getIscrizioneDAO() {
		return new MySqlIscrizioneDAO(this.sessionFactory);
	}

	public static void main(String args[]){
		TransferObjectFactory fact = new TransferObjectFactory();
		MySqlDAOFactory daoFact = new MySqlDAOFactory();
		try {
			daoFact.testConnection();
			IscrizioneTO cmpto = fact.createIscrizioneTO();
			ManagerDiCompetizioneDAO cmpDAO = daoFact.getManagerDiCompetizioneDAO();

			List<ManagerDiCompetizioneTO> list = cmpDAO.getAll();
			int i=0;
			for(Object lst: list){
				System.out.println(list.get(i).getId() + " " + list.get(i).getNomeStatoUtente());
				i++;
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}	
	}
}