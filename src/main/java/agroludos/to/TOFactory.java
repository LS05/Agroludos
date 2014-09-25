package agroludos.to;

public interface TOFactory {

	DatabaseTO createDatabaseTO();

	ManagerDiSistemaTO createMdSTO();

	UtenteTO createUTO();

	ManagerDiCompetizioneTO createMdCTO();
	
	CompetizioneTO createCompetizioneTO();
	
	IscrizioneTO createIscrizioneTO();
	
	OptionalTO createOptionalTO();
	
	PartecipanteTO createPartecipanteTO();
	
	TipoCompetizioneTO createTipoCompetizioneTO();
	
	TipoOptionalTO createTipoOptionalTO();

	UtenteTO createNullUTO();
	
	StatoCompetizioneTO createStatoCompetizioneTO();
	
	ErrorTO createErrorTO();

}
