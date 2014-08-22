package agroludos.to;

import java.util.Date;

class Partecipante implements PartecipanteTO {
	private int id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String telefono;
	private String email;
	private String cf;
	private String indirizzo;
	private Date annoNascita;
	private String sesso;
	private String numTS;
	private String src;
	private Date dataSRC;
	
	public String getCF() {
		return cf;
	}
	public void setCF(String cf) {
		this.cf = cf;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public Date getAnnoNascita() {
		return annoNascita;
	}
	public void setAnnoNascita(Date annoNascita) {
		this.annoNascita = annoNascita;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public String getNumTS() {
		return numTS;
	}
	public void setNumTS(String numTS) {
		this.numTS = numTS;
	}
	public String getSRC() {
		return src;
	}
	public void setSRC(String src) {
		this.src = src;
	}
	public Date getDataSRC() {
		return dataSRC;
	}
	public void setDataSRC(Date dataSRC) {
		this.dataSRC = dataSRC;
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
	public String getUsername() {
		return username;
	}
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String getTelefono() {
		return telefono;
	}
	@Override
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	public int getId() {
		return id;
	}
	@Override
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String getTipo() {
		return "partecipante";
	}	
}