package agroludos.presentation.views.components.tablemodel;

import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import agroludos.to.IscrizioneTO;

public class IscModel {
	private SimpleStringProperty id;
	private SimpleStringProperty nome;
	private SimpleStringProperty cognome;
	private SimpleStringProperty email;
	private SimpleStringProperty data;
	private SimpleStringProperty stato;
	private SimpleStringProperty competizione;
	private IscrizioneTO iscto;

	public IscModel(IscrizioneTO iscto){
		this.id = new SimpleStringProperty(Integer.toString(iscto.getId()));
		this.nome = new SimpleStringProperty(iscto.getPartecipante().getNome());
		this.cognome = new SimpleStringProperty(iscto.getPartecipante().getCognome());
		this.email =  new SimpleStringProperty(iscto.getPartecipante().getEmail());
		this.data =  new SimpleStringProperty(iscto.getData().toString());
		this.stato =  new SimpleStringProperty(iscto.getStatoIscrizione().getNome());
		this.competizione = new SimpleStringProperty(iscto.getCompetizione().getNome());
		this.iscto = iscto;
	}

	public String getCompetizione() {
		return competizione.get();
	}

	public void setCompetizione(String competizione) {
		this.competizione.set(competizione);
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
	
	public String getData() {
		return data.get();
	}

	public void setData(Date data) {
		this.data.set(data.toString());
	}

	public String getStato() {
		return stato.get();
	}

	public void setStato(String stato) {
		this.stato.set(stato);
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
	
	public SimpleStringProperty statoProperty(){
		return this.stato;
	}
	
	public SimpleStringProperty dataProperty(){
		return this.data;
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ", nome=" + nome + ", cognome=" + cognome
				+ ", email=" + email + ", data=" + data + ", stato=" + stato
				+ ", competizione=" + competizione + ", iscto=" + iscto + "]";
	}
}