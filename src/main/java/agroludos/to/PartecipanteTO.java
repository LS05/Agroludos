package agroludos.to;

import java.util.Date;
import java.util.List;

public interface PartecipanteTO {

	public String getCf();
	public void setCf(String cf);
	public String getIndirizzo();
	public void setIndirizzo(String indirizzo);
	public String getSesso();
	public void setSesso(String sesso);
	public String getNumTS();
	public void setNumTS(String numTS);
	public Date getDataSRC();
	public void setDataSRC(Date dataSRC);
	void setSrc(String src);
	String getSrc();
	Date getDataNasc();
	void setDataNasc(Date dataNasc);
	Integer getId();
	List<IscrizioneTO> getAllIscrizioni();
	String getNome();
	String getCognome();
	void setNome(String nome);
	void setUsername(String username);
	String getUsername();
	void setId(Integer id);
	void setCognome(String cognome);
	String getEmail();
	String getStato();
	void setEmail(String email);
	void setStato(String stato);

}