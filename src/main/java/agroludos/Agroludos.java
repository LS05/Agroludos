package agroludos;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import agroludos.main.App;
import javafx.application.Application;
import javafx.stage.Stage;

public class Agroludos extends Application{
	private App mainApp;
	
	@Override
	public void start(Stage stage) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        this.mainApp.setPrimaryStage(stage);
		this.mainApp.show();
	}
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        launch(args);
    }
}
