package agroludos.presentation.views.utenti;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.UtenteTO;

/**
 * Gestisce la view di controllo per degli utenti
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public class ControllerUtenti extends AgroludosController {
	protected String viewName;

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

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();
		if(commandName.equals( this.getCommandName("autenticazioneUtente"))){
			Object res = response.getRespData();
			if(res instanceof UtenteTO){
				UtenteTO uto = (UtenteTO)res;
				this.setUtente(uto);
				request.getSession().setAttribute(uto);
				this.setVista(uto.getTipoUtente().getNome());
			}
		}
	}
}