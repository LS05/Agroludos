package agroludos.to;

public interface DatabaseTO{
	String getNome();
	String getTipo();
	String getUsername();
	String getPassword();
	String getPercorso();
	void setNome(String nome);
	void setTipo(String tipo);
	void setUsername(String username);
	void setPassword(String password);
	void setPercorso(String percorso);
}
