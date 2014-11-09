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

	public Date getDataSRC();

	public void setDataSRC(Date dataSRC);

	public CertificatoTO getCertificato();
	
	public void setCertificato(CertificatoTO certificato);
	
	public String getSrc();
	
	public void setSrc(String src);

	public Date getDataNasc();

	public void setDataNasc(Date dataNasc);

	public List<IscrizioneTO> getAllIscrizioni();

}