package agroludos.business.as.gestoremds;

import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiSistemaTO;

public interface SManagerDiSistema {
	boolean inserisciManagerDiSistema(ManagerDiSistemaTO mdsto) throws DatabaseException;
}
