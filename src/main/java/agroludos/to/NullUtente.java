package agroludos.to;

class NullUtente implements UtenteTO{
	private Integer id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	private Integer idruolo;
	private Integer stato;
	
	@Override
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public String getNome() {
		return this.nome;
	}
	
	@Override
	public void setNome(String nome) {
		this.nome = "";
	}
	
	@Override
	public String getCognome() {
		return this.cognome;
	}
	
	@Override
	public void setCognome(String cognome) {
		this.cognome = "";
	}
	
	@Override
	public String getUsername() {
		return this.username;
	}
	
	@Override
	public void setUsername(String username) {
		this.username = "";
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public void setPassword(String password) {
		this.password = "";
	}
	
	@Override
	public String getEmail() {
		return this.email;
	}
	
	@Override
	public void setEmail(String email) {
		this.email = "";
	}
	
	@Override
	public Integer getIdruolo() {
		return this.idruolo;
	}
	
	@Override
	public void setIdruolo(Integer idruolo) {
		this.idruolo = -1;
	}
	
	@Override
	public Integer getStato() {
		return this.stato;
	}
	
	@Override
	public void setStato(Integer stato) {
		this.stato = -1;
	}

	@Override
	public String getRuolo() {
		return "";
	}

	@Override
	public String getNomeRuolo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNomeRuolo(String nomeRuolo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNomeStatoUtente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNomeStatoUtente(String nomeStatoUtente) {
		// TODO Auto-generated method stub
		
	}
}