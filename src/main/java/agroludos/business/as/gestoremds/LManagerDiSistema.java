package agroludos.business.as.gestoremds;

import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.UtenteTO;

public interface LManagerDiSistema {
	ManagerDiSistemaTO getManagerDiSistema(UtenteTO uto) throws DatabaseException;
}
