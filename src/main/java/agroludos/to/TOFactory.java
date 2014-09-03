package agroludos.to;

public interface TOFactory {

	DatabaseTO createDatabaseTO();

	ManagerDiSistemaTO createMdSTO();

	UtenteTO createUTO();

	ConfigurazioneTO createConfigurazioneTO();

	ManagerDiCompetizioneTO createMdCTO();
	
	CompetizioneTO createCompetizioneTO();
	
	IscrizioneTO createIscrizioneTO();
	
	OptionalTO createOptionalTO();
	
	PartecipanteTO createPartecipanteTO();
	
	TipoCompetizioneTO createTipoCompetizioneTO();
	
	TipoOptionalTO createTipoOptionalTO();

}
