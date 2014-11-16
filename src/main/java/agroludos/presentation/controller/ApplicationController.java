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

	public AgroResponseContext gestisciRichiesta(AgroRequestContext request);

	public void gestisciRisposta(AgroRequestContext requestContext, AgroResponseContext responseContext);

}