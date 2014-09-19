package agroludos.presentation.views.tablemodel;

import javafx.beans.property.SimpleStringProperty;
import agroludos.to.IscrizioneTO;

public class IscModel {
	private SimpleStringProperty id;
	private SimpleStringProperty nome;
	private SimpleStringProperty cognome;
	private SimpleStringProperty email;
	private IscrizioneTO iscto;

	public IscModel(IscrizioneTO iscto){
		this.id = new SimpleStringProperty(Integer.toString(iscto.getId()));
		this.nome = new SimpleStringProperty(iscto.getPartecipanteIscrizione().getNome());
		this.cognome = new SimpleStringProperty(iscto.getPartecipanteIscrizione().getCognome());
		this.email =  new SimpleStringProperty(iscto.getPartecipanteIscrizione().getEmail());
		this.iscto = iscto;
	}

	public IscrizioneTO getIscrizioneTO() {
		return this.iscto;
	}

	public void setIscrizioneTO(IscrizioneTO iscto) {
		this.iscto = iscto;
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

	public SimpleStringProperty idProperty(){
		return this.id;
	}

	public SimpleStringProperty nomeProperty(){
		return this.nome;
	}

	public SimpleStringProperty cognomeProperty(){
		return this.cognome;
	}

	public SimpleStringProperty emailProperty(){
		return this.email;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", nome=" + nome + ", cognome=" + cognome
				+ ", email=" + email + ", iscto=" + iscto + "]";
	}
}