package agroludos.presentation.views.mdc;

import java.util.List;

import agroludos.integration.dao.db.mysql.MySqlDAOFactory;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.DataRequest;
import agroludos.presentation.req.SimpleRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.mds.MdcModel;
import agroludos.presentation.views.utenti.ControllerUtenti;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TransferObjectFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerMdcMain extends ControllerUtenti{

	@FXML private GridPane paneCompetizioni;

	@FXML private Button btnPaneComptizioni;
	@FXML private Button btnNuovaCompetizione;

	//tabella competizioni gestite
	@FXML private TableView<CmpModel> tableCompetizione;
	@FXML private TableColumn<CmpModel, String> cmpIdCol;
	@FXML private TableColumn<CmpModel, String> cmpNomeCol;
	@FXML private TableColumn<CmpModel, String> cmpDataCol;
	@FXML private TableColumn<CmpModel, String> cmpTipoCol;
	@FXML private TableColumn<CmpModel, String> cmpStatoCol;

	private ObservableList<CmpModel> listaTabCmp;
	private List<CompetizioneTO> listCmp;

	private AgroResponse risposta;

	private DataRequest richiesta;

	@Override
	public void initializeView() {
		this.richiesta = reqFact.createDataRequest(super.utente,"getCompetizioneByMdc");
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		this.listaTabCmp = this.getListTabellaCmp();
		this.initCmpTable();
	}

	@FXML protected void btnPaneComptizioni(MouseEvent event) {
		this.paneCompetizioni.setVisible(true);
	}

		
	
	private ObservableList<CmpModel> getListTabellaCmp(){
		ObservableList<CmpModel> res = FXCollections.observableArrayList();
		CmpModel modelCmp = null;

		for(CompetizioneTO cmp : this.listCmp){
			modelCmp = new CmpModel(cmp);
			res.add(modelCmp);
		}

		return res;
	}
	
	private <S,T> TableColumn<S, T> initColumn(TableColumn<S, T> col, String colName){
		col.setCellValueFactory(new PropertyValueFactory<S, T>(colName));
		return col;
	}
	
	private void initCmpTable(){
		this.initColumn(this.cmpIdCol, "Id");
		this.initColumn(this.cmpNomeCol, "nome");
		this.initColumn(this.cmpDataCol, "data");
		this.initColumn(this.cmpTipoCol, "tipo");
		this.initColumn(this.cmpStatoCol, "stato");

		this.tableCompetizione.getItems().setAll(this.listaTabCmp);
//		this.tableCompetizione.getSelectionModel().selectedItemProperty().addListener(
//				new ChangeListener<MdcModel>(){
//
//					@Override
//					public void changed(ObservableValue<? extends MdcModel> mdcModel,
//							MdcModel oldMod, MdcModel newMod) {
//						lblMdcNome.setText(newMod.getNome());
//						lblMdcCognome.setText(newMod.getCognome());
//						lblMdcEmail.setText(newMod.getEmail());
//						lblMdcUsername.setText(newMod.getUsername());
//						lblMdcStato.setText(newMod.getStato());
//						System.out.println(newMod);
//					}
//
//				});
	}
	
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("getCompetizioneByMdc")){
			Object res = (Object)response.getRespData();
			if(res instanceof List<?>){
				List<CompetizioneTO> cmpList = (List<CompetizioneTO>)res;
				this.listCmp = cmpList;
			}
		}
		
	}	
}

