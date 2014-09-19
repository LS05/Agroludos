package agroludos.to;

public interface TipoUtenteTO extends AgroludosTO, Comparable<TipoUtenteTO>{

	public String getNome();

	public void setNome(String nome);

	Integer getId();
}
