package agroludos.to;

public interface UtenteTO extends AgroludosTO {
	
	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);
	
	public String getRuolo();
	
	void setRuolo(String ruolo);

	String getNome();

	void setNome(String nome);

	void setCognome(String cognome);

	String getCognome();

	int getId();

	String getEmail();

	void setEmail(String email);

	Integer getStato();

	void setStato(Integer stato);

	Integer getTipo();
}