package agroludos.integration.dao.db.mysql;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.to.IscrizioneTO;

class MySqlIscrizioneDAO extends MySqlAgroludosDAO<IscrizioneTO> implements IscrizioneDAO{

	MySqlIscrizioneDAO() {
		this.setClasse(IscrizioneTO.class);
	}

	@Override
	public List< IscrizioneTO > getAll() throws DatabaseException{
		List< IscrizioneTO > res = super.getAll();
		return res;
	}

	@Override
	public IscrizioneTO annullaIscrizione(IscrizioneTO iscTO) throws DatabaseException {

		super.update(iscTO);
		return iscTO;

	}

	//TODO Da rivedere il confronto con null
	@Override
	public boolean esisteIscrizione(IscrizioneTO iscTO) throws DatabaseException {
		IscrizioneTO iscrizione = this.findOne(iscTO.getId());
		return iscrizione != null;
	}

}