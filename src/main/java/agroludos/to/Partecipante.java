package agroludos.to;

import java.util.Date;

class Partecipante extends Utente implements PartecipanteTO {
	private static final long serialVersionUID = -4787024687328022378L;
	private String cf;
	private String indirizzo;
	private Date dataNasc;
	private String sesso;
	private String numTS;
	private Date dataSRC;
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
	public int compareTo(UtenteTO o) {
		// TODO Auto-generated method stub
		return 0;
	}
}