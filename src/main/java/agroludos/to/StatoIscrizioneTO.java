package agroludos.to;

public interface StatoIscrizioneTO extends AgroludosTO, Comparable<StatoCompetizioneTO> {
	public String getNome();

	public void setNome(String nome);

	Integer getId();

	void setId(Integer id);
}