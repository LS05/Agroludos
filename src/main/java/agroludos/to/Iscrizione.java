package agroludos.to;

import java.util.ArrayList;
import java.util.Date;
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
	
	public void setOptionals(Set<Optional> optionals) {
		this.optionals = optionals;
	}
	
	@Override
	public Partecipante getPartecipante() {
		return this.partecipante;
	}
	
	public void setPartecipante(Partecipante partecipante) {
		this.partecipante = partecipante;
	}
	
	@Override
	public Competizione getCompetizione() {
		return this.competizione;
	}
	
	public void setCompetizione(Competizione competizione) {
		this.competizione = competizione;
	}
	

	@Override
	public int compareTo(IscrizioneTO o) {
		// TODO Auto-generated method stub
		return 0;
	}
}