package agroludos.presentation.views.utenti;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.UtenteTO;

public class ControllerUtenti extends AgroludosController {

	protected static UtenteTO utente;
	@Override
	public void initializeView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("autenticazioneUtente")){
			Object res = response.getRespData();
			if(res instanceof UtenteTO){
				UtenteTO uto = (UtenteTO)res;
				this.utente = uto;
				request.getSession().setAttribute(uto);
				nav.setVista(uto.getNomeRuolo());
			}

		}


	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		// TODO Auto-generated method stub
		
	}
}
