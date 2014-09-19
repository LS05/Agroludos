package agroludos.to;

import java.util.Date;
import java.util.List;

public interface IscrizioneTO extends AgroludosTO, Comparable<IscrizioneTO>{
	
	public Date getData();
	
	public void setData(Date data);
	
	public Integer getId();
	
	Integer getStato();
	
	void setStato(Integer stato);
	
	List<OptionalTO> getAllOptionals();
	
	PartecipanteTO getPartecipanteIscrizione();
	
	String getNomeStato();
	
	void setNomeStato(String nomeStato);
}
