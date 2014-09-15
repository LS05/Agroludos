package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.to.ManagerDiCompetizioneTO;

class MySqlManagerDiCompetizioneDAO extends MySqlUtenteDAO implements ManagerDiCompetizioneDAO{
	
	MySqlManagerDiCompetizioneDAO(SessionFactory sessionFactory){
		super(sessionFactory);
	}

	@Override
	public ManagerDiCompetizioneTO getByUsername(String username) throws DatabaseException {
		return (ManagerDiCompetizioneTO) super.getByUsername(username);
	}

	@Override
	public ManagerDiCompetizioneTO getByID(Integer id) throws DatabaseException {
		return (ManagerDiCompetizioneTO) super.getByID(id);
	}

	@Override
	public boolean crea(ManagerDiCompetizioneTO mdcto) throws DatabaseException {
		return super.crea(mdcto);
	}

	@Override
	public boolean update(ManagerDiCompetizioneTO mdcto) {
		return this.update(mdcto);
	}

	@Override
	public boolean delete(ManagerDiCompetizioneTO mdcto) {
		mdcto.setStato(0);
		return this.update(mdcto);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ManagerDiCompetizioneTO> readAll() throws DatabaseException {
		List<?> list = super.executeQuery("getAllManagerDiCompetizione");
		 List<ManagerDiCompetizioneTO> res = (List<ManagerDiCompetizioneTO>)list;
		
		int index=0;
		for(Object cmp: list){
			this.setNomeRuolo(res.get(index));
			this.setNomeStatoUtente(res.get(index));
			index++;
		}
		return res;
	}
}