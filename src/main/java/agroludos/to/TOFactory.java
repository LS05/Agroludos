package agroludos.to;

/**
 * L'interfaccia rappresenta il Factory dei TO, crea istanze concrete dei diversi to ma restituendo la 
 * loro interfaccia
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface TOFactory {

	/**
	 * crea un istanza di {@link ManagerDiSistema}
	 * @return un'istanza di {@link ManagerDiSistemaTO}
	 */
	ManagerDiSistemaTO createMdSTO();

	/**
	 * crea un istanza di {@link Utente}
	 * @return un'istanza di {@link UtenteTO}
	 */
	UtenteTO createUTO();

	/**
	 * crea un istanza di {@link ManagerDiCompetizione}
	 * @return un'istanza di {@link ManagerDiCompetizioneTO}
	 */
	ManagerDiCompetizioneTO createMdCTO();

	/**
	 * crea un istanza di {@link Competizione}
	 * @return un'istanza di {@link CompetizioneTO}
	 */
	CompetizioneTO createCompetizioneTO();

	/**
	 * crea un istanza di {@link Iscrizione}
	 * @return un'istanza di {@link IscrizioneTO}
	 */
	IscrizioneTO createIscrizioneTO();

	/**
	 * crea un istanza di {@link Optional}
	 * @return un'istanza di {@link OptionalTO}
	 */
	OptionalTO createOptionalTO();

	/**
	 * crea un istanza di {@link Partecipante}
	 * @return un'istanza di {@link PartecipanteTO}
	 */
	PartecipanteTO createPartecipanteTO();

	/**
	 * crea un istanza di {@link TipoCompetizione}
	 * @return un'istanza di {@link TipoCompetizioneTO}
	 */
	TipoCompetizioneTO createTipoCompetizioneTO();

	/**
	 * crea un istanza di {@link TipoOptional}
	 * @return un'istanza di {@link TipoOptionalTO}
	 */
	TipoOptionalTO createTipoOptionalTO();

	/**
	 * crea un istanza di {@link NullUtente}
	 * @return un'istanza di {@link UtenteTO}
	 */
	UtenteTO createNullUTO();

	/**
	 * crea un istanza di {@link StatoCompetizione}
	 * @return un'istanza di {@link StatoCompetizioneTO}
	 */
	StatoCompetizioneTO createStatoCompetizioneTO();

	/**
	 * crea un istanza di {@link ErrorImpl}
	 * @return un'istanza di {@link ErrorTO}
	 */
	ErrorTO createErrorTO();

	/**
	 * crea un istanza di {@link SuccessMessageImpl}
	 * @return un'istanza di {@link SuccessMessageTO}
	 */
	SuccessMessageTO createSuccMessageTO();

	/**
	 * crea un istanza di {@link StatoUtente}
	 * @return un'istanza di {@link StatoUtenteTO}
	 */
	StatoUtenteTO createStatoUtenteTO();

	/**
	 * crea un istanza di {@link StatoOptional}
	 * @return un'istanza di {@link StatoOptionalTO}
	 */
	StatoOptionalTO createStatoOptionalTO();

	/**
	 * crea un istanza di {@link Question}
	 * @return un'istanza di {@link QuestionTO}
	 */
	QuestionTO createQuestionTO();

	/**
	 * crea un istanza di {@link TipoUtente}
	 * @return un'istanza di {@link TipoUtenteTO}
	 */
	TipoUtenteTO createTipoUtenteTO();

	/**
	 * crea un istanza di {@link CertFileImpl}
	 * @return un'istanza di {@link CertFile}
	 */
	CertFile createCertFile();

	/**
	 * crea un istanza di {@link Certificato}
	 * @return un'istanza di {@link CertificatoTO}
	 */
	CertificatoTO createCertificatoTO();

	/**
	 * crea un istanza di {@link EmailImpl}
	 * @return un'istanza di {@link EmailTO}
	 */
	EmailTO createEmailTO();

	/**
	 * crea un istanza di {@link ErrorMessageImpl}
	 * @return un'istanza di {@link ErrorMessageTO}
	 */
	ErrorMessageTO createErrMessageTO();

	/**
	 * crea un istanza di {@link StatoIscrizione}
	 * @return un'istanza di {@link StatoIscrizioneTO}
	 */
	StatoIscrizioneTO createStatoIscrizioneTO();

	/**
	 * crea un istanza di {@link InfoMessageImpl}
	 * @return un'istanza di {@link InfoMessageTO}
	 */
	InfoMessageTO createInfoMessageTO();

}
