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

	SuccessTO createSuccessTO();

	StatoUtenteTO createStatoUtenteTO();

	StatoOptionalTO createStatoOptionalTO();
	
	QuestionTO createQuestionTO();

	TipoUtenteTO createTipoUtenteTO();

	CertFile createCertFile();
	
	CertificatoTO createCertificatoTO();

	EmailTO createEmailTO();

}