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
	 */
	void addOptional(OptionalTO optional);

	/**
	 * Rimuove tutti gli optional associati ad una competizione
	 * 
	 */
	void clearOptionals();

	/**
	 * Ritorna la lista degli optional scelti per questa competizione.
	 * 
	 * @return Una {@code List} di optional.
	 */
	List<OptionalTO> getAllOptionals();

	/**
	 * 
	 * @return il tipo di competizione
	 * @see TipoCompetizioneTO
	 */
	TipoCompetizioneTO getTipoCompetizione();

	/**
	 * 
	 * @return lo stato della competizione
	 * @see StatoCompetizioneTO
	 */
	StatoCompetizioneTO getStatoCompetizione();

	/**
	 * 
	 * @return il Manager della competizione
	 * @see ManagerDiCompetizioneTO
	 */
	ManagerDiCompetizioneTO getManagerDiCompetizione();

	/**
	 * Inserisce lo stato della competizione
	 * @param statoCompetizione
	 * @see StatoCompetizioneTO
	 */
	void setStatoCompetizione(StatoCompetizioneTO statoCompetizione);

	/**
	 * Inserisce il tipo di competizione
	 * @param tipoCompetizione
	 * @see TipoCompetizioneTO
	 */
	void setTipoCompetizione(TipoCompetizioneTO tipoCompetizione);

	/**
	 * Inserisce il manager di competizione
	 * @param managerDiCompetizione
	 * @see ManagerDiCompetizioneTO
	 */
	void setManagerDiCompetizione(ManagerDiCompetizioneTO managerDiCompetizione);

	/**
	 * 
	 * @return restituisce il numero di iscritti alla competizione
	 */
	int getNiscritti();

	/**
	 * Inserisce in numero di iscritti alla competizione
	 * @param nIscritti
	 */
	void setNiscritti(int nIscritti);

}