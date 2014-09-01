package agroludos.to;

public interface TOFactory {

	DatabaseTO createDatabaseTO();

	ManagerDiSistemaTO createMdSTO();

	UtenteTO createUTO();

	ConfigurazioneTO createConfigurazioneTO();

	ManagerDiCompetizioneTO createMdCTO();

}
