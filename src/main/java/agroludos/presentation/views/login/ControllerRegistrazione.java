package agroludos.presentation.views.login;

import java.sql.Date;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.StatoUtenteTO;
import agroludos.to.TipoUtenteTO;

public class ControllerRegistrazione extends AgroludosController{

	private String viewName;

	@FXML private TextField txtUsername;
	@FXML private TextField txtNome;
	@FXML private TextField txtCognome;
	@FXML private TextField txtEmail;
	@FXML private TextField txtIndirizzo;
	@FXML private TextField txtDataDiNascita;
	@FXML private TextField txtCF;
	@FXML private TextField txtSesso;
	@FXML private TextField txtNTSanitaria;
	@FXML private TextField txtDataCertificato;
	@FXML private PasswordField txtPassword;
	@FXML private PasswordField txtPasswordVerifica;

	@FXML private Button btnRegistrati;
	@FXML private Button btnCarica;

	private PartecipanteTO parTO;

	private AgroResponse risposta;

	private AgroRequest richiesta;

	private List<TipoUtenteTO> listTipiU;

	private List<StatoUtenteTO> listStatiU;

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		// TODO dal forword passo l'errore to tramite setvista e setto tutti i campi
		this.txtCF.setText("zgrfnc88e27a285p");
		this.txtDataCertificato.setText("1988-05-05");
		this.txtDataDiNascita.setText("1988-05-05");
		this.txtCognome.setText("Francesco");
		this.txtNome.setText("francesco");
		this.txtEmail.setText("francesco@fra.it");
		this.txtIndirizzo.setText("via firenze 33");
		this.txtNTSanitaria.setText("321654987987654");
		this.txtPassword.setText("1OyFlxaFzx37R6HihlFSvCHli28IX9InAd8IET5DWcM=");
		this.txtPasswordVerifica.setText("1OyFlxaFzx37R6HihlFSvCHli28IX9InAd8IET5DWcM=");
		this.txtSesso.setText("m");
		this.txtUsername.setText("");

	}

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;

		this.parTO = toFact.createPartecipanteTO();

		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta("getAllTipoUtente", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		
		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta("getAllStatoUtente", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		this.btnRegistrati.setDisable(false);
		this.btnCarica.setDisable(false);

		this.txtCF.setText("zgrfnc88e27a285p");
		this.txtDataCertificato.setText("1988-05-05");
		this.txtDataDiNascita.setText("1988-05-05");
		this.txtCognome.setText("Francesco");
		this.txtNome.setText("francesco");
		this.txtEmail.setText("francesco@fra.it");
		this.txtIndirizzo.setText("via firenze 33");
		this.txtNTSanitaria.setText("321654987987654");
		this.txtPassword.setText("1OyFlxaFzx37R6HihlFSvCHli28IX9InAd8IET5DWcM=");
		this.txtPasswordVerifica.setText("1OyFlxaFzx37R6HihlFSvCHli28IX9InAd8IET5DWcM=");
		this.txtSesso.setText("m");
		this.txtUsername.setText("");
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}


	@FXML protected void btnRegistrati(MouseEvent event) {

		if(this.txtPassword.getText().compareTo(this.txtPasswordVerifica.getText())==0){
			this.parTO.setUsername(this.txtUsername.getText());
			this.parTO.setNome(this.txtNome.getText());
			this.parTO.setCognome(this.txtCognome.getText());
			this.parTO.setEmail(this.txtEmail.getText());
			this.parTO.setIndirizzo(this.txtIndirizzo.getText());
			this.parTO.setDataNasc(Date.valueOf(this.txtDataDiNascita.getText()));
			this.parTO.setCf(this.txtCF.getText());
			this.parTO.setSesso(this.txtSesso.getText());
			this.parTO.setNumTS(this.txtNTSanitaria.getText());
			this.parTO.setDataSRC(Date.valueOf(this.txtDataCertificato.getText()));
			this.parTO.setPassword(this.txtPassword.getText());
			
			//TODO da togliere
			this.parTO.setSrc("ciao.txt");
			
			this.risposta = respFact.createResponse();
			this.richiesta = this.getRichiesta(this.parTO,"inserisciPartecipante", this.viewName);
			frontController.eseguiRichiesta(this.richiesta, this.risposta);
			
			this.close();

		}else{
			//TODO
		}




	}

	@FXML protected void btnCarica(MouseEvent event) {
		//TODO caricare file csrc
	}


	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();
		if(commandName.equals( this.reqProperties.getProperty("getAllTipoUtente") )){
			Object res = response.getRespData();
			if(res instanceof List<?>){
				this.listTipiU = (List<TipoUtenteTO>)res;
				for(TipoUtenteTO tUto: this.listTipiU){
					if(tUto.getId()==2)
						this.parTO.setTipoUtente(tUto);
				}
			}
		}else if(commandName.equals( this.reqProperties.getProperty("getAllStatoUtente") )){
			Object res = response.getRespData();
			if(res instanceof List<?>){
				this.listStatiU = (List<StatoUtenteTO>)res;
				for(StatoUtenteTO sUto: this.listStatiU){
					if(sUto.getId()==1)
						this.parTO.setStatoUtente(sUto);
				}
			}
		}else if(commandName.equals( this.reqProperties.getProperty("inserisciPartecipante") )){
			Object res = response.getRespData();
			if(res instanceof PartecipanteTO){
				this.parTO = toFact.createPartecipanteTO();
			}
		}

	}

}
