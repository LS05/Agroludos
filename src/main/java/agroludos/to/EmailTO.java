package agroludos.to;

import java.util.List;

public interface EmailTO extends AgroludosTO{

	void addDestinatario(UtenteTO dest);

	List<UtenteTO> getDestinatari();

	String getMessage();

	void setMessage(String message);


	String getOggetto();

	void setOggetto(String ogg);

}