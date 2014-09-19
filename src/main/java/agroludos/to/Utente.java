package agroludos.to;

class Utente implements UtenteTO{
	private static final long serialVersionUID = -5750807819852265909L;
	protected Integer id;
	protected String nome;
	protected String cognome;
	protected String username;
	protected String password;
	protected String email;
	protected Integer idruolo; //id ruolo utente
	protected Integer stato;
	protected String nomeRuolo;
	protected String nomeStatoUtente;
	
	@Override
	public String getNomeRuolo() {
		return nomeRuolo;
	}
	
	@Override
	public void setNomeRuolo(String nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}
	
	@Override
	public String getNomeStatoUtente() {
		return nomeStatoUtente;
	}
	
	@Override
	public void setNomeStatoUtente(String nomeStatoUtente) {
		this.nomeStatoUtente = nomeStatoUtente;
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
	public Integer getIdruolo() {
		return idruolo;
	}
	
	@Override
	public void setIdruolo(Integer idruolo) {
		this.idruolo = idruolo;
	}
	
	@Override
	public Integer getStato() {
		return stato;
	}
	
	@Override
	public void setStato(Integer stato) {
		this.stato = stato;
	}

	@Override
	public String getRuolo() {
		return this.nomeRuolo;
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
				+ ", email=" + email + ", idruolo=" + idruolo + ", stato="
				+ stato + ", nomeRuolo=" + nomeRuolo + ", nomeStatoUtente="
				+ nomeStatoUtente + "]";
	}
}