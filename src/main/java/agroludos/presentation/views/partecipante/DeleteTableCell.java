package agroludos.presentation.views.partecipante;

import agroludos.presentation.views.components.tablemodel.OptModel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class DeleteTableCell extends TableCell<OptModel, String> {

	private final Button del;

	public DeleteTableCell() {
		this.del = new Button("X");
		del.setStyle("-fx-base: red;");
		this.del.setAlignment(Pos.CENTER);

		setAlignment(Pos.CENTER);
		setGraphic(del);
		del.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent t) {
				System.out.println("Elimina");
			}                
		});
	} 

	@Override 
	public void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			setGraphic(del);
		}
	}
}