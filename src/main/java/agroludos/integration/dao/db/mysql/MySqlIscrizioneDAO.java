package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Session;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.to.IscrizioneTO;

class MySqlIscrizioneDAO extends MySqlAgroludosDAO<IscrizioneTO> implements IscrizioneDAO{

	MySqlIscrizioneDAO(Session session) {
		super(session);
		this.setClasse(IscrizioneTO.class);
	}

	@Override
	public List< IscrizioneTO > getAll() throws DatabaseException{
		List< IscrizioneTO > res = super.getAll();
		return res;
	}

	@Override
	public IscrizioneTO annullaIscrizione(IscrizioneTO iscto) throws DatabaseException {
		
		super.update(iscto);
		this.session.refresh(iscto.getCompetizione().getManagerDiCompetizione());
		return iscto;

	}

}