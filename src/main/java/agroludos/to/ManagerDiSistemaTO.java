package agroludos.to;

public interface ManagerDiSistemaTO extends UtenteTO{
	public int getId();

	public void setId(int id);

	public String getNome();

	public void setNome(String nome);

	public String getCognome();

	public void setCognome(String cognome);

	public String getTelefono();

	public void setTelefono(String telefono);

	public String getEmail();

	public void setEmail(String email);

}