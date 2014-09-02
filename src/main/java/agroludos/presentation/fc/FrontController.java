package agroludos.presentation.fc;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;

public interface FrontController {
	public void eseguiRichiesta(AgroRequest request, AgroResponse response);
}