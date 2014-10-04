package agroludos.business.as.gestorepartecipante;

import java.io.IOException;
import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.UserNotFoundException;
import agroludos.to.PartecipanteTO;

public interface LPartecipante extends AgroludosService {
	
	PartecipanteTO getPartecipante(PartecipanteTO parto) throws DatabaseException, IOException, UserNotFoundException;
	
	List<PartecipanteTO> getAllPartecipante() throws DatabaseException, IOException;
	
	PartecipanteTO getPartecipanteById(PartecipanteTO parto) throws DatabaseException, UserNotFoundException, IOException;
	String getCertificatoSRC(PartecipanteTO parto)throws DatabaseException, IOException;
}
