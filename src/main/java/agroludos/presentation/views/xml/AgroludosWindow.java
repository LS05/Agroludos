package agroludos.presentation.views.xml;

import java.util.Locale;
import java.util.ResourceBundle;

import agroludos.presentation.views.AgroludosController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public abstract class AgroludosWindow {
	private ResourceBundle itBundle;
	protected FXMLLoader loader;
	private Stage stage;

	
	AgroludosWindow(View view){
		this.initLoader(view.getUrl());	
	}
	
	private void initLoader(String url){
		this.itBundle = ResourceBundle.getBundle("bundles.Agroludos", Locale.forLanguageTag("it"));
		this.loader = new FXMLLoader(this.getClass().getResource(url), itBundle);
	}
	
	public FXMLLoader getLoader(){
		return this.loader;
	}
	
	public AgroludosController getController(){
		return this.loader.getController();
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}


	abstract public String getUrl();
	
	abstract public String getName();
	
	abstract public int getWidth();
	
	abstract public int getHeight();
	
	abstract public String getTitle();
	
	abstract public boolean isDialog();
	
	abstract public boolean isMainView();



}