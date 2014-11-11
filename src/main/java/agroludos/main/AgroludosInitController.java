package agroludos.main;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorMessageTO;
import agroludos.to.InfoMessageTO;

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