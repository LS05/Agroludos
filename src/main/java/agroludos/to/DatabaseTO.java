package agroludos.to;

public interface DatabaseTO extends AgroludosTO{
	public String getNome();

	public String getTipo();

	public String getUsername();

	public String getPassword();

	public String getServer();

	public String getPorta();

	public void setNome(String nome);

	public void setTipo(String tipo);

	public void setUsername(String username);

	public void setPassword(String password);

	public void setServer(String server);

	public void setPorta(String porta);
}