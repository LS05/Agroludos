package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.PartecipanteTO;

public interface IscrizioneDAO extends DAO<IscrizioneTO>{
	
	boolean esisteIscrizione(IscrizioneTO iscto) throws DatabaseException;
	
	IscrizioneTO annullaIscrizione(IscrizioneTO iscto) throws DatabaseException;

	List<IscrizioneTO> getAllIscrizioniAttive(PartecipanteTO parTO) throws DatabaseException;

	List<IscrizioneTO> getIscrizioniAttiveCmp(CompetizioneTO cmp) throws DatabaseException;

	List<IscrizioneTO> getAllIscrizioniPartecipante(PartecipanteTO parTO) throws DatabaseException;

	void terminaIscrizioni(CompetizioneTO cmp) throws DatabaseException;

}
