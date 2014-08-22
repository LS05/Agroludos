package agroludos.to;

import java.util.Date;

public interface PartecipanteTO extends UtenteTO{
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
	
	public String getCF();

	public void setCF(String cf);
	
	public String getIndirizzo();

	public void setIndirizzo(String indirizzo);

	public Date getAnnoNascita();

	public void setAnnoNascita(Date annoNascita);
	
	public String getSesso();

	public void setSesso(String sesso);
	
	public String getNumTS();

	public void setNumTS(String numTS);
	
	public String getSRC();

	public void setSRC(String src);
	
	public Date getDataSRC();

	public void setDataSRC(Date dataSRC);
	
}