package agroludos.business.as.gestorechiusura;

import agroludos.business.as.AgroludosAS;

class ASGestoreChiusura extends AgroludosAS implements LChiusura{

	@Override
	public void chiusura() {
		System.exit(0);
	}

}