package agroludos.main;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.InfoMessageTO;

public class AgroludosInitController extends AgroludosController{
	private String viewName;

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		AgroRequest richiesta = null;
		AgroResponse risposta = null;
		String commandName = request.getCommandName();

		if(commandName.equals(this.getCommandName("checkConfigurazione") )){
			richiesta = this.getRichiesta("testConnessioneDB", this.viewName);
			risposta = this.getRisposta();
			this.eseguiRichiesta(richiesta, risposta);
		}else if(commandName.equals(this.getCommandName("testConnessioneDB") )){
			Object res = response.getRespData();
			if(res instanceof Boolean){
				boolean dbConn = (Boolean)response.getRespData();
				if(dbConn){
					richiesta = this.getRichiesta("checkMds", this.viewName);
					risposta = this.getRisposta();
					this.eseguiRichiesta(richiesta, risposta);
				} else {
					System.out.println("Connessione Database Non Riuscita");
				}
			}
		}else if(commandName.equals(this.getCommandName("checkMds") )){
			Object res = response.getRespData();
			if(res instanceof Boolean){
				boolean mds = (Boolean)response.getRespData();
				if(mds){
					this.setVista("login");
				} else {
					this.setVista("configurazione");
				}
			}else if(res instanceof String){
				InfoMessageTO errorMessage = toFact.createInfoMessageTO();
				String msg = (String)res;
				errorMessage.setMessage(msg);
				this.setVista("configurazione");
				this.setVista("messageDialog", errorMessage);
			}
		}
	}

	@Override
	public void initializeView(String nameView) {
		this.viewName = nameView;

	}

	@Override
	public void initializeView(AgroludosTO mainTO) {

	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}
}