package agroludos.presentation.views.xml;

import java.util.Locale;
import java.util.ResourceBundle;

import agroludos.presentation.views.AgroludosController;
import agroludos.system.SystemConf;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public abstract class AgroludosWindow {

	protected FXMLLoader loader;
	private ResourceBundle itBundle;
	private Stage stage;
	private SystemConf config;

	protected AgroludosWindow(View view, SystemConf conf){
		this.config = conf;
		this.initLoader(view.getUrl());	
	}

	private void initLoader(String url){
		String langPath = this.config.getString("langPath");
		String lang = this.config.getLang();
		this.itBundle = ResourceBundle.getBundle( langPath, Locale.forLanguageTag(lang) );
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