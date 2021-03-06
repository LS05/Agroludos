package agroludos.to;

/**
 * Implementazione dell'interfaccia {@link TOFactory}
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class TOFactoryImpl implements TOFactory {


	@Override
	public SuccessMessageTO createSuccMessageTO(){
		return new SuccessMessageImpl();
	}

	@Override
	public ErrorMessageTO createErrMessageTO(){
		return new ErrorMessageImpl();
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
	public UtenteTO createNullUTO(){
		return new NullUtente();
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

	@Override
	public StatoCompetizioneTO createStatoCompetizioneTO() {
		return new StatoCompetizione();
	}

	@Override
	public StatoUtenteTO createStatoUtenteTO() {
		return new StatoUtente();
	}

	@Override
	public StatoOptionalTO createStatoOptionalTO() {
		return new StatoOptional();
	}

	@Override
	public ErrorTO createErrorTO() {
		return new ErrorImpl();
	}

	@Override
	public QuestionTO createQuestionTO() {
		return new Question();
	}

	@Override
	public TipoUtenteTO createTipoUtenteTO() {
		return new TipoUtente();
	}

	@Override
	public CertFile createCertFile() {
		return new CertFileImpl();
	}

	@Override
	public CertificatoTO createCertificatoTO() {
		return new Certificato();
	}

	@Override
	public EmailTO createEmailTO() {
		return new EmailImpl();
	}

	@Override
	public StatoIscrizioneTO createStatoIscrizioneTO() {
		return new StatoIscrizione();
	}
	
	@Override
	public InfoMessageTO createInfoMessageTO() {
		return new InfoMessageImpl();
	}
}