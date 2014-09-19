package agroludos.to;

public interface StatoOptionalTO extends AgroludosTO, Comparable<StatoCompetizioneTO> {
	public String getNome();

	public void setNome(String nome);

	Integer getId();
}
