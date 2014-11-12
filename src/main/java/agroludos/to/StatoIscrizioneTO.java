package agroludos.to;

public interface StatoIscrizioneTO extends AgroludosTO, Comparable<StatoCompetizioneTO> {
	
	public String getNome();

	Integer getId();

}