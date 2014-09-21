package agroludos.to;

import java.util.List;

public interface TipoCompetizioneTO extends AgroludosTO, Comparable<TipoCompetizioneTO>{
	
	public String getNome();
	
	public void setNome(String nome);
	
	public String getDescrizione();
	
	public void setDescrizione(String descrizione);
	
	public Integer getId();

	List<CompetizioneTO> getAllCompetizioni();
}
