package agroludos.business.as.gestoreutente;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;

public interface SUtente extends AgroludosService {
	public boolean nuovaRegistrazione() throws DatabaseException;
}
