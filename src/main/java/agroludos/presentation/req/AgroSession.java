package agroludos.presentation.req;

import agroludos.to.UtenteTO;

public interface AgroSession {

	void setAttribute(UtenteTO uto);

	UtenteTO getAttribute();

}