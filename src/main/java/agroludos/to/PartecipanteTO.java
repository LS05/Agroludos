package agroludos.to;

import java.util.Date;
import java.util.List;

public interface PartecipanteTO extends UtenteTO {

	public String getCf();

	public void setCf(String cf);

	public String getIndirizzo();

	public void setIndirizzo(String indirizzo);

	public String getSesso();

	public void setSesso(String sesso);

	public String getNumTS();

	public void setNumTS(String numTS);
	
	//TODO Da rinominare CSRC
	public Date getDataSRC();

	//TODO Da rinominare CSRC
	public void setDataSRC(Date dataSRC);

	//TODO Da rinominare CSRC
	public void setSrc(String src);

	//TODO Da rinominare CSRC
	public String getSrc();

	public Date getDataNasc();

	public void setDataNasc(Date dataNasc);
	
	public void setCertificato(String certificato);
	
	public String getCertificato();
	
	public List<IscrizioneTO> getAllIscrizioni();
}