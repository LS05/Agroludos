package agroludos.presentation.views.mds;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import agroludos.presentation.fc.FC;
import agroludos.presentation.req.FrameRequest;
import agroludos.utility.SecurePassword;

public class ControllerMdsMain implements Initializable{

	//pane centrali
	@FXML private GridPane paneGestioneCompetizioni;
	@FXML private GridPane paneGestioneOptional;
	@FXML private GridPane paneGestioneManagerCompetizione;
	@FXML private GridPane paneGestionePartecipanti;

	//button mainView
	@FXML private Button btnGestManComp;
	@FXML private Button btnGestComp;
	@FXML private Button btnGestOptional;
	@FXML private Button btnGestPart;

	//button gest competizioni
	@FXML private Button btnCorsaCampestre;
	@FXML private Button btnTiroConArco;
	@FXML private Button btnNuovoTipoCompetizione;

	//button gest optionlal
	@FXML private Button btnPranzo;
	@FXML private Button btnMerenda;
	@FXML private Button btnPernotto;
	@FXML private Button btnNuovoTipoOptional;

	//texfield 



	private FC frontController = FC.getInstance();
	private FrameRequest richiesta;


	public void initialize(URL arg0, ResourceBundle arg1) {
		//setto visibile solo il primo pane
		this.paneGestioneCompetizioni.setVisible(true);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneManagerCompetizione.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);
	}

	//----------------Main View--------------------

	@FXML protected void btnGestComp(MouseEvent event) {
		this.paneGestioneCompetizioni.setVisible(true);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneManagerCompetizione.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);
	}

	@FXML protected void btnGestOptional(MouseEvent event) {
		this.paneGestioneCompetizioni.setVisible(false);
		this.paneGestioneOptional.setVisible(true);
		this.paneGestioneManagerCompetizione.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);
	}

	@FXML protected void btnGestManComp(MouseEvent event) {
		this.paneGestioneCompetizioni.setVisible(false);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneManagerCompetizione.setVisible(true);
		this.paneGestionePartecipanti.setVisible(false);

	}

	@FXML protected void btnGestPart(MouseEvent event) {
		this.paneGestioneCompetizioni.setVisible(false);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneManagerCompetizione.setVisible(false);
		this.paneGestionePartecipanti.setVisible(true);
	}

	//--------------------Gest Competizioni View---------------

	@FXML protected void btnCorsaCampestre(MouseEvent event) {
		//caricare competizioni di corsa campestre nella tabella
	}

	@FXML protected void btnTiroConArco(MouseEvent event) {
		//caricare competizioni di tiro con l'arco nella tabella
	}

	@FXML protected void btnNuovoTipoCompetizione(MouseEvent event) {
	}

	//--------------------Gest Optional View---------------

	@FXML protected void btnPranzo(MouseEvent event) {
		//caricare optional nella tabella
	}

	@FXML protected void btnMerenda(MouseEvent event) {
		//caricare optional nella tabella
	}

	@FXML protected void btnPernotto(MouseEvent event) {
		//caricare optional nella tabella
	}
	@FXML protected void btnNuovoTipoOptional(MouseEvent event) {
	}
}
