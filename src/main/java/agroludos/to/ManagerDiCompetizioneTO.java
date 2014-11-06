package agroludos.to;

import java.util.List;

public interface ManagerDiCompetizioneTO extends UtenteTO{
	
	public String toString();
	
	public Double getStipendio();
	
	public void setStipendio(Double stipendio);

	List<CompetizioneTO> getAllCompetizioni();
	
}