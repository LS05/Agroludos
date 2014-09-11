package agroludos.business.as.gestoremds;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiSistemaTO;

public interface LManagerDiSistema  extends AgroludosService{
	<T> ManagerDiSistemaTO getManagerDiSistema(T username) throws DatabaseException;
}
