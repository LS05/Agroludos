package agroludos.presentation.views.xml;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

class AgroludosView extends AgroludosWindow{
	private View view;
	
	AgroludosView(View view){
		super(view);
		this.view = view;
	}
	
	@Override
	public String getUrl() {
		return this.view.getUrl();
	}

	@Override
	public String getName() {
		return this.view.getName();
	}

	@Override
	public int getWidth() {
		return this.view.getWidth();
	}

	@Override
	public int getHeight() {
		return this.view.getHeight();
	}

	@Override
	public String getTitle() {
		return this.view.getTitle();
	}

	@Override
	public FXMLLoader getLoader() {
		return this.loader;
	}

	@Override
	public boolean isDialog() {
		return false;
	}

	@Override
	public void setOwnerStage(Stage ownerStage) {
		
	}

	@Override
	public Stage getOwnerStage() {
		// TODO Auto-generated method stub
		return null;
	}	
}