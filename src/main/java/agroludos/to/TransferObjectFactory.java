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

	@Override
	public CompetizioneTO createCompetizioneTO() {
		return new Competizione();
	}

	@Override
	public IscrizioneTO createIscrizioneTO() {
		return new Iscrizione();
	}

	@Override
	public OptionalTO createOptionalTO() {
		return new Optional();
	}

	@Override
	public PartecipanteTO createPartecipanteTO() {
		return new Partecipante();
	}

	@Override
	public TipoCompetizioneTO createTipoCompetizioneTO() {
		return new TipoCompetizione();
	}

	@Override
	public TipoOptionalTO createTipoOptionalTO() {
		return new TipoOptional();
	}
}
