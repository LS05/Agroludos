package agroludos.to;

class Database implements DatabaseTO{
	private static final long serialVersionUID = -5536152960444041292L;
	private String nome;
	private String server;
	private String porta;
	private String username;
	private String password;
	private String tipo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getUsername() {
		return username;
	}
	public String getPorta() {
		return porta;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setPorta(String porta) {
		this.porta = porta;
	}
	
	@Override
	public String toString(){
		return "DatabaseTO";
	}
}
