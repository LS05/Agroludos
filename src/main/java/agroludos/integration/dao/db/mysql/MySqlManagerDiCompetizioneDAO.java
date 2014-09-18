package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.to.ManagerDiCompetizioneTO;

class MySqlManagerDiCompetizioneDAO extends MySqlUtenteDAO<ManagerDiCompetizioneTO> implements ManagerDiCompetizioneDAO{
	
	MySqlManagerDiCompetizioneDAO(Session session){
		super(session);
		this.setClasse(ManagerDiCompetizioneTO.class);
	}
	
	@Override
	public List< ManagerDiCompetizioneTO > getAll() throws DatabaseException{
		List< ManagerDiCompetizioneTO > res = super.getAll();

		for(ManagerDiCompetizioneTO mdc : res){
			this.setNomeStatoUtente(mdc);
		}

		return res;
	}

	@Override
	public ManagerDiCompetizioneTO getByStipendio(Integer id) throws DatabaseException {
		return null;
	}
}