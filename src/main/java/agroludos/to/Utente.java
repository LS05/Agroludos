package agroludos.to;

class Utente implements UtenteTO{
	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome
				+ ", username=" + username + ", password=" + password
				+ ", email=" + email + ", idruolo=" + idruolo + ", stato="
				+ stato + ", nomeRuolo=" + nomeRuolo + ", nomeStatoUtente="
				+ nomeStatoUtente + "]";
	}

	private Integer id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	private Integer idruolo; //id ruolo utente
	private Integer stato;
	
	private String nomeRuolo;
	private String nomeStatoUtente;
	
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

}