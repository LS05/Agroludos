package agroludos.to;


public interface TipoOptionalTO extends TipiAgroludosTO, AgroludosTO, Comparable<TipoOptionalTO>{

	public String getNome();

	public void setNome(String nome);

	Integer getId();
}