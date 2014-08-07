package agroludos.business.bd;

import agroludos.presentation.reqresh.AgroRequestContext;
import agroludos.req.mapper.CommandMapper;
import agroludos.to.AgroludosTO;

public abstract class AGBusinessDelegate extends CommandMapper{
	AGBusinessDelegate(){
		super();
	}
	
	public Object gestisciServizio(String servizio, AgroludosTO to) {
		return execute(servizio, to);
	}
	
	public Object gestisciServizio(AgroludosTO to) {
		return execute(to);
	}
}
