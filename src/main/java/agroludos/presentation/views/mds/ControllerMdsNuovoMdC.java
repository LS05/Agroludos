package agroludos.presentation.views.mds;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.numberspinner.NumberSpinner;
import agroludos.to.AgroludosTO;
import agroludos.to.StatoUtenteTO;
import agroludos.to.SuccessTO;
import agroludos.to.TipoUtenteTO;
import agroludos.to.UtenteTO;

public class ControllerMdsNuovoMdC extends AgroludosController implements Initializable{
	private String viewName;
	
	private @FXML TextField txtUsername;
	private @FXML PasswordField txtPassword;
	private @FXML TextField txtNome;
	private @FXML TextField txtCognome;
	private @FXML TextField txtRevealPassword;
	private @FXML TextField txtEmail;
	private @FXML CheckBox checkBoxReveal;
	private @FXML ComboBox<String> cmbStato;
	private @FXML GridPane mainNuovoMdC;
	private List<TipoUtenteTO> listTipiUtente;
	private List<StatoUtenteTO> listStatiUtente;
	
	private AgroRequest richiesta;
	private AgroResponse risposta;
	
	private ResourceBundle resources;

	private NumberSpinner stipendioMdC;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.resources = resources;
	}
	
	@Override
	protected void initializeView(AgroludosTO mainTO) {
		
		// TODO Auto-generated method stub
	}

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
		this.stipendioMdC = new NumberSpinner(BigDecimal.ZERO, new BigDecimal("10"), new DecimalFormat("#,##0.00"));
		this.mainNuovoMdC.add(this.stipendioMdC, 1, 5);
		this.richiesta = this.getRichiesta("getAllTipoUtente", this.viewName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		
		this.richiesta = this.getRichiesta("getAllStatoUtente", this.viewName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		ObservableList<String> listStati = FXCollections.observableArrayList();
		for(StatoUtenteTO stato : this.listStatiUtente){
			listStati.add(stato.getNome());
		}
		this.cmbStato.setItems(listStati);
		String nomeStato = listStati.get(0);
		this.cmbStato.setValue(nomeStato);
		
		this.checkBoxReveal.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) {
                if(newVal){
                	txtPassword.setVisible(false);
                	txtRevealPassword.setVisible(true);
                	txtRevealPassword.setText(txtPassword.getText());
                } else {
                	txtPassword.setVisible(true);
                	txtPassword.setText(txtRevealPassword.getText());
                	txtRevealPassword.setVisible(false);
                }
            }
        });
	}
	
	@FXML protected void confermaNuovoManagerDiCompetizione(MouseEvent event){
		UtenteTO utenteTO = toFact.createMdCTO();
		utenteTO.setNome(this.txtNome.getText());
		utenteTO.setCognome(this.txtCognome.getText());
		utenteTO.setUsername(this.txtUsername.getText());
		utenteTO.setPassword(this.txtPassword.getText());
		utenteTO.setEmail(this.txtEmail.getText());
		utenteTO.setTipoUtente(this.listTipiUtente.get(1));
		int selectedStato = this.cmbStato.getSelectionModel().getSelectedIndex();
		utenteTO.setStatoUtente(this.listStatiUtente.get(selectedStato));
		this.richiesta = this.getRichiesta(utenteTO, "nuovoManagerDiCompetizione", this.viewName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		SuccessTO succTO = toFact.createSuccessTO();
		succTO.setMessage(this.resources.getString("key132"));
		nav.setVista("successDialog", succTO);
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();
		
		if( commandName.equals( this.reqProperties.getProperty("getAllTipoUtente") )){
			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<TipoUtenteTO> listTipo = (List<TipoUtenteTO>)res;
				this.listTipiUtente = listTipo;
			}
		} else if( commandName.equals( this.reqProperties.getProperty("getAllStatoUtente") )){
			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<StatoUtenteTO> listStati = (List<StatoUtenteTO>)res;
				this.listStatiUtente = listStati;
			}
		}
	}
}