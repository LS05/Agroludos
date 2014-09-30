package agroludos.to;

import java.util.Date;
import java.util.List;

/**
 * L'interfaccia rappresenta l'entit&agrave Competizione. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti una competizione).
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface CompetizioneTO extends AgroludosTO, Comparable<CompetizioneTO>{
	
	/**
	 * Il metodo ritorna il nome della competizione
	 * 
	 * @return Il nome della competizione
	 */
	public String getNome();
	
	/**
	 * Il metodo inserisce il nome della competizione
	 * 
	 * @param nome 
	 * 		  {@code String} che rappresenta il nome della competizione
	 */
	public void setNome(String nome);
	
	/**
	 * Ritorna la data di <strong>inizio</strong> della competizione
	 * 
	 * @return
	 */
	public Date getData();
	
	/**
	 * Il metodo setta la data della competizione
	 * 
	 * @param data
	 * 		  Data dela competizione
	 */
	public void setData(Date data);
	
	/**
	 * Ritorna la descrizione della competizione
	 * 
	 * @return
	 */
	public String getDescrizione();
	
	
	/**
	 * Inserisce la descrizione della competizione
	 * 
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione);
	
	/**
	 * Ritorna il costo di iscizione ad una competizione
	 * 
	 * @return
	 */
	public Double getCosto();
	
	
	/**
	 * Inserisce il costo di iscrizione ad una competizione
	 * 
	 * @param costo
	 */
	public void setCosto(Double costo);
	
		/**
	 * Ritorna un intero che rappresenta l'id della competizione.
	 * 
	 * Tale intero può essere utilizzato nelle query nel db.
	 * 
	 * @return {@code Integer} L'id della competizione
	 */
	Integer getId();
	
	/**
	 * Ritorna un intero che rappresenta il numero minimo di partecipanti
	 * che è possibile avere in questa competizione
	 * 
	 * @return {@code Integer} Il numero minimo di partecipanti iscrivibili
	 */
	int getNmin();
	
	/**
	 * Setta il numero minimo di partecipanti che è possibile avere in questa 
	 * competizione.
	 * 
	 * @return
	 */
	//TODO Cambiare in Integer
	void setNmin(int nmin);
	
	/**
	 * Ritorna un intero che rappresenta il numero massimo di partecipanti
	 * che è possibile avere in questa competizione
	 * 
	 * @return {@code Integer} Il numero massimo di partecipanti iscrivibili
	 */
	int getNmax();
	
	/**
	 * Setta il numero massimo di partecipanti che è possibile avere in questa 
	 * competizione.
	 * 
	 * @return
	 */
	//TODO Cambiare in Integer
	void setNmax(int nmax);
	

	/**
	 * Aggiunge un optional alla competizione. In questo modo il partecipante
	 * può scegliere gli optional inseriti in questa competizione.
	 * 
	 * @return
	 */
	void addOptional(OptionalTO optional);

	/**
	 * Ritorna la lista dei partecipanti attualmente iscritti a questa competizione.
	 * 
	 * @return Una {@code List} di partecipanti iscritti alla competizione.
	 */
	List<PartecipanteTO> getAllIscritti();

	/**
	 * Ritorna la lista degli optional scelti per questa competizione.
	 * 
	 * @return Una {@code List} di optional.
	 */
	List<OptionalTO> getAllOptionals();

	/**
	 * Ritorna la lista delle iscrizioni alla competizione.
	 * Diversamente da {@link getAllIscritti} grazie a questo metodo è possibile
	 * ottenere informazioni riguardanti gli optional scelti per l'iscrizione o altre
	 * informazioni riguardanti specificatamente un'iscrizione.
	 * 
	 * @return
	 */
	List<IscrizioneTO> getAllIscrizioni();

	boolean isTerminata();

	boolean isChiusa();

	//TODO
	TipoCompetizioneTO getTipoCompetizione();
	StatoCompetizioneTO getStatoCompetizione();
	ManagerDiCompetizioneTO getManagerDiCompetizione();

	void setStatoCompetizione(StatoCompetizioneTO statoCompetizione);

	int getIdStato();

	void setIdStato(int idStato);

	void setTipoCompetizione(TipoCompetizioneTO tipoCompetizione);

	public void setManagerDiCompetizione(ManagerDiCompetizioneTO managerDiCompetizione);

	public List<IscrizioneTO> getAllIscrizioniAttive();

}