package agroludos.business.as.gestoremail;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.EmailTO;

public interface LEmail extends AgroludosService{
	
	boolean sendEmail(EmailTO email) throws DatabaseException;

}
