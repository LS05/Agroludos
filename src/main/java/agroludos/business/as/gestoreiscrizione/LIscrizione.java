package agroludos.business.as.gestoreiscrizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.PartecipanteTO;


public interface LIscrizione extends AgroludosService {
	List<IscrizioneTO> getAllIscrizione() throws DatabaseException;
	List<IscrizioneTO> getAllIscrizioniAttive(PartecipanteTO parTO)
			throws DatabaseException;
	List<IscrizioneTO> getAllIscrizioniAttiveByCmp(CompetizioneTO cmpTO)
			throws DatabaseException;
	List<IscrizioneTO> getAllIscrizioniPartecipante(PartecipanteTO parTO)
			throws DatabaseException;
	IscrizioneTO esisteIscrizione(IscrizioneTO iscTO) throws DatabaseException;
}
