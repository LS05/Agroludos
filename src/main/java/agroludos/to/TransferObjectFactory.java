package agroludos.to;

class TransferObjectFactory implements TOFactory {

	@Override
	public DatabaseTO createDatabaseTO(){
		return new Database();
	}

	@Override
	public SuccessTO createSuccessTO(){
		return new SuccessImpl();
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
}