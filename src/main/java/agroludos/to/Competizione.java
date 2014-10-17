package agroludos.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * Implementazione dell'interfaccia CompetizioneTO. L'implementazione sull'assunzione
 * per quale alcuni metodi non sono resi pubblici nell'interfaccia, in quanto chiamati
 * da Hibernate per settare o leggere i campi in base a quelli presenti nella tabella
 * competizione.
 * In particolare i campi seguono 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class Competizione implements CompetizioneTO{
	private static final long serialVersionUID = 6648039519261379934L;

	private Integer id;
	private String nome;
	private DateTime suppData;
	private Date data;
	private int nmin;
	private int nmax;
	private String descrizione;
	private Double costo;
	private int idStato;
	
	private Set<Optional> optionals;
	private Set<Partecipante> iscritti;
	private Set<Iscrizione> iscrizioni;
	private TipoCompetizione tipoCompetizione;
	private StatoCompetizione statoCompetizione;
	private ManagerDiCompetizione managerDiCompetizione;
	
	@Override
	public int getIdStato() {
		return idStato;
	}
	@Override
	public void setIdStato(int idStato) {
		this.idStato = idStato;
	}

	private Set<Iscrizione> iscrizioniAttive;
	
	@Override
	public ManagerDiCompetizione getManagerDiCompetizione() {
		return managerDiCompetizione;
	}

	public void setManagerDiCompetizione(ManagerDiCompetizione mdc) {
		this.managerDiCompetizione = mdc;
	}
	
	@Override
	public TipoCompetizione getTipoCompetizione() {
		return tipoCompetizione;
	}

	@Override
	public void setTipoCompetizione(TipoCompetizioneTO tipoCompetizione) {
		this.tipoCompetizione = (TipoCompetizione) tipoCompetizione;
	}
	@Override
	public StatoCompetizione getStatoCompetizione() {
		return statoCompetizione;
	}

	@Override
	public void setStatoCompetizione(StatoCompetizioneTO statoCompetizione) {
		this.statoCompetizione = (StatoCompetizione) statoCompetizione;
	}

	
	Competizione(){
		this.optionals = new HashSet<Optional>();
	}


	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public Date getData() {
		return data;
	}

	@Override
	public void setData(Date data) {
		this.suppData = new DateTime(data).withTimeAtStartOfDay();
		this.data = data;
	}


	@Override
	public String getDescrizione() {
		return descrizione;
	}

	@Override
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public Double getCosto() {
		return costo;
	}

	@Override
	public void setCosto(Double costo) {
		this.costo = costo;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void addOptional(OptionalTO optional) {
		this.optionals.add((Optional) optional);
	}

	@Override
	public int getNmin() {
		return nmin;
	}

	@Override
	public void setNmin(int nmin) {
		this.nmin = nmin;
	}

	@Override
	public int getNmax() {
		return nmax;
	}

	@Override
	public void setNmax(int nmax) {
		this.nmax = nmax;
	}

	public Set<Optional> getOptionals() {
		return optionals;
	}

	public void setOptionals(Set<Optional> optionals) {
		this.optionals = optionals;
	}

	@Override
	public List<OptionalTO> getAllOptionals() {
		List<OptionalTO> res = new ArrayList<OptionalTO>();

		for(Optional item : this.optionals){
			res.add(item);
		}

		return res;
	}

	public void clearOptionals(){
		this.optionals.clear();
	}
	
	@Override
	public List<PartecipanteTO> getAllIscritti() {
		List<PartecipanteTO> res = new ArrayList<PartecipanteTO>();

		for(Partecipante item : this.iscritti){
			res.add(item);
		}

		return res;
	}

	public Set<Partecipante> getPartecipanti() {
		return iscritti;
	}

	public void setPartecipanti(Set<Partecipante> partecipanti) {
		this.iscritti = partecipanti;
	}

	public void setIscrizioniAttive(Set<Iscrizione> iscrizioniAttive) {
		this.iscrizioniAttive = iscrizioniAttive;
	}
	
	@Override
	public List<IscrizioneTO> getAllIscrizioniAttive() {
		List<IscrizioneTO> res = new ArrayList<IscrizioneTO>();
		
		for(Iscrizione item : this.iscrizioniAttive){
			res.add(item);
		}
		
		return res;
	}
	
	public Set<Iscrizione> getIscrizioniAttive() {
		return this.iscrizioniAttive;
	}
	
	@Override
	public List<IscrizioneTO> getAllIscrizioni() {
		List<IscrizioneTO> res = new ArrayList<IscrizioneTO>();

		for(Iscrizione item : this.iscrizioni){
			res.add(item);
		}

		return res;
	}

	public Set<Iscrizione> getIscrizioni() {
		return iscrizioni;
	}

	public void setIscrizioni(Set<Iscrizione> iscrizioni) {
		this.iscrizioni = iscrizioni;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean isTerminata(){
		LocalDate date = new DateTime(this.getData()).toLocalDate();
		LocalDate today = new DateTime().toLocalDate();
		boolean res = false;

		if(date.isBefore(today)){
			res = true;
		}

		return res;
	}

	@Override
	public boolean isChiusa(){
		DateTime date = new DateTime().withTimeAtStartOfDay();
		boolean res = false;

		if(date.plusDays(2).isEqual(this.suppData)){
			res = true;
		}

		return res;
	}

	@Override
	public boolean equals(Object competizione){
		if (competizione == null) return false;
		if (competizione == this) return true;
		if (!(competizione instanceof CompetizioneTO)) return false;

		Competizione otherComp = (Competizione)competizione;
		if(this.id == otherComp.getId()){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int compareTo(CompetizioneTO cpt) {
		int res = 0;

		if(this.id == cpt.getId()){
			res = 0;
		} else {
			this.suppData.compareTo(new DateTime(cpt.getData()));
		}

		return res;
	}

	@Override
	public void setManagerDiCompetizione(ManagerDiCompetizioneTO managerDiCompetizione) {
		this.managerDiCompetizione = (ManagerDiCompetizione) managerDiCompetizione;
		
	}
	
	@Override
	public String toString() {
		return "Competizione [id=" + id + ", nome=" + nome + ", suppData="
				+ suppData + ", data=" + data + ", nmin=" + nmin + ", nmax="
				+ nmax + ", descrizione=" + descrizione + ", costo=" + costo
				+ ", optionals=" + optionals + ", iscritti=" + iscritti
				+ ", iscrizioni=" + iscrizioni + ", tipoCompetizione="
				+ tipoCompetizione + ", statoCompetizione=" + statoCompetizione
				+ ", managerDiCompetizione=" + managerDiCompetizione + "]";
	}




}