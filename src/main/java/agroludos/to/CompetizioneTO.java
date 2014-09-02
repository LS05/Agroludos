package agroludos.to;

import java.util.Date;

public interface CompetizioneTO {
	
	public String getNome();
	public void setNome(String nome);
	public Date getData();
	public void setData(Date data);
	public int getnMin();
	public void setnMin(int nMin);
	public int getnMax();
	public void setnMax(int nMax);
	public String getDescrizione();
	public void setDescrizione(String descrizione);
	public Double getCosto();
	public void setCosto(Double costo);
	
	Integer getStato();
	void setStato(Integer stato);

	Integer getTipo();
	void setTipo(Integer tipo);
	
	Integer getMdc();
	void setMdc(Integer mdc);
	
	Integer getId();
	
}
