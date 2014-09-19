package agroludos.to;

public interface OptionalTO extends AgroludosTO, Comparable<OptionalTO>{

	public String getNome();

	public void setNome(String nome);

	public String getDescrizione();

	public void setDescrizione(String descrizione);

	public Double getCosto();

	public void setCosto(Double costo);

	public Integer getStato();

	public void setStato(Integer stato);

	public Integer getTipo();

	public void setTipo(Integer tipo);

	public Integer getId();

	public String getNomeStatoOpt();

	public String getNomeTipoOpt();

	public void setNomeStatoOpt(String nomeStato);

	public void setNomeTipoOpt(String nomeTipo);

}