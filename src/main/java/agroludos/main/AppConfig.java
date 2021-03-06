package agroludos.main;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;

import javafx.stage.Stage;

/**
 * La classe rappresenta l'implementazione dell'interfaccia {@link agroludos.main.App}.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 */

class AppConfig extends AgroludosController implements App{

	private final String VIEW_NAME = "mainController";

	@Override
	public void initialize(Stage stage){
		AgroRequest richiesta = this.getRichiesta("checkConfigurazione", this.VIEW_NAME);
		AgroResponse risposta = this.getRisposta();

		this.setMainStage(stage);
		this.setVista("initView");
		this.eseguiRichiesta(richiesta, risposta);	
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {

	}

	@Override
	protected void initializeView(String nameView) {

	}

	@Override
	protected String getViewName() {
		return this.VIEW_NAME;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {

	}
}