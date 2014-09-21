package agroludos.presentation.req;

import agroludos.to.AgroludosTO;

public interface RequestFactory {

	DataRequest createDataRequest(AgroludosTO data, String commandName, String viewName);

	SimpleRequest createSimpleRequest(String commandName, String viewName);
}