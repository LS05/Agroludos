//package agroludos.presentation.views.partecipante;
//
//import java.util.Map;
//
//import agroludos.presentation.views.components.tablemodel.OptModel;
//
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Pos;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableCell;
//import javafx.scene.control.TableView;
//
//class DeleteTableCell extends TableCell<OptModel, String> {
//
//	private final Button btnElimina;
//
//	public DeleteTableCell(final Map<String, Integer> indScelti, final TableView<OptModel> tableOptScelti) {
//		this.btnElimina = new Button("X");
//		this.btnElimina.setStyle("-fx-base: red;");
//		this.btnElimina.setAlignment(Pos.CENTER);
//
//		setAlignment(Pos.CENTER);
//		setGraphic(this.btnElimina);
//		
//		this.btnElimina.setOnAction(new EventHandler<ActionEvent> () {
//			
//			@Override
//			public void handle(ActionEvent t) {
//				int index = getIndex();
//				OptModel optMod = tableOptScelti.getItems().get(index);
//				String nomeTipo = optMod.getOptTO().getTipoOptional().getNome();
//				indScelti.remove(nomeTipo);
//				tableOptScelti.getItems().remove(index);
//			}
//			
//		});
//	} 
//
//	@Override 
//	public void updateItem(String item, boolean empty) {
//		super.updateItem(item, empty);
//		if (empty) {
//			setText(null);
//			setGraphic(null);
//		} else {
//			setGraphic(this.btnElimina);
//		}
//	}
//}