package agroludos.to;

import java.util.List;

public interface EmailTO extends AgroludosTO{

	void addDestinatario(UtenteTO dest);
	
	void setMessage(String message);
	
	List<UtenteTO> getDestinatari();
	
	String getMessage();
	
	void setMittente(UtenteTO mitt);

	UtenteTO getMittente();

	String getOggetto();
	void setOggetto(String ogg);

}