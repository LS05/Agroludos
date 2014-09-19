package agroludos.to;

public interface OptionalTO extends AgroludosTO, Comparable<OptionalTO>{

	public String getNome();

	public void setNome(String nome);

	public String getDescrizione();

	public void setDescrizione(String descrizione);

	public Double getCosto();

	public void setCosto(Double costo);

	public Integer getId();

	TipoOptionalTO getTipoOptional();

	StatoOptionalTO getStatoOptional();

}