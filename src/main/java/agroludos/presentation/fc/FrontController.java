package agroludos.presentation.fc;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;

/**
 * L'interfaccia rappresenta il pattern <strong>Front Controller</strong>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://martinfowler.com/eaaCatalog/frontController.html">http://martinfowler.com/eaaCatalog/frontController.html</a>
 */
public interface FrontController {
	public void eseguiRichiesta(AgroRequest request, AgroResponse response);
}