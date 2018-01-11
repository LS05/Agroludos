package agroludos.to;

/**
 * L'interfaccia rappresenta l'entit&agrave Utente. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti una competizione).
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface UtenteTO extends AgroludosTO, Comparable<UtenteTO> {
	
	/**
	 * 
	 * @return username dell'utente
	 */
	public String getUsername();

	/**
	 * inserisce l'username dell'utente
	 * @param username
	 */
	public void setUsername(String username);

	/**
	 * 
	 * @return la password dell'utente 
	 */
	public String getPassword();

	/**
	 * inserisce la password dell'utente
	 * @param password
	 */
	public void setPassword(String password);

	/**
	 * 
	 * @return il nome dell'utente
	 */
	String getNome();

	/**
	 * Inserisce il nome dell'utente
	 * @param nome
	 */
	void setNome(String nome);

	/**
	 * Inserisce il cognome dell'utente 
	 * @param cognome
	 */
	void setCognome(String cognome);

	/**
	 * 
	 * @return il cognome dell'utente
	 */
	String getCognome();

	/**
	 * 
	 * @return l'id dell'utente
	 */
	Integer getId();

	/**
	 * 
	 * @return l'email dell'utente
	 */
	String getEmail();

	/**
	 * inserisce l'indirizzo email dell'utente
	 * @param email
	 */
	void setEmail(String email);
	
	/**
	 * 
	 * @return il tipo di utente
	 * @see TipoUtenteTO
	 */
	TipoUtenteTO getTipoUtente();
	
	/**
	 * 
	 * @return lo stato dell'utente
	 * @see StatoUtenteTO
	 */
	StatoUtenteTO getStatoUtente();
	
	/**
	 * Inserisce lo stato dell'utente
	 * @param stato
	 * @see StatoUtenteTO
	 */
	void setStatoUtente(StatoUtenteTO stato);

	/**
	 * Inserisce il tipo di utente
	 * @param stato
	 * @see TipoUtenteTO
	 */
	void setTipoUtente(TipoUtenteTO tipoUtente);
}