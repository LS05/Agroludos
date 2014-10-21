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
	private Set<Optional> optionalAttivi;

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
	public List<OptionalTO> getAllOptionalAttivi() {
		List<OptionalTO> res = new ArrayList<OptionalTO>();

		for(Optional item : this.optionalAttivi){
			res.add(item);
		}

		return res;
	}

	public Set<Optional> getOptionalAttivi() {
		return optionalAttivi;
	}

	public void setOptionalAttivi(Set<Optional> optionalAttivi) {
		this.optionalAttivi = optionalAttivi;
	}

	@Override
	public int compareTo(TipoOptionalTO o) {
		return this.getNome().compareTo(o.getNome());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		TipoOptional other = (TipoOptional) obj;
		if (!nome.equalsIgnoreCase(other.getNome()))
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return "TipoOptional [nome=" + nome + ", id=" + id + "]";
	}
}