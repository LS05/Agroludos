package agroludos.presentation.views.tablemodel;

import agroludos.to.PartecipanteTO;
import javafx.beans.property.SimpleStringProperty;

public class PartModel {
	private SimpleStringProperty id;
	private SimpleStringProperty nome;
	private SimpleStringProperty cognome;
	private SimpleStringProperty email;
	private SimpleStringProperty username;
	private SimpleStringProperty stato;
	private SimpleStringProperty dataSRC;
	private SimpleStringProperty cf;
	private SimpleStringProperty indirizzo;
	private SimpleStringProperty sesso;
	private SimpleStringProperty dataNasc;
	private SimpleStringProperty numTessera;

	public PartModel(PartecipanteTO part){
		this.id = new SimpleStringProperty(Integer.toString(1));
		this.nome = new SimpleStringProperty(part.getNome());
		this.cognome = new SimpleStringProperty(part.getCognome());
		this.email = new SimpleStringProperty(part.getEmail());
		this.username = new SimpleStringProperty(part.getUsername());
		this.stato = new SimpleStringProperty(part.getNomeStatoUtente());
		this.dataSRC = new SimpleStringProperty(part.getDataSRC().toString());
		this.cf = new SimpleStringProperty(part.getCf());
		this.indirizzo = new SimpleStringProperty(part.getIndirizzo());
		this.sesso = new SimpleStringProperty(part.getSesso());
		this.dataNasc = new SimpleStringProperty(part.getDataNasc().toString());
		this.numTessera = new SimpleStringProperty(part.getNumTS());
	}

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public String getCognome() {
		return cognome.get();
	}

	public void setCognome(String cognome) {
		this.cognome.set(cognome);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public String getUsername() {
		return username.get();
	}

	public void setUsername(String username) {
		this.username.set(username);
	}

	public String getStato() {
		return stato.get();
	}

	public String getData() {
		return dataSRC.get();
	}

	public void setData(String data) {
		this.dataSRC.set(data);
	}

	public String getCf() {
		return cf.get();
	}

	public void setCf(String cf) {
		this.cf.set(cf);
	}

	public String getIndirizzo() {
		return indirizzo.get();
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo.set(indirizzo);
	}

	public String getSesso() {
		return sesso.get();
	}

	public void setSesso(String sesso) {
		this.sesso.set(sesso);
	}

	public String getDataNasc() {
		return dataNasc.get();
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc.set(dataNasc);
	}

	public String getNumTessera() {
		return numTessera.get();
	}

	public void setNumTessera(String numTessera) {
		this.numTessera.set(numTessera);
	}

	public void setStato(String stato) {
		this.stato.set(stato);
	}

	public SimpleStringProperty nomeProperty() {
		return this.nome;
	}
	
	public SimpleStringProperty usernameProperty() {
		return this.username;
	}

	public SimpleStringProperty cognomeProperty() {
		return this.cognome;
	}

	public SimpleStringProperty emailProperty() {
		return this.email;
	}

	@Override
	public String toString() {
		return "[ id = " + id.get() + ",   nome = " + nome.get() + ",   cognome=" + cognome.get()
				+ ",   email=" + email.get() + ",   username=" + username.get() + " ]";
	}
}