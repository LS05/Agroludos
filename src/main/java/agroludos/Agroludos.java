package agroludos;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import agroludos.main.App;
import javafx.application.Application;
import javafx.stage.Stage;

public class Agroludos extends Application{
	private App mainApp;
	
	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void start(Stage stage) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        this.mainApp = (App)context.getBean("mainApp");
        this.mainApp.initialize();
        this.mainApp.setPrimaryStage(stage);
		this.mainApp.show();
	}
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        launch(args);
    }
}
