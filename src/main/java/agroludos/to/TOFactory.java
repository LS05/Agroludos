package agroludos.to;

public class TOFactory {
	
	private TOFactory(){ }
	
	public DatabaseTO createDatabaseTO(){
		return new Database();
	}
	
	public ManagerDiSistemaTO createMdSTO(){
		return new ManagerDiSistema();
	}
	
	public ManagerDiSistemaTO createUTO(){
		return new ManagerDiSistema();
	}
	
	public ConfigurazioneTO createConfigurazioneTO(){
		return new Configurazione();
	}
	
	public ManagerDiCompetizioneTO createMdCTO(){
		return new ManagerDiCompetizione();
	}
}