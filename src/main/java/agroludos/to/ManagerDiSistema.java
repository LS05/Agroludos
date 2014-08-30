package agroludos.to;

class ManagerDiSistema implements ManagerDiSistemaTO {
	private int id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String telefono;
	private String email;
	private String tipo;
	
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
	public String getTelefono() {
		return telefono;
	}
	
	@Override
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	
	@Override
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String getTipo() {
		return "managerDiSistema";
	}
	
	@Override
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ", nome=" + nome + ", cognome=" + cognome
				+ ", username=" + username + ", password=" + password
				+ ", telefono=" + telefono + ", email=" + email + "]";
	}
}