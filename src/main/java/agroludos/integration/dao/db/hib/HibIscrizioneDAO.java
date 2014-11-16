package agroludos.integration.dao.db.hib;

import java.util.ArrayList;
import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.StatoIscrizioneTO;

class HibIscrizioneDAO extends HibAgroludosDAO<IscrizioneTO> implements IscrizioneDAO{

	HibIscrizioneDAO() {
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
	
	private StatoIscrizioneTO getStatoIscrizioneTerminato() throws DatabaseException {

		StatoIscrizioneTO siscTO = (StatoIscrizioneTO) super.executeQuery("getStatoIscrizioneTerminato");

		return siscTO;
	}

	@Override
	public void terminaIscrizioni(CompetizioneTO cmp) throws DatabaseException {
		List<IscrizioneTO> res = getIscrizioniAttiveCmp(cmp);

		for(IscrizioneTO isc: res){
			isc.setStatoIscrizione(getStatoIscrizioneTerminato());
			this.update(isc);
		}

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