package agroludos.to;

import java.util.Date;

class Partecipante implements PartecipanteTO {
	private int id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
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
	
	@Override
	public String toString() {
		return "{" + "\n\tNome = " + nome + ",\n\tCognome = " + cognome
				+ ",\n\tUsername = " + username + ",\n\tPassword = " + password
				+ ",\n\tEmail = " + email + ",\n\tCodice Fiscale = " + cf + ",\n\tIndirizzo = "
				+ indirizzo + ",\n\tannoNascita=" + annoNascita + ",\n\tSesso = "
				+ sesso + ",\n\tNum. Tessera Sanitaria = " + numTS + ",\n\tData Certificato SRC = "
				+ dataSRC + "\n}";
	}
	
	public static void main(String args[]){
		Partecipante part = new Partecipante();
		part.setNome("luca");
		part.setCognome("Suriano");
		part.setCF("srnlcu89t05l328x");
		System.out.println(part);
	}
}