package agroludos.business.as.gestoremail;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.EmailTO;

public interface LEmail extends AgroludosService{

	EmailTO inviaMail(EmailTO mail) throws DatabaseException;

}