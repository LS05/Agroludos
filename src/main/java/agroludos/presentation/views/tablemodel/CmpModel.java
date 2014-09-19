package agroludos.presentation.views.tablemodel;

import agroludos.to.CompetizioneTO;

import javafx.beans.property.SimpleStringProperty;

public class CmpModel {
	private SimpleStringProperty id;
	private SimpleStringProperty nome;
	private SimpleStringProperty data;
	private SimpleStringProperty niscritti;
	private SimpleStringProperty nmin;
	private SimpleStringProperty nmax;
	private SimpleStringProperty tipo;
	private SimpleStringProperty stato;
	private SimpleStringProperty descrizione;
	private SimpleStringProperty costo;
	private CompetizioneTO cmpto;

	public CmpModel(CompetizioneTO cmpto){
		this.id = new SimpleStringProperty(Integer.toString(cmpto.getId()));
		this.nome = new SimpleStringProperty(cmpto.getNome());
		this.data = new SimpleStringProperty(cmpto.getData().toString());
		this.niscritti = new SimpleStringProperty(Integer.toString(cmpto.getAllIscritti().size()));
		this.nmin = new SimpleStringProperty(Integer.toString(cmpto.getNmin()));
		this.nmax = new SimpleStringProperty(Integer.toString(cmpto.getNmax()));
		this.tipo = new SimpleStringProperty(cmpto.getNomeTipo());
		this.stato = new SimpleStringProperty(Integer.toString(cmpto.getStato()));
		this.descrizione = new SimpleStringProperty(cmpto.getDescrizione());
		this.costo = new SimpleStringProperty(Double.toString(cmpto.getCosto()));
		this.cmpto = cmpto;
	}


	public CompetizioneTO getCompetizioneTO() {
		return this.cmpto;
	}
	public void setCompetizioneTO(CompetizioneTO cmpto) {
		this.cmpto = cmpto;
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

	public String getNiscritti() {
		return niscritti.get();
	}

	public void setNiscritti(String niscritti) {
		this.niscritti.set(niscritti);
	}

	public String getNmin() {
		return nmin.get();
	}

	public void setNmin(String nmin) {
		this.nmin.set(nmin);
	}

	public String getNmax() {
		return nmax.get();
	}

	public void setNmax(String nmax) {
		this.nmax.set(nmax);
	}

	public String getDescrizione() {
		return descrizione.get();
	}

	public String getCosto() {
		return costo.get();
	}

	public SimpleStringProperty idProperty(){
		return this.id;
	}

	public SimpleStringProperty nomeProperty(){
		return this.nome;
	}

	public SimpleStringProperty dataProperty(){
		return this.data;
	}

	public SimpleStringProperty niscrittiProperty(){
		return this.niscritti;
	}

	public SimpleStringProperty nminProperty(){
		return this.nmin;
	}

	public SimpleStringProperty nmaxProperty(){
		return this.nmax;
	}

	public SimpleStringProperty tipoProperty(){
		return this.tipo;
	}

	public SimpleStringProperty descrizioneProperty(){
		return this.descrizione;
	}

	public SimpleStringProperty costoProperty(){
		return this.costo;
	}

	public SimpleStringProperty statoProperty(){
		return this.stato;
	}

	@Override
	public String toString() {
		return "CmpModel [id=" + id + ", nome=" + nome + ", data=" + data
				+ ", niscritti=" + niscritti + ", nmin=" + nmin + ", nmax="
				+ nmax + ", tipo=" + tipo + ", stato=" + stato + "]";
	}
}