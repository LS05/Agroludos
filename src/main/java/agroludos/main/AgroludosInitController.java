package agroludos.main;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorMessageTO;

public class AgroludosInitController extends AgroludosController{
	private String viewName;

	private AgroRequest richiesta;

	private AgroResponse risposta;

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if(commandName.equals(this.getCommandName("checkConfigurazione") )){
			this.richiesta = this.getRichiesta("testConnessioneDB", this.viewName);
			this.risposta = this.getRisposta();
			this.eseguiRichiesta(richiesta, risposta);
		}else if(commandName.equals(this.getCommandName("testConnessioneDB") )){
			Object res = response.getRespData();
			if(res instanceof Boolean){
				boolean dbConn = (Boolean)response.getRespData();
				if(dbConn){
					this.richiesta = this.getRichiesta("checkMds", this.viewName);
					this.risposta = this.getRisposta();
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
				ErrorMessageTO errorMessage = toFact.createErrMessageTO();
				String msg = (String)res;
				errorMessage.setMessage(msg);
				this.setVista("messageDialog", errorMessage);
				this.setVista("configurazione");
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