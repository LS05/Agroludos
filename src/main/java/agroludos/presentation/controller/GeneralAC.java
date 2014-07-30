package agroludos.presentation.controller;

import agroludos.business.as.gestoreconfigurazione.IntConfigurazione;
import agroludos.business.bd.BDFactory;
import agroludos.business.bd.BusinessDelegate;
import agroludos.integration.dao.xml.XmlConfigurazioneDAO;
import agroludos.presentation.req.DataFieldException;
import agroludos.presentation.reqresh.AgroRequestContext;
import agroludos.to.Database;
import agroludos.to.DatabaseTO;

public class GeneralAC extends AgroludosAC{

	private static BusinessDelegate agroBD;
	
//	private static ServerTOFactory toFact = ServerTOFactory.getTOFactory();

	public static void avvia(){
		agroBD = BDFactory.getBD();
//		int res = agroBD.checkConfigurazione();
	}
	
	public static void chiudiAgroludos()
	{
		System.exit(0);
	}
	
	public static <ASGestoreConfigurazione> boolean confermaConfigurazione(AgroRequestContext request){
		System.out.println("GeneralAC.confermaConfigurazione");
		Database dbto = new Database();
		
		try {
			dbto.setTipo((String)(request.getData((String)"tipo")));
			dbto.setNome((String)(request.getData((String)"nome")));
			dbto.setServer((String)(request.getData((String)"server")));
			dbto.setPorta((String)(request.getData((String)"porta")));
			dbto.setUsername((String)(request.getData((String)"username")));
			dbto.setPassword((String)(request.getData((String)"password")));
			//dbto.setTipo((String)(request.getData((String)"tipo")));
		} catch (DataFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		IntConfigurazione config = new IntConfigurazione();
		config.getSConfigurazioneI().inserisciConfigurazione(dbto);
		config.getLConfigurazioneI().testDBConnection(dbto);
		return false;
	}

	@Override
	public Class getResourceClass() {
		return this.getClass();
	}

}
