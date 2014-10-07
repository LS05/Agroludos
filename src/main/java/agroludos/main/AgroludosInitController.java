package agroludos.main;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;

public class AgroludosInitController extends AgroludosController{
	private String viewName;
	
	private AgroRequest richiesta;

	private AgroResponse risposta;

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("checkConfigurazione")){
			boolean isConf = (Boolean)response.getRespData();

			if(isConf){
				this.richiesta = reqFact.createSimpleRequest("testConnessioneDB", this.viewName);
				this.risposta = respFact.createResponse();
				frontController.eseguiRichiesta(richiesta, risposta);
			}else{
				nav.setVista("configurazione");
			}
		}else if(request.getCommandName().equals("testConnessioneDB")){
			boolean dbConn = (Boolean)response.getRespData();
			if(dbConn){
				this.richiesta = this.getRichiesta("checkMds", this.viewName);
				this.risposta = respFact.createResponse();
				frontController.eseguiRichiesta(richiesta, risposta);
			} else {
				System.out.println("Connessione Database Non Riuscita");
			}
		}else if(request.getCommandName().equals("checkMds")){
			boolean mds = (Boolean)response.getRespData();
			if(mds){
				
				nav.setVista("login");
			} else {
				nav.setVista("configurazione");
			}
		}
	}

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;
		
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}
}