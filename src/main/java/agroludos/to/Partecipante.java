package agroludos.to;

import java.util.Date;

class Partecipante extends Utente implements PartecipanteTO {
	private String cf;
	private String indirizzo;
	private Date annoNascita;
	private String sesso;
	private String numTS;
	private String src;
	private Date dataSRC;
	
	@Override
	public String getCF() {
		return cf;
	}
	
	@Override
	public void setCF(String cf) {
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
	public Date getAnnoNascita() {
		return annoNascita;
	}
	
	@Override
	public void setAnnoNascita(Date annoNascita) {
		this.annoNascita = annoNascita;
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
	public String getSRC() {
		return src;
	}
	
	@Override
	public void setSRC(String src) {
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
}