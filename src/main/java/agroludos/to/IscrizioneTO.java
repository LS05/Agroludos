package agroludos.to;

import java.util.Date;
import java.util.List;

public interface IscrizioneTO extends AgroludosTO, Comparable<IscrizioneTO>{
	
	public Date getData();
	
	public void setData(Date data);
	
	public Integer getId();
	
	List<OptionalTO> getAllOptionals();

	StatoIscrizione getStatoIscrizione();

	PartecipanteTO getPartecipante();

	CompetizioneTO getCompetizione();
	
}
