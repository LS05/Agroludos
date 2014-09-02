package agroludos.presentation.controller;

import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resph.AgroResponseContext;

public interface ApplicationController {
	public AgroResponseContext gestisciRichiesta(AgroRequestContext request);
	public void gestisciRisposta(AgroRequestContext requestContext, AgroResponseContext responseContext);
}