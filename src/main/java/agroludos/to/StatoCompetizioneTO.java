package agroludos.to;

public interface StatoCompetizioneTO extends AgroludosTO, Comparable<StatoCompetizioneTO>{

	public String getNome();

	public void setNome(String nome);

	Integer getId();
}
