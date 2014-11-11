package agroludos.presentation.views.components.tablemodel;

import agroludos.to.CompetizioneTO;
import javafx.beans.property.SimpleStringProperty;

public class CmpModel {
	private SimpleStringProperty id;
	private SimpleStringProperty nome;
	private SimpleStringProperty data;
	private SimpleStringProperty nmin;
	private SimpleStringProperty nmax;
	private SimpleStringProperty tipo;
	private SimpleStringProperty stato;
	private SimpleStringProperty descrizione;
	private SimpleStringProperty costo;
	private SimpleStringProperty iscritti;
	private SimpleStringProperty mdcmail;
	private CompetizioneTO cmpto;

	public CmpModel(CompetizioneTO cmpto){
		this.id = new SimpleStringProperty(Integer.toString(cmpto.getId()));
		this.nome = new SimpleStringProperty(cmpto.getNome());
		this.data = new SimpleStringProperty(cmpto.getData().toString());
		this.nmin = new SimpleStringProperty(Integer.toString(cmpto.getNmin()));
		this.nmax = new SimpleStringProperty(Integer.toString(cmpto.getNmax()));
		this.tipo = new SimpleStringProperty(cmpto.getTipoCompetizione().getNome());
		this.stato = new SimpleStringProperty(cmpto.getStatoCompetizione().getNome());
		this.descrizione = new SimpleStringProperty(cmpto.getDescrizione());
		this.costo = new SimpleStringProperty(Double.toString(cmpto.getCosto()));
		this.iscritti =  new SimpleStringProperty(Integer.toString(cmpto.getNiscritti()));
		this.mdcmail =  new SimpleStringProperty(cmpto.getManagerDiCompetizione().getEmail());
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

	public String getMdcmail() {
		return mdcmail.get();
	}


	public void setMdcmail(String mdcMail) {
		this.mdcmail.set(mdcMail);
	}
	
	public String getNiscritti() {
		return iscritti.get();
	}


	public void setNiscritti(String niscritti) {
		this.iscritti.set(niscritti);
	}
	
	public SimpleStringProperty mdcmailProperty(){
		return this.mdcmail;
	}

	public SimpleStringProperty iscrittiProperty(){
		return this.iscritti;
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
				+ ", niscritti=" + iscritti + ", nmin=" + nmin + ", nmax="
				+ nmax + ", tipo=" + tipo + ", stato=" + stato + "]";
	}

}