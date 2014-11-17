package agroludos.presentation.fc;

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
class FrontControllerImpl implements FrontController{

	/**
	 * Riferimento all'application controller.<br>
	 * Rappresenta una dipendenza per la classe e rappresenta l'application controller che permetterà di gestire
	 * le richieste e le risposte.
	 * 
	 * @see agroludos.presentation.controller.ApplicationController
	 */
	private ApplicationController appController;

	/**
	 * Riferimento ad una classe che implementa il factory per il context object delle richieste.
	 */
	private RequestContextFactory reqFact;

	/**
	 * Il costruttore inizializza gli attributi appController e reqFact.
	 * 
	 * @param reqFact Riferimento ad una classe che implementa il factory per il context object delle richieste.
	 * @param applicationController Riferimento all'application controller
	 */
	FrontControllerImpl(RequestContextFactory reqFact, ApplicationController applicationController){
		this.reqFact = reqFact;
		this.appController = applicationController;
	}

	/**
	 * Il metodo si occupa di gestire la richiesta specificata. In particolare viene utilizzato il metodo 
	 * gestisciRichiesta dell'application controller, viene settata la risposta e viene utilizzato l'application
	 * controller per effettuare il dispatching.
	 */
	@Override
	public void eseguiRichiesta(AgroRequest request, AgroResponse response) {
		AgroRequestContext requestContext = this.reqFact.createRequestContext(request);
		AgroResponseContext responseContext = this.appController.gestisciRichiesta(requestContext);
		responseContext.setResponse(response);
		this.appController.gestisciRisposta(requestContext, responseContext);
	}
}