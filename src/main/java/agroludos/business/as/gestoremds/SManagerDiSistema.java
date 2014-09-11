package agroludos.business.as.gestoremds;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiSistemaTO;

public interface SManagerDiSistema extends AgroludosService{
	boolean inserisciManagerDiSistema(ManagerDiSistemaTO mdsto) throws DatabaseException;
}
