package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.PartecipanteTO;

class MySqlIscrizioneDAO extends MySqlAgroludosDAO<IscrizioneTO> implements IscrizioneDAO{

	MySqlIscrizioneDAO() {
		this.setClasse(toFact.createIscrizioneTO());
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

	@Override
	public List<IscrizioneTO> getAllIscrizioniAttive(PartecipanteTO parTO) throws DatabaseException {
		List<PartecipanteTO> param = new ArrayList<PartecipanteTO>();
		param.add(parTO);

		List<IscrizioneTO> res = super.executeParamQuery("getAllIscrizioniAttive", param);

		return res;
	}


	@Override
	public List<IscrizioneTO> getIscrizioniAttiveCmp(CompetizioneTO cmp) throws DatabaseException {
		List<CompetizioneTO> param = new ArrayList<CompetizioneTO>();
		param.add(cmp);

		List<IscrizioneTO> res = super.executeParamQuery("getIscrizioniAttiveCmp", param);

		return res;
	}

	@Override
	public List<IscrizioneTO> getAllIscrizioniPartecipante(PartecipanteTO parTO) throws DatabaseException {
		List<PartecipanteTO> param = new ArrayList<PartecipanteTO>();
		param.add(parTO);

		List<IscrizioneTO> res = super.executeParamQuery("getAllIscrizioniPartecipante", param);

		return res;
	}

}