package agroludos.to;

class TransferObjectFactory implements TOFactory {
	
	@Override
	public DatabaseTO createDatabaseTO(){
		return new Database();
	}
	
	@Override
	public ManagerDiSistemaTO createMdSTO(){
		return new ManagerDiSistema();
	}
	
	@Override
	public UtenteTO createUTO(){
		return new Utente();
	}
	
	@Override
	public ConfigurazioneTO createConfigurazioneTO(){
		return new Configurazione();
	}
	
	@Override
	public ManagerDiCompetizioneTO createMdCTO(){
		return new ManagerDiCompetizione();
	}
}
