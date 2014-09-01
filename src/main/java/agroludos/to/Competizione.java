package agroludos.to;

import java.util.Date;

class Competizione implements CompetizioneTO{

	private String nome;
	private String tipoCompetizione;
	private Date data;
	private int nMin;
	private int nMax;
	private String descrizione;
	private String managerDiCompetizione;
	private Double costo;

	private int mdc;
	private int stato;
	private int tipo;

	@Override
	public String getNome() {
		return nome;
	}
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String getTipoCompetizione() {
		return tipoCompetizione;
	}
	@Override
	public Date getData() {
		return data;
	}
	@Override
	public void setData(Date data) {
		this.data = data;
	}
	@Override
	public int getnMin() {
		return nMin;
	}
	@Override
	public void setnMin(int nMin) {
		this.nMin = nMin;
	}
	@Override
	public int getnMax() {
		return nMax;
	}
	@Override
	public void setnMax(int nMax) {
		this.nMax = nMax;
	}
	@Override
	public Integer getMdc() {
		return mdc;
	}
	@Override
	public String getManagerDiCompetizione() {
		return managerDiCompetizione;
	}
	@Override
	public void setManagerDiCompetizione(String managerDiCompetizione) {
		this.managerDiCompetizione =managerDiCompetizione;
	}
	@Override
	public Integer getStato() {
		return stato;
	}
	@Override
	public void setStato(Integer stato) {
		this.stato = stato;
	}
	@Override
	public Integer getTipo() {
		return tipo;
	}
	@Override
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	@Override
	public void setMdc(Integer mdc) {
		this.mdc = mdc;
	}
	@Override
	public String getDescrizione() {
		return descrizione;
	}
	@Override
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public Double getCosto() {
		return costo;
	}
	@Override
	public void setCosto(Double costo) {
		this.costo = costo;
	}



}
