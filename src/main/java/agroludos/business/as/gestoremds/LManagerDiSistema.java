package agroludos.business.as.gestoremds;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.business.MdsNotFoundException;
import agroludos.to.ManagerDiSistemaTO;

public interface LManagerDiSistema  extends AgroludosService{
	ManagerDiSistemaTO getManagerDiSistema(ManagerDiSistemaTO mdsto) throws DatabaseException;
	public boolean checkMds() throws DatabaseException, MdsNotFoundException;
}
