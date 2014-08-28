package agroludos.presentation.views.xml;

import javafx.fxml.FXMLLoader;

public class AgroludosDialog extends AgroludosWindow{
	private Dialog dialog;
	
	AgroludosDialog(Dialog dialog){
		super(dialog);
		this.dialog = dialog;
	}
	
	@Override
	public String getUrl() {
		return this.dialog.getUrl();
	}
	
	@Override
	public String getName() {
		return this.dialog.getName();
	}
	
	@Override
	public int getWidth() {
		return this.dialog.getWidth();
	}
	
	@Override
	public int getHeight() {
		return this.dialog.getHeight();
	}
	
	@Override
	public String getTitle() {
		return "";
	}

	@Override
	public FXMLLoader getLoader() {
		return this.loader;
	}
}
