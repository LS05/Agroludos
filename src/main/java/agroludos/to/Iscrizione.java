package agroludos.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementazione dell'interfaccia {@link IscrizioneTO}. Alcuni metodi non sono resi pubblici nell'interfaccia, in quanto chiamati
 * da Hibernate per settare o leggere i campi in base a quelli presenti nella tabella.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class Iscrizione implements IscrizioneTO{
	private static final long serialVersionUID = 1L;
	private Date data;
	private Integer id;
	private Integer idcompetizione;	
	private Partecipante partecipante;
	private Competizione competizione;
	private Set<Optional> optionals;
	private StatoIscrizione statoIscrizione;
	private Double costo;

	Iscrizione(){
		this.optionals = new HashSet<Optional>();
	}

	@Override
	public Integer getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Double getCosto() {
		this.costo = this.competizione.getCosto();
		for(OptionalTO opt: getAllOptionals()){
			this.costo = this.costo + opt.getCosto();
		}
		return this.costo;
	}

	@Override
	public void setCosto(Double costo) {
		this.costo = costo;
	}

	@Override
	public StatoIscrizione getStatoIscrizione() {
		return statoIscrizione;
	}

	/**
	 * inserisce lo stato dell'iscrizione
	 * @param statoIscrizione
	 * @see StatoIscrizioneTO
	 */
	public void setStatoIscrizione(StatoIscrizione statoIscrizione) {
		this.statoIscrizione = statoIscrizione;
	}

	@Override
	public Date getData() {
		return data;
	}

	@Override
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * 
	 * @return l'id della competizione associata all'iscrizione
	 */
	public Integer getIdcompetizione() {
		return idcompetizione;
	}

	/**
	 * Metodo utilizzato da hibernate per settare l'id della competizione attraverso il mapping
	 * @param idcompetizione
	 */
	@SuppressWarnings("unused")
	private void setIdcompetizione(Integer idcompetizione) {
		this.idcompetizione = idcompetizione;
	}

	@Override
	public List<OptionalTO> getAllOptionals(){
		List<OptionalTO> res = new ArrayList<OptionalTO>();

		for(Optional item : this.optionals){
			res.add(item);
		}

		return res;
	}

	/**
	 * metodo utilizzato da hibernate per leggere gli optional associati all'iscrizione settati tramite mapping
	 * @return set degli optional associati all'iscrizione
	 */
	public Set<Optional> getOptionals() {
		return optionals;
	}

	@Override
	public void setOptionals(Set<OptionalTO> optionals) {
		for(OptionalTO opt : optionals){
			this.optionals.add((Optional)opt);
		}
	}

	@Override
	public void addOptional(OptionalTO optional) {
		Optional o = (Optional)optional;		
		this.optionals.add(o);
	}

	@Override
	public Partecipante getPartecipante() {
		return this.partecipante;
	}

	@Override
	public void setPartecipante(UtenteTO partecipante) {
		this.partecipante = (Partecipante)partecipante;
	}

	@Override
	public Competizione getCompetizione() {
		return this.competizione;
	}

	@Override
	public void setCompetizione(CompetizioneTO competizione) {
		this.competizione = (Competizione) competizione;
	}

	@Override
	public void clearOptionals(){
		this.optionals.clear();
	}

	@Override
	public void setStatoIscrizione(StatoIscrizioneTO statoIscrizione) {
		this.statoIscrizione = (StatoIscrizione) statoIscrizione;
	}

	@Override
	public int compareTo(IscrizioneTO o) {
		// TODO Auto-generated method stub
		return 0;
	}
}