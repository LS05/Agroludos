package agroludos.presentation.views.components.tablemodel;

import agroludos.to.OptionalTO;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class OptModel {
	private SimpleStringProperty id;
	private SimpleStringProperty nome;
	private SimpleStringProperty descrizione;
	private SimpleStringProperty nomeTipo;
	private SimpleStringProperty nomeStato;
	private SimpleDoubleProperty costo;
	private OptionalTO optTO;

	public OptModel(OptionalTO optTO){
		this.id = new SimpleStringProperty(Integer.toString(1));
		this.nome = new SimpleStringProperty(optTO.getNome());
		this.descrizione = new SimpleStringProperty(optTO.getDescrizione());
		this.nomeTipo = new SimpleStringProperty(optTO.getTipoOptional().getNome());
		this.nomeStato = new SimpleStringProperty(optTO.getStatoOptional().getNome());
		this.costo = new SimpleDoubleProperty(optTO.getCosto());
		this.optTO = optTO;
	}

	public String getId() {
		return this.id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public String getNome() {
		return this.nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public String getDescrizione() {
		return this.descrizione.get();
	}

	public void setDescrizione(String descrizione) {
		this.descrizione.set(descrizione);
	}

	public String getNomeTipo() {
		return this.nomeTipo.get();
	}

	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo.set(nomeTipo);
	}

	public String getNomeStato() {
		return nomeStato.get();
	}

	public void setNomeStato(String nomeStato) {
		this.nomeStato.set(nomeStato);
	}

	public Double getCosto() {
		return costo.get();
	}

	public void setCosto(Double costo) {
		this.costo.set(costo);
	}

	public OptionalTO getOptTO() {
		return optTO;
	}

	public SimpleStringProperty nomeProperty() {
		return this.nome;
	}

	public SimpleStringProperty descrizioneProperty() {
		return this.descrizione;
	}

	public SimpleStringProperty nomeTipoProperty() {
		return this.nomeTipo;
	}

	public SimpleStringProperty nomeStatoProperty() {
		return this.nomeStato;
	}

	public SimpleDoubleProperty costoProperty() {
		return this.costo;
	}

	@Override
	public String toString() {
		return "OptModel [id=" + id + ", nome=" + nome + ", descrizione="
				+ descrizione + ", nomeTipo=" + nomeTipo
				+ ", nomeStato=" + nomeStato + ", costo="
				+ costo + ", optTO=" + optTO + "]";
	}
}