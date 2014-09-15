package agroludos.presentation.fc;

import agroludos.presentation.controller.ACFactory;
import agroludos.presentation.controller.ApplicationController;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.reqh.RequestContextFactory;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.resph.AgroResponseContext;

/**
 * <strong>Presentation Tier</strong><br />
 * La classe rappresenta un'implementazione del Front Controller (basandosi 
 * su quanto specificato nell'interfaccia {@link agroludos.presentation.fc.FrontController}).<br />
 * 
 * Grazie all'uso del front controller è possibile centralizzare le richieste provenienti dal livello
 * di presentazione. Senza alcun punto di centralizzazione, il codice dei livelli sottostanti 
 * è strettamente accoppiato e duplicato con le view. Se ciò dovesse accadere, l'applicazione risulterebbe
 * meno modulare e coesa. <br>
 * Inoltre, avere codice duplicato in diversi punti, significa avere un'enorme
 * difficoltà in termini di manutenzione (un singolo cambiamento potrebbe richiedere un numero elevato di
 * modifiche al codice).
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://martinfowler.com/eaaCatalog/frontController.html">http://martinfowler.com/eaaCatalog/frontController.html</a>
 * @see <a href="http://it.wikipedia.org/wiki/Front_Controller_pattern">http://it.wikipedia.org/wiki/Front_Controller_pattern</a>
 */
class FC implements FrontController{

	/**
	 * Factory dell'application controller.<br>
	 * Rappresenta una dipendenza per la classe e permette di ottenere un'istanza 
	 * di una class che implementa l'interfaccia 
	 * {@link agroludos.presentation.controller.ApplicationController}
	 * 
	 * @see agroludos.presentation.controller.ACFactory
	 */
	private ACFactory acFact;

	/**
	 * 
	 */
	private RequestContextFactory reqFact;

	/**
	 * 
	 * @param reqFact
	 * @param acFact
	 */
	FC(RequestContextFactory reqFact, ACFactory acFact){
		this.reqFact = reqFact;
		this.acFact = acFact;
	}

	/**
	 * 
	 */
	@Override
	public void eseguiRichiesta(AgroRequest request, AgroResponse response) {
		AgroRequestContext requestContext = this.reqFact.createRequestContext(request);
		ApplicationController ac = this.acFact.getAC();
		AgroResponseContext responseContext = ac.gestisciRichiesta(requestContext);
		responseContext.setResponse(response);
		ac.gestisciRisposta(requestContext, responseContext);
	}
}