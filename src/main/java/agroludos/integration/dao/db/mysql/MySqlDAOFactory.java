package agroludos.integration.dao.db.mysql;

import java.util.List;

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

	private static SessionFactory sessionFactory;

	public boolean initialize() throws DatabaseException{
		boolean res = false;
		sessionFactory = MySqlDAO.buildSessionFactory();
		if(sessionFactory != null)
			res = true;
		return res;
	}

	@Override
	public ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO() {
		return new MySqlManagerDiCompetizioneDAO(sessionFactory);
	}

	@Override
	public ManagerDiSistemaDAO getManagerDiSistemaDAO() {
		return new MySqlManagerDiSistemaDAO(sessionFactory);
	}

	@Override
	public CompetizioneDAO getCompetizioneDAO() {
		return new MySqlCompetizioneDAO(sessionFactory);
	}

	@Override
	public PartecipanteDAO getPartecipanteDAO() {
		return new MySqlPartecipanteDAO(sessionFactory);
	}

	@Override
	public TipoCompetizioneDAO getTipoCompetizioneDAO() {
		return new MySqlTipoCompetizioneDAO(sessionFactory);
	}

	@Override
	public TipoOptionalDAO getTipoOptionalDAO() {
		return new MySqlTipoOptionalDAO(sessionFactory);
	}

	@Override
	public OptionalDAO getOptionalDAO() {
		return new MySqlOptionalDAO(sessionFactory);
	}

	@Override
	public UtenteDAO<UtenteTO> getUtenteDAO() {
		return new MySqlUtenteDAO<UtenteTO>(sessionFactory);
	}

	@Override
	public IscrizioneDAO getIscrizioneDAO() {
		return new MySqlIscrizioneDAO(sessionFactory);
	}

	public static void main(String args[]){
		TransferObjectFactory fact = new TransferObjectFactory();
		MySqlDAOFactory daoFact = new MySqlDAOFactory();
		try {
			daoFact.initialize();
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