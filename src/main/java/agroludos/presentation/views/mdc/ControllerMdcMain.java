package agroludos.presentation.views.mdc;

import java.util.List;

import javassist.Loader;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.DataRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.utenti.ControllerUtenti;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ControllerMdcMain extends ControllerUtenti{

	@FXML private GridPane paneCompetizioni;

	@FXML private Button btnPaneComptizioni;
	@FXML private Button btnNuovaCompetizione;



	//tabella competizioni gestite
	@FXML private TableView<CmpModel> tableCompetizione;
	@FXML private TableColumn<CmpModel, String> cmpIdCol;
	@FXML private TableColumn<CmpModel, String> cmpNomeCol;
	@FXML private TableColumn<CmpModel, String> cmpDataCol;
	@FXML private TableColumn<CmpModel, String> cmpNIscrittiCol;
	@FXML private TableColumn<CmpModel, String> cmpNminCol;
	@FXML private TableColumn<CmpModel, String> cmpNmaxCol;
	@FXML private TableColumn<CmpModel, String> cmpTipoCol;
	@FXML private TableColumn<CmpModel, String> cmpStatoCol;

	private ObservableList<CmpModel> listaTabCmp;
	private List<CompetizioneTO> listCmp;

	private AgroResponse risposta;
	private DataRequest richiesta;

	private Integer tableCompetizioneItemIndex;
	private CmpModel cmpModelRow;
	private CompetizioneTO cmpto;

	private Stage stage;

	//chiamato dai set vista con parametro
	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.cmpto = (CompetizioneTO)mainTO;
		cmpModelRow.setCompetizioneTO(this.cmpto);
		cmpModelRow.setData(this.cmpto.getData().toString());
		cmpModelRow.setId(Integer.toString(this.cmpto.getId()));
		cmpModelRow.setNiscritti(Integer.toString(this.cmpto.getAllIscritti().size()));
		cmpModelRow.setNmax(Integer.toString(this.cmpto.getNmax()));
		cmpModelRow.setNmin(Integer.toString(this.cmpto.getNmin()));
		cmpModelRow.setNome(this.cmpto.getNome());
		cmpModelRow.setStato(this.cmpto.getNomeStato());
		cmpModelRow.setTipo(this.cmpto.getNomeTipo());
		
	}
 
	@Override
	public void initializeView() {
		
			this.richiesta = reqFact.createDataRequest(utente,"getCompetizioneByMdc");
			this.risposta = respFact.createResponse();
			frontController.eseguiRichiesta(this.richiesta, this.risposta);
			
			this.listaTabCmp = this.getListTabellaCmp();
			this.initCmpTable();
			
			
			
			this.stage = (Stage)this.paneCompetizioni.getScene().getWindow();
			this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		        public void handle(WindowEvent we) {
		            System.out.println("inviare richiesta chiudi");
		        }
		    });
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
		this.initColumn(this.cmpIdCol, "id");
		this.initColumn(this.cmpNomeCol, "nome");
		this.initColumn(this.cmpDataCol, "data");
		this.initColumn(this.cmpNIscrittiCol, "niscritti");
		this.initColumn(this.cmpNminCol, "nmin");
		this.initColumn(this.cmpNmaxCol, "nmax");
		this.initColumn(this.cmpTipoCol, "tipo");
		this.initColumn(this.cmpStatoCol, "stato");

		this.tableCompetizione.getItems().setAll(this.listaTabCmp);
		
		this.tableCompetizione.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() > 1) {
                    System.out.println("double clicked!");
                    @SuppressWarnings("unchecked")
					TableView<CmpModel> table = (TableView<CmpModel>) event.getSource();
                    cmpModelRow = table.getSelectionModel().getSelectedItem();
                    //mi salvo l'indice del item selezionato per poi aggiornarlo
                    tableCompetizioneItemIndex = table.getSelectionModel().getSelectedIndex();
                    nav.setVista("mostraCmp", cmpModelRow.getCompetizioneTO());
                }
            }
        });
	}
	
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("getCompetizioneByMdc")){
			Object res = (Object)response.getRespData();
			if(res instanceof List<?>){
				List<CompetizioneTO> cmpList = (List<CompetizioneTO>)res;
				this.listCmp = cmpList;
			}
		}else if(request.getCommandName().equals("modificaCompetizione")){
			CmpModel cmpModelRow = this.tableCompetizione.getItems().get(this.tableCompetizioneItemIndex);
			Object res = (Object)response.getRespData();
			if(res instanceof CompetizioneTO){
				CompetizioneTO cmpto = (CompetizioneTO)res;
				cmpModelRow.setData(cmpto.getData().toString());
				cmpModelRow.setId(Integer.toString(cmpto.getId()));
				cmpModelRow.setNiscritti(Integer.toString(cmpto.getAllIscritti().size()));
				cmpModelRow.setNmax(Integer.toString(cmpto.getNmax()));
				cmpModelRow.setNmin(Integer.toString(cmpto.getNmin()));
				cmpModelRow.setNome(cmpto.getNome());
				cmpModelRow.setStato(cmpto.getNomeStato());
				cmpModelRow.setTipo(cmpto.getNomeTipo());
				
			}
			
			
		}
		
	}	
}

