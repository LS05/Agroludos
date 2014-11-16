package agroludos.to;

/**
 * L'interfaccia rappresenta l'entit&agrave Optional. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti una competizione).
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface OptionalTO extends AgroludosTO, Comparable<OptionalTO>{

	/**
	 * 
	 * @return il nome dell'optional
	 */
	public String getNome();

	/**
	 * inserisce il nome dell'optional
	 * @param nome
	 */
	public void setNome(String nome);

	/**
	 * 
	 * @return la descrizione dell'optional
	 */
	public String getDescrizione();

	/**
	 * Inserisce la descrizione dell'optional
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione);

	/**
	 * 
	 * @return costo dell'optional
	 */
	public Double getCosto();

	/**
	 * Inserisce il costo dell'optional
	 * @param costo
	 */
	public void setCosto(Double costo);

	/**
	 * 
	 * @return l'id dell'optional
	 */
	public Integer getId();

	/**
	 * 
	 * @return il tipo di optional
	 * @see TipoOptionalTO
	 */
	TipoOptionalTO getTipoOptional();

	/**
	 * 
	 * @return lo stato dell'optional
	 * @see StatoOptionalTO
	 */
	StatoOptionalTO getStatoOptional();

	/**
	 * Inserisce il tipo di optional
	 * @param tipoOptional
	 * @see TipoOptionalTO
	 */
	void setTipoOptional(TipoOptionalTO tipoOptional);

	/**
	 * Inserisce lo stato dell'optional
	 * @param statoOptional
	 * @see StatoOptionalTO
	 */
	void setStatoOptional(StatoOptionalTO statoOptional);

}