package agroludos.presentation.views.mds;

import java.net.URL;
import java.util.ResourceBundle;

import com.google.common.eventbus.Subscribe;

import agroludos.presentation.views.AgroludosController;
import javafx.fxml.Initializable;

public class ControllerMdsModificaMDC extends AgroludosController implements Initializable{

	@Override
	public void initialize(URL url, ResourceBundle resBoundle) {
		eventBus.register(this);
	}

	@Subscribe
	public void visualizzaManagerDiCompetizione(Object e) {

	}

}