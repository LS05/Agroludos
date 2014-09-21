package agroludos.presentation.req;

import agroludos.to.AgroludosTO;

public interface RequestFactory {

	DataRequest createDataRequest(AgroludosTO data, String commandName, String fromName);

	SimpleRequest createSimpleRequest(String commandName, String fromName);
}