package agroludos.to;

import java.util.ArrayList;
import java.util.List;


public class EmailImpl implements EmailTO{
	private static final long serialVersionUID = -8701532820569848721L;
	
	private List<UtenteTO> listDestinatari;
	private String messaggio;
	private UtenteTO mittente;

	private String oggetto;
	
	EmailImpl(){
		this.listDestinatari = new ArrayList<UtenteTO>();
	}

	@Override
	public void setMessage(String message) {
		this.messaggio = message;
		
	}

	@Override
	public List<UtenteTO> getDestinatari() {
		return this.listDestinatari;
	}

	@Override
	public String getMessage() {
		return this.messaggio;
	}

	@Override
	public void addDestinatario(UtenteTO dest) {
		this.listDestinatari.add(dest);		
	}

	@Override
	public void setMittente(UtenteTO mitt) {
		this.mittente = mitt;
		
	}
	
	@Override
	public UtenteTO getMittente() {
		return this.mittente;
	}

	@Override
	public String getOggetto() {
		return this.oggetto;
	}

	@Override
	public void setOggetto(String ogg) {
		this.oggetto = ogg;
		
	}
}