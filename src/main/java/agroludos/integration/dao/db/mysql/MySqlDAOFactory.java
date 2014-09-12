package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.integration.dao.db.DBConfigurazioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.integration.dao.db.OptionalDAO;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.integration.dao.db.TipoCompetizioneDAO;
import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.integration.dao.db.UtenteDAO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.TransferObjectFactory;

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
	public UtenteDAO getUtenteDAO() {
		return new MySqlUtenteDAO();
	}

	@Override
	public DBConfigurazioneDAO getConfigurazioneDAO() {
		return new MySqlConfigurazioneDAO();
	}

	@Override
	public IscrizioneDAO getIscrizioneDAO() {
		return new MySqlIscrizioneDAO();
	}

	public static void main(String args[]){
		TransferObjectFactory fact = new TransferObjectFactory();
		IscrizioneTO iscTO = fact.createIscrizioneTO();
		MySqlDAOFactory daoFact = new MySqlDAOFactory();
		daoFact.initialize();
		
		ManagerDiCompetizioneDAO mdcDAO = daoFact.getManagerDiCompetizioneDAO();
		List<ManagerDiCompetizioneTO> mdcto = mdcDAO.readAll();
		
		CompetizioneDAO dao = daoFact.getCompetizioneDAO();
		CompetizioneTO cmpto = dao.readById(65);
//		PartecipanteDAO parDAO = daoFact.getPartecipanteDAO();
//		PartecipanteTO listPart = parDAO.readByID(62);
//		IscrizioneDAO iscDAO = daoFact.getIscrizioneDAO();
//		List<IscrizioneTO> list = iscDAO.getAllIscrizioni();
//		IscrizioneTO iscr = list.get(0);
//		System.out.println(iscr.getPartecipanteIscrizione().getSrc());
//		PartecipanteTO part = iscr.getPartecipanteIscrizione();
	}
}