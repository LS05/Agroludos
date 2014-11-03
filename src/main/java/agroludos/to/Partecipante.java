package agroludos.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

class Partecipante extends Utente implements PartecipanteTO {
	private static final long serialVersionUID = -4787024687328022378L;
	private String cf;
	private String indirizzo;
	private Date dataNasc;
	private String sesso;
	private String numTS;
	private Date dataSRC;
	private Set<Iscrizione> iscrizioni;
	private Set<Iscrizione> iscrizioniAttive;
	private String src;
	private CertificatoTO certificato;

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
	public CertificatoTO getCertificato() {
		return this.certificato;
	}
	
	@Override
	public void setCertificato(CertificatoTO certificato) {
		this.certificato = certificato;
	}
	
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

	public void setIscrizioniAttive(Set<Iscrizione> iscrizioniAttive) {
		this.iscrizioniAttive = iscrizioniAttive;
	}

	public Set<Iscrizione> getIscrizioniAttive() {
		return this.iscrizioniAttive;
	}

	@Override
	public List<IscrizioneTO> getAllIscrizioniAttive() {
		List<IscrizioneTO> res = new ArrayList<IscrizioneTO>();

		for(Iscrizione item : this.iscrizioniAttive){
			res.add(item);
		}

		return res;
	}

	@Override
	public int compareTo(UtenteTO o) {
		// TODO Auto-generated method stub
		return 0;
	}
}