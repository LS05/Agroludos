package agroludos.to;

public class TOFactory {
	public static ConfigurazioneTO getConfigurazioneTO(){
		return new Configurazione();
	}
	
	public static DatabaseTO getDatabaseTO(){
		return new Database();
	}
}
