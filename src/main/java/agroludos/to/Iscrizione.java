package agroludos.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

class Iscrizione implements IscrizioneTO{
	private Date data;
	private Integer id;
	private Integer stato;
	private Integer idcompetizione;	
	private Partecipante partecipante;
	private Integer idpartecipante;
	private Set<Optional> optionals;
	private String nomeStato;
	
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
	
	@Override
	public Integer getStato() {
		return stato;
	}
	
	@Override
	public void setStato(Integer stato) {
		this.stato = stato;
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
	
	public Integer getIdpartecipante() {
		return idpartecipante;
	}
	
	public void setIdpartecipante(Integer idpartecipante) {
		this.idpartecipante = idpartecipante;
	}
	
	@Override
	public PartecipanteTO getPartecipanteIscrizione() {
		return this.partecipante;
	}
	
	public Partecipante getPartecipante() {
		return partecipante;
	}
	
	public void setPartecipante(Partecipante partecipante) {
		this.partecipante = partecipante;
	}
	
	@Override
	public String getNomeStato() {
		return this.nomeStato;
	}
	
	@Override
	public void setNomeStato(String nomeStato) {
		this.nomeStato = nomeStato;
		
	}
	
	@Override
	public String toString() {
		return "Iscrizione [data=" + data + ", id=" + id + ", stato=" + stato
				+ ", idcompetizione=" + idcompetizione + ", partecipante="
				+ partecipante + ", idpartecipante=" + idpartecipante
				+ ", optionals=" + optionals + "]";
	}
}