package agroludos.presentation.views.utenti;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.UtenteTO;

public class ControllerUtenti extends AgroludosController {

	protected static UtenteTO utente;
	
	public static UtenteTO getUtente(){
		return utente;
	}

	@Override
	public void initializeView(String nameView) {
		this.nameView = nameView;
		
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("autenticazioneUtente")){
			Object res = response.getRespData();
			if(res instanceof UtenteTO){
				UtenteTO uto = (UtenteTO)res;
				utente = uto;
				request.getSession().setAttribute(uto);
				//aggiungo la scena al mainStage
				nav.setVista(uto.getTipoUtente().getNome());
			}
		}
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		// TODO Auto-generated method stub

	}
	private String nameView;
	@Override
	protected String getNameView() {
		return this.nameView;
	}

	@Override
	protected void setNameView(String nameView) {
		this.nameView = nameView;
	}
}