package agroludos.to;

class Utente implements UtenteTO{
	private Integer id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	private Integer idruolo; //id ruolo utente
	private Integer stato;
	private String ruolo;
	
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
		return this.ruolo;
	}

}