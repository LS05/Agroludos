package agroludos.to;

class NullUtente implements UtenteTO{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	
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
	public int compareTo(UtenteTO o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TipoUtenteTO getTipoUtente() {
		return new TipoUtente();
	}

	@Override
	public StatoUtenteTO getStatoUtente() {
		return new StatoUtente();
	}
}