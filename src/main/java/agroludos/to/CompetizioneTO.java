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
	 * Ritorna lo stato della competizione
	 * 
	 * @return
	 */
	Integer getStato();
	
	/**
	 * Inserisce lo stato della competizione
	 * 
	 * @param stato
	 */
	void setStato(Integer stato);

	/**
	 * Ritorna un intero che rappresenta il tipo di competizione.
	 * Tale intero può essere utilizzato nelle query nel db.
	 * 
	 * @return {@code Integer} Il tipo di competizione
	 */
	Integer getTipo();
		
	/**
	 * Ritorna un intero che rappresenta il manager di competizione, 
	 * che gestisce la competizione.
	 * 
	 * Tale intero può essere utilizzato nelle query nel db.
	 * 
	 * @return {@code Integer} L'id del manager di competizione
	 */
	Integer getMdc();
	
	/**
	 * Setta un intero che rappresenta l'id del manager di competizione, 
	 * che gestisce la competizione.
	 * 
	 * Tale intero può essere utilizzato nelle query nel db.
	 * 
	 * @return
	 */
	void setMdc(Integer mdc);
	
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
	
	/**
	 * Ritorna una rappresentazione in stringa dello stato della competizione.
	 * 
	 * @return Lo stato della competizione
	 */
	String getNomeStato();
	
	/**
	 * Ritorna una rappresentazione in stringa del tipo della competizione.
	 * 
	 * @return Il tipo competizione
	 */	
	String getNomeTipo();

	/**
	 * Setta una rappresentazione in stringa dello stato della competizione.
	 * 
	 * @param nomeStato Lo stato della competizione
	 */
	void setNomeStato(String nomeStato);

	/**
	 * Setta una rappresentazione in stringa del tipo della competizione.
	 * 
	 * @param nomeTipo Il tipo della competizione
	 */
	void setNomeTipo(String nomeTipo);	
}