package agroludos.to;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TipoOptional implements TipoOptionalTO{
	private static final long serialVersionUID = -9220739058941615067L;
	private String nome;
	private Integer id;
	private Set<Optional> optionals;
	
	TipoOptional(){
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
	public Integer getId() {
		return id;
	}
	
	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public List<OptionalTO> getAllOptionals() {
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
	public int compareTo(TipoOptionalTO o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return "TipoOptional [nome=" + nome + ", id=" + id + "]";
	}
	
}