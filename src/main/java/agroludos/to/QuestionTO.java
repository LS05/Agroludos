package agroludos.to;

/**
 * L'interfaccia rappresenta question. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti un question).
 * L'oggetto viene creato quando si vuole effettuare una richiesta dopo aver sottoposto il client
 * ad una domanda.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface QuestionTO extends AgroludosTO {

	/**
	 * 
	 * @return il nome della view che effettuerà la richiesta
	 */
	public abstract String getViewName();

	/**
	 * Inserisce il nome della view che effettuerà la richiesta
	 * @param nameView
	 */
	public abstract void setViewName(String nameView);

	/**
	 * 
	 * @return il nome della richiesta da esegure
	 */
	public abstract String getRequest();

	/**
	 * Inserisce il nome della richiesta da inserire
	 * @param request
	 */
	public abstract void setRequest(String request);

	/**
	 * 
	 * @return un'istanza di {@link AgroludosTO} da utilizzare nella richiesta se necessario
	 */
	public abstract AgroludosTO getDataTO();

	/**
	 * Inserisce un {@link AgroludosTO} che verrà utilizzato nella richiesta
	 * @param dataTO
	 */
	public abstract void setDataTO(AgroludosTO dataTO);
	
	/**
	 * Inserisce il testo della domanda
	 * @param question
	 */
	public abstract void setQuestion(String question);

	/**
	 * 
	 * @return il testo della domanda
	 */
	public abstract String getQuestion();

}