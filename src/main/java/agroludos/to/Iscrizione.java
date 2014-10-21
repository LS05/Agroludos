package agroludos.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Iscrizione implements IscrizioneTO{
	private static final long serialVersionUID = 1L;
	private Date data;
	private Integer id;
	private Integer idcompetizione;	
	private Partecipante partecipante;
	private Competizione competizione;
	private Set<Optional> optionals;
	private StatoIscrizione statoIscrizione;

	Iscrizione(){
		this.optionals = new HashSet<Optional>();
	}
	
	@Override
	public StatoIscrizione getStatoIscrizione() {
		return statoIscrizione;
	}

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
	
	@Override
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	

	public Integer getIdcompetizione() {
		return idcompetizione;
	}
	
	public void setIdcompetizione(Integer idcompetizione) {
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