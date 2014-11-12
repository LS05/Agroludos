package agroludos.business.as.gestoreconfigurazione;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;

public interface LConfigurazione extends AgroludosService{
	public boolean checkConfigurazione();
	public boolean testConnessioneDB() throws DatabaseException;
}
