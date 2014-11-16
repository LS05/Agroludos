package agroludos.to;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * L'interfaccia rappresenta l'entit&agrave Iscrizione. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti un'iscrizione).
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface IscrizioneTO extends AgroludosTO, Comparable<IscrizioneTO>{

	/**
	 * 
	 * @return Data dell'iscrizione
	 */
	public Date getData();

	/**
	 * Inserisce la data dell'iscrizione
	 * @param data
	 */
	public void setData(Date data);

	/**
	 * 
	 * @return l'id del'iscrizione
	 */
	public Integer getId();

	/**
	 * 
	 * @return La lista degli optional associati all'iscrizione
	 * @see OptionalTO
	 */
	List<OptionalTO> getAllOptionals();

	/**
	 * 
	 * @return lo stato dell'iscrizione
	 * @see StatoIscrizioneTO
	 */
	StatoIscrizioneTO getStatoIscrizione();

	/**
	 * 
	 * @return il partecipante associato all'iscrizione
	 * @see PartecipanteTO
	 */
	PartecipanteTO getPartecipante();

	/**
	 * 
	 * @return la competizione associata all'iscrizione
	 * @see CompetizioneTO
	 */
	CompetizioneTO getCompetizione();

	/**
	 * Inserisce lo stato dell'iscrizione {@link StatoIscrizioneTO}
	 * @param statoIscrizione
	 */
	void setStatoIscrizione(StatoIscrizioneTO statoIscrizione);

	/**
	 * Rimuove tutti gli optional associati all'iscrizione
	 * @see OptionalTO
	 */
	void clearOptionals();

	/**
	 * Aggiunge alla lista degli optional l'optional in input
	 * @param optional
	 * @see OptionalTO
	 */
	void addOptional(OptionalTO optional);

	/**
	 * Inserisce la comptizione associata all'iscrizione
	 * @param competizione
	 * @see CompetizioneTO
	 */
	void setCompetizione(CompetizioneTO competizione);

	/**
	 * Inserisce il partecipante che effettua l'iscrizione
	 * @param partecipante
	 * @see IscrizioneTO
	 */
	void setPartecipante(UtenteTO partecipante);

	/**
	 * Metodo utilizzato da Hibernate per effettuare il mapping tra iscrizione e gli optional associati
	 * @param optionals
	 */
	void setOptionals(Set<OptionalTO> optionals);

	/**
	 * Inserisce il costo dell'iscrizione
	 * @param costoIsc
	 */
	void setCosto(Double costoIsc);

	/**
	 * 
	 * @return il costo dell'iscrizione tendendo conto del costo degli optional scelti
	 * e il costo della competizione
	 * @see OptionalTO
	 * @see CompetizioneTO
	 */
	Double getCosto();

}