package agroludos.to;

import java.util.Date;

public interface PartecipanteTO extends UtenteTO{

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