package agroludos.to;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TipoCompetizione implements TipoCompetizioneTO{
	private static final long serialVersionUID = -5721300020452970477L;
	private String descrizione;
	private String nome;
	private Integer id;
	private Set<Competizione> competizioni;
	
	TipoCompetizione(){
		this.competizioni = new HashSet<Competizione>();
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
	public String getNome() {
		return nome;
	}
	
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	void setId(Integer id) {
		this.id = id;
	}
	
	public Set<Competizione> getCompetizioni() {
		return competizioni;
	}

	public void setCompetizioni(Set<Competizione> competizioni) {
		this.competizioni = competizioni;
	}
	
	@Override
	public List<CompetizioneTO> getAllCompetizioni() {
		List<CompetizioneTO> res = new ArrayList<CompetizioneTO>();

		for(Competizione item : this.competizioni){
			res.add(item);
		}

		return res;
	}
	
	@Override
	public int compareTo(TipoCompetizioneTO o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return "TipoCompetizione [descrizione=" + descrizione + ", nome="
				+ nome + ", id=" + id + "]";
	}
}