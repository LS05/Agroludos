package agroludos.presentation.views.tablemodel;

import agroludos.to.OptionalTO;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OptModel {
	private SimpleStringProperty id;
	private SimpleStringProperty nome;
	private SimpleStringProperty descrizione;
	private SimpleIntegerProperty tipo;
	private SimpleStringProperty nomeTipo;
	private SimpleIntegerProperty stato;
	private SimpleStringProperty nomeStato;
	private SimpleDoubleProperty costo;
	private OptionalTO optTO;

	public OptModel(OptionalTO optTO){
		this.id = new SimpleStringProperty(Integer.toString(1));
		this.nome = new SimpleStringProperty(optTO.getNome());
		this.descrizione = new SimpleStringProperty(optTO.getDescrizione());
		this.tipo = new SimpleIntegerProperty(optTO.getTipoOptional().getId());
		this.nomeTipo = new SimpleStringProperty(optTO.getTipoOptional().getNome());
		this.stato = new SimpleIntegerProperty(optTO.getStatoOptional().getId());
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

	public Integer getTipo() {
		return this.tipo.get();
	}

	public void setTipo(Integer tipo) {
		this.tipo.set(tipo);
	}

	public String getNomeTipo() {
		return this.nomeTipo.get();
	}

	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo.set(nomeTipo);
	}

	public Integer getStato() {
		return stato.get();
	}

	public void setStato(Integer stato) {
		this.stato.set(stato);
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

	public SimpleIntegerProperty tipoProperty() {
		return this.tipo;
	}

	public SimpleStringProperty nomeTipoProperty() {
		return this.nomeTipo;
	}

	public SimpleIntegerProperty statoProperty() {
		return this.stato;
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
				+ descrizione + ", tipo=" + tipo + ", nomeTipo=" + nomeTipo
				+ ", stato=" + stato + ", nomeStato=" + nomeStato + ", costo="
				+ costo + ", optTO=" + optTO + "]";
	}


}