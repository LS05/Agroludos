package agroludos.to;

public interface UtenteTO extends AgroludosTO, Comparable<UtenteTO> {
	
	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);

	String getNome();

	void setNome(String nome);

	void setCognome(String cognome);

	String getCognome();

	Integer getId();

	String getEmail();

	void setEmail(String email);
	
	TipoUtenteTO getTipoUtente();
	
	StatoUtenteTO getStatoUtente();
	
	void setStatoUtente(StatoUtenteTO stato);
}