package agroludos.business.as.gestoreconfigurazione;

import agroludos.exceptions.DatabaseException;

public interface LConfigurazione {
	public boolean checkConfigurazione();
	public boolean testConnessioneDB() throws DatabaseException;
}
