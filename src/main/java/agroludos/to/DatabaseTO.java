package agroludos.to;

public interface DatabaseTO{
	String getNome();
	String getTipo();
	String getUsername();
	String getPassword();
	String getServer();
	String getPorta();
	void setNome(String nome);
	void setTipo(String tipo);
	void setUsername(String username);
	void setPassword(String password);
	void setServer(String server);
	void setPorta(String porta);
}
