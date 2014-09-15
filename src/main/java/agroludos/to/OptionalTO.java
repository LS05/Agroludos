package agroludos.to;

public interface OptionalTO extends AgroludosTO{

	public String getNome();

	public void setNome(String nome);

	public String getDescrizione();

	public void setDescrizione(String descrizione);

	public Double getCosto();

	public void setCosto(Double costo);

	Integer getStato();

	void setStato(Integer stato);

	Integer getTipo();

	void setTipo(Integer tipo);

	int getId();

}