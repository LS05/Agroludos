package agroludos.presentation.controller;

import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resph.AgroResponseContext;

/**
 * L'interfaccia rappresenta il pattern <strong>Application Controller</strong>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://martinfowler.com/eaaCatalog/applicationController.html">http://martinfowler.com/eaaCatalog/applicationController.html</a>
 */
public interface ApplicationController {

	/**
	 * Il metodo si occupa di gestire la richiesta espressa come parametro.<br>
	 * In base alla sua esecuzione il risultato è restituito tramite un'istanza di {@link AgroResponseContext}
	 * 
	 * @param request Richiesta da utilizzare per l'esecuzione di un particolare servizio
	 * @return
	 */
	public AgroResponseContext gestisciRichiesta(AgroRequestContext request);

	/**
	 * Il metodo si occupa di effettuare il dispatching.
	 * 
	 * @param requestContext Richiesta del servizio da eseguire
	 * @param responseContext Riposta ottenuta dalla sua esecuzione
	 */
	public void gestisciRisposta(AgroRequestContext requestContext, AgroResponseContext responseContext);

}