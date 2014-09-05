package agroludos.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Competizione implements CompetizioneTO{

	private String nome;
	private Date data;
	private int nmin;
	private int nmax;
	private String descrizione;
	private Double costo;
	private int mdc;
	private int stato;
	private int tipo;
	private Integer id;
	
	public Set<Optional> optionals;
	
	public Set<Partecipante> iscritti;

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
		this.data = data;
	}
	
	@Override
	public Integer getMdc() {
		return mdc;
	}
	@Override
	public Integer getStato() {
		return stato;
	}
	@Override
	public void setStato(Integer stato) {
		this.stato = stato;
	}
	@Override
	public Integer getTipo() {
		return tipo;
	}
	@Override
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	@Override
	public void setMdc(Integer mdc) {
		this.mdc = mdc;
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
		//TODO Effettuare controlli prima di aggiungere l'optional
		Optional opt = new Optional();
		opt.setId(optional.getId());
		opt.setCosto(optional.getCosto());
		opt.setDescrizione(optional.getDescrizione());
		opt.setStato(optional.getStato());
		opt.setTipo(optional.getTipo());
		opt.setNome(optional.getNome());
		this.optionals.add(opt);
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
}