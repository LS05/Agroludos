package agroludos.to;

public interface StatoUtenteTO extends AgroludosTO, Comparable<StatoUtenteTO>{
	
	public String getNome();

	public void setNome(String nome);

	Integer getId();
}
