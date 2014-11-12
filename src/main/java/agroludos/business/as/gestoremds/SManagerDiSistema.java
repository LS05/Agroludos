package agroludos.business.as.gestoremds;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.ManagerDiSistemaTO;

public interface SManagerDiSistema extends AgroludosService{
	ManagerDiSistemaTO nuovoManagerDiSistema(ManagerDiSistemaTO mdsto) throws DatabaseException, ValidationException;
}
