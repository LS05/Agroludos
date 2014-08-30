package agroludos.to;

class Utente implements UtenteTO{
	private int id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	private Integer tipo;
	private Integer stato;
	private String ruolo;

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
	public int getId() {
		return id;
	}
	
	void setId(int id) {
		this.id = id;
	}
	
	@Override
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	@Override
	public String getRuolo() {
		return this.ruolo;
	}
	
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	public Integer getTipo() {
		return tipo;
	}
	
	@Override
	public Integer getStato() {
		return stato;
	}
	
	@Override
	public void setStato(Integer stato) {
		this.stato = stato;
	}
}