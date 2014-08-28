package agroludos.presentation.views.xml;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;

public abstract class AgroludosWindow {
	private ResourceBundle itBundle;
	protected FXMLLoader loader;
	
	AgroludosWindow(View view){
		this.initLoader(view.getUrl());	
	}
	
	AgroludosWindow(Dialog dialog){
		this.initLoader(dialog.getUrl());
	}
	
	private void initLoader(String url){
		this.itBundle = ResourceBundle.getBundle("bundles.Agroludos", Locale.forLanguageTag("it"));
		this.loader = new FXMLLoader(this.getClass().getResource(url), itBundle);
	}
	
	public FXMLLoader getLoader(){
		return this.loader;
	}

	abstract public String getUrl();
	
	abstract public String getName();
	
	abstract public int getWidth();
	
	abstract public int getHeight();
	
	abstract public String getTitle();
}