package agroludos.to;

public interface ManagerDiCompetizioneTO extends UtenteTO{
	
	public String toString();
	
	public Double getStipendio();
	
	public void setStipendio(Double stipendio);

	public void setStato(int selectedIndex);
	
}