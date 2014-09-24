package agroludos.business.as.gestoremail;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;

public interface LEmail extends AgroludosService{
	public boolean checkConfigurazione();
	public boolean testConnessioneDB() throws DatabaseException;
}
