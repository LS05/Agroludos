package agroludos.presentation.req;

import agroludos.to.AgroludosTO;

public interface RequestFactory {

	DataRequest createDataRequest(AgroludosTO data, String commandName);

	SimpleRequest createSimpleRequest(String commandName);

}
