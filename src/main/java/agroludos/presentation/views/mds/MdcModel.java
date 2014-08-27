package agroludos.presentation.views.mds;

import agroludos.to.ManagerDiCompetizioneTO;
import javafx.beans.property.SimpleStringProperty;

public class MdcModel {
	private SimpleStringProperty id;
	private SimpleStringProperty nome;
	private SimpleStringProperty cognome;
	private SimpleStringProperty email;
	private SimpleStringProperty username;
	private SimpleStringProperty stato;
	
	MdcModel(ManagerDiCompetizioneTO manComp){
		this.id = new SimpleStringProperty(Integer.toString(manComp.getId()));
		this.nome = new SimpleStringProperty(manComp.getNome());
		this.cognome = new SimpleStringProperty(manComp.getCognome());
		this.email = new SimpleStringProperty(manComp.getEmail());
		this.username = new SimpleStringProperty(manComp.getUsername());
		this.stato = new SimpleStringProperty(Integer.toString(manComp.getStato()));
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

	public void setStato(String stato) {
		this.stato.set(stato);
	}

	@Override
	public String toString() {
		return "[ id = " + id.get() + ",   nome = " + nome.get() + ",   cognome=" + cognome.get()
				+ ",   email=" + email.get() + ",   username=" + username.get() + " ]";
	}
}