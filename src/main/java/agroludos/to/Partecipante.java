package agroludos.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

class Partecipante implements PartecipanteTO {
	private Integer id;
	private String cf;
	private String indirizzo;
	private Date dataNasc;
	private String sesso;
	private String numTS;
	private String src;
	private Date dataSRC;
	private String username;
	private String nome;
	private String cognome;
	private String email;
	private String stato;
	
	public Set<Iscrizione> iscrizioni;
	
	@Override
	public String getCf() {
		return cf;
	}

	@Override
	public void setCf(String cf) {
		this.cf = cf;
	}

	@Override
	public String getIndirizzo() {
		return indirizzo;
	}

	@Override
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Override
	public String getSesso() {
		return sesso;
	}

	@Override
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	@Override
	public String getNumTS() {
		return numTS;
	}

	@Override
	public void setNumTS(String numTS) {
		this.numTS = numTS;
	}

	@Override
	public String getSrc() {
		return src;
	}

	@Override
	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public Date getDataSRC() {
		return dataSRC;
	}

	@Override
	public void setDataSRC(Date dataSRC) {
		this.dataSRC = dataSRC;
	}
	
	@Override
	public Date getDataNasc() {
		return dataNasc;
	}
	
	@Override
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
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

	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
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
	public String getCognome() {
		return cognome;
	}

	@Override
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getStato() {
		return stato;
	}
	
	@Override
	public void setStato(String stato) {
		this.stato = stato;
	}
}