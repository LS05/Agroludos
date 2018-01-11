package agroludos.presentation.reqh;

import agroludos.presentation.req.AgroRequest;

public interface RequestContextFactory {

	AgroRequestContext createRequestContext(AgroRequest request);

}