package agroludos;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import agroludos.main.App;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * La classe modella l'entry point dell'intera applicazione. La classe estende 
 * Application ({@link javafx.application.Application}) in quanto rappresenta
 * un'applicazione JavaFX. 
 * Il metodo {@link start} infatti si occupa di inizializzare Spring caricando 
 * il file xml spring.xml e di mostrare la schermata iniziale.
 *  
 * @author Luca Suriano
 * @author Francesco Zagaria
 * 
 * @see javafx.application.Application
 * @see org.springframework.context.support.ClassPathXmlApplicationContext#ClassPathXmlApplicationContext(String)
 */

public class Agroludos extends Application{
	private App mainApp;

	@Override
	public void start(Stage stage) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        this.mainApp = (App)context.getBean("mainApp");
        this.mainApp.initialize(stage);
	}
	
	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}
	
    public static void main( String[] args )
    {
        launch(args);
    }
}
