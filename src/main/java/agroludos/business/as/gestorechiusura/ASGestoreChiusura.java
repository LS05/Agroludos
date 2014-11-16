package agroludos.business.as.gestorechiusura;

import agroludos.business.as.AgroludosAS;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore Chiusura.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare la chiusura dell'intero 
 * sistema Agroludos, utilizza l'interfaccia LChiusura
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see agroludos.business.as.gestorechiusura.LChiusura
 */
class ASGestoreChiusura extends AgroludosAS implements LChiusura{

	@Override
	public void chiusura() {
		System.exit(0);
	}

}