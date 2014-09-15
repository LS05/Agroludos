package agroludos.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Competizione implements CompetizioneTO{

	@Override
	public String toString() {
		return "Competizione [nome=" + nome + ", data=" + data + ", nmin="
				+ nmin + ", nmax=" + nmax + ", descrizione=" + descrizione
				+ ", costo=" + costo + ", mdc=" + mdc + ", stato=" + stato
				+ ", tipo=" + tipo + ", id=" + id + ", nomeStato=" + nomeStato
				+ ", nomeTipo=" + nomeTipo + ", optionals=" + optionals
				+ ", iscritti=" + iscritti + ", iscrizioni=" + iscrizioni + "]";
	}

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
	
	private String nomeStato;
	private String nomeTipo;
	
	@Override
	public void setNomeStato(String nomeStato) {
		this.nomeStato = nomeStato;
	}

	@Override
	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}

	@Override
	public String getNomeStato() {
		return this.nomeStato;
	}

	@Override
	public String getNomeTipo() {
		return this.nomeTipo;
	}

	public Set<Optional> optionals;
	
	public Set<Partecipante> iscritti;
	public Set<Iscrizione> iscrizioni;

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
}