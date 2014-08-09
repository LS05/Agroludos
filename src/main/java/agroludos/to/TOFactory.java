package agroludos.to;

public class TOFactory {
	
	private TOFactory(){ }
	
	public DatabaseTO createDatabaseTO(){
		return new Database();
	}
	
	public ConfigurazioneTO createConfigurazioneTO(){
		return new Configurazione();
	}
}
