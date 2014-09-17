package agroludos.presentation.views.mdc;

import agroludos.to.CompetizioneTO;
import javafx.beans.property.SimpleStringProperty;

public class CmpModel {
	private SimpleStringProperty id;
	private SimpleStringProperty nome;
	private SimpleStringProperty data;
	private SimpleStringProperty tipo;
	private SimpleStringProperty stato;
	
	CmpModel(CompetizioneTO cmpto){
		this.id = new SimpleStringProperty(Integer.toString(cmpto.getId()));
		this.nome = new SimpleStringProperty(cmpto.getNome());
		this.data = new SimpleStringProperty(cmpto.getData().toString());
		this.tipo = new SimpleStringProperty(cmpto.getNomeTipo());
		this.stato = new SimpleStringProperty(cmpto.getNomeStato());
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

	public String getData() {
		return data.get();
	}

	public void setData(String data) {
		this.data.set(data);
	}

	public String getTipo() {
		return tipo.get();
	}

	public void setTipo(String tipo) {
		this.tipo.set(tipo);
	}

	public String getStato() {
		return stato.get();
	}

	public void setStato(String stato) {
		this.stato.set(stato);
	}

	@Override
	public String toString() {
		return "CmpModel [id=" + id + ", nome=" + nome + ", data=" + data
				+ ", tipo=" + tipo + ", stato=" + stato + "]";
	}

	
}