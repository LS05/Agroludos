package agroludos.to;

/**
 * Implementazione dell'interfaccia {@link UtenteTO}. Alcuni metodi non sono resi pubblici nell'interfaccia, in quanto chiamati
 * da Hibernate per settare o leggere i campi in base a quelli presenti nella tabella.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class Utente implements UtenteTO{

	private static final long serialVersionUID = -5750807819852265909L;
	protected Integer id;
	protected String nome;
	protected String cognome;
	protected String username;
	protected String password;
	protected String email;
	private TipoUtente tipoUtente;
	private StatoUtente statoUtente;

	@Override
	public Integer getId() {
		return id;
	}

	/**
	 * Utilizzato da hibernate per settare l'id tramite mapping
	 * @param id
	 */
	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public TipoUtente getTipoUtente() {
		return tipoUtente;
	}
	
	@Override
	public void setTipoUtente(TipoUtenteTO tipoUtente) {
		this.tipoUtente = (TipoUtente)tipoUtente;
	}

	@Override
	public StatoUtenteTO getStatoUtente() {
		return statoUtente;
	}
	
	@Override
	public void setStatoUtente(StatoUtenteTO stato) {
		this.statoUtente = (StatoUtente)stato;
	}

	/**
	 * Utilizzato da hibernate per settare lo statoutente tramite mapping
	 * @param statoUtente
	 */
	public void setStatoUtente(StatoUtente statoUtente) {
		this.statoUtente = statoUtente;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getCognome() {
		return cognome;
	}

	@Override
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome
				+ ", username=" + username + ", password=" + password
				+ ", email=" + email + ", tipoUtente=" + tipoUtente
				+ ", statoUtente=" + statoUtente + "]";
	}

	@Override
	public int compareTo(UtenteTO arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}