package agroludos.to;

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

	public void setStatoUtente(StatoUtente statoUtente) {
		this.statoUtente = statoUtente;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	public int compareTo(UtenteTO o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome
				+ ", username=" + username + ", password=" + password
				+ ", email=" + email + ", tipoUtente=" + tipoUtente
				+ ", statoUtente=" + statoUtente + "]";
	}
}