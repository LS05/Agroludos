package agroludos.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

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
	private Set<Optional> optionals;
	private TipoCompetizione tipoCompetizione;
	private StatoCompetizione statoCompetizione;
	private ManagerDiCompetizione managerDiCompetizione;

	private int niscritti;

	Competizione(){
		this.optionals = new HashSet<Optional>();
	}

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

	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
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

	@Override
	public void clearOptionals(){
		this.optionals.clear();
	}

	@Override
	public void setManagerDiCompetizione(ManagerDiCompetizioneTO managerDiCompetizione) {
		this.managerDiCompetizione = (ManagerDiCompetizione) managerDiCompetizione;
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
	public CompetizioneTO clone(){
		Competizione c = new Competizione();
		//		c.id = new Integer(id);
		//		c.nome = new String(nome);
		//		private DateTime suppData;
		//		private Date data;
		//		c.nmin = nmin;
		//		c.nmax = nmax;
		//		c.descrizione = new String(descrizione);
		//		c.costo = new Double(costo);
		//		c.idStato = idStato;
		//		c.optionals = this.optionals.clone();
		//		private Set<Optional> optionals;
		//		private TipoCompetizione tipoCompetizione;
		//		private StatoCompetizione statoCompetizione;
		//		private ManagerDiCompetizione managerDiCompetizione;
		return c;
	}

	@Override
	public int getNiscritti() {
		return this.niscritti;
	}

	@Override
	public void setNiscritti(int nIscritti) {
		this.niscritti = nIscritti;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[id=").append(id).append(", nome=").append(nome)
			.append(", data=").append(data).append(", nmin=").append(nmin)
			.append(", nmax=").append(nmax).append(", descrizione=")
			.append(descrizione).append(", costo=").append(costo)
			.append(", optionals=").append(optionals).append(", iscritti=")
			.append(", tipoCompetizione=").append(tipoCompetizione)
			.append(", statoCompetizione=").append(statoCompetizione)
			.append(", managerDiCompetizione=")
			.append(managerDiCompetizione).append("]");
		return builder.toString();
	}

}