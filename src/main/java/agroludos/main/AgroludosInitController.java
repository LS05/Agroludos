package agroludos.main;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;

public class AgroludosInitController extends AgroludosController{
	
	private AgroRequest richiesta;
	
	private AgroResponse risposta;

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("checkConfigurazione")){
			boolean isConf = (Boolean)response.getRespData();
			
			if(isConf){
				this.richiesta = reqFact.createSimpleRequest("testConnessioneDB");
				this.risposta = respFact.createResponse();
				frontController.eseguiRichiesta(richiesta, risposta);
			}else{
				nav.setVista("configurazione");
			}
		}
		
		if(request.getCommandName().equals("testConnessioneDB")){
			boolean dbConn = (Boolean)response.getRespData();
			if(dbConn){
				nav.setVista("login");
			} else {
				System.out.println("Connessione Database Non Riuscita");
			}
		}
	}
}