package agroludos.presentation.req;

import java.util.HashMap;
import java.util.Map;

import agroludos.to.UtenteTO;

public class AgroSessionImpl implements AgroSession{

	private Map<String, UtenteTO> utenti;

	public AgroSessionImpl() {
		this.utenti = new HashMap<String, UtenteTO>();
	}

	@Override
	public void setAttribute(UtenteTO uto) {
		if(this.utenti.isEmpty()){
			this.utenti.put("session", uto);
		}else{
			this.utenti.clear();
			this.utenti.put("session", uto);
		}
	}

	@Override
	public UtenteTO getAttribute() {
		return this.utenti.get("session");
	}

}