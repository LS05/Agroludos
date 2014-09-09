package agroludos.business.as.gestoremds;

import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiSistemaTO;

public interface LManagerDiSistema {
	<T> ManagerDiSistemaTO getManagerDiSistema(T username) throws DatabaseException;
}
