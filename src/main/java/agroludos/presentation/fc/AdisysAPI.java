package agroludos.presentation.fc;

import java.util.List;

import adisys.server.to.InfermiereTO;
import adisys.server.to.PazienteTO;

public interface AdisysAPI {
	List<InfermiereTO> getAllInfermieri();
	void modificaInfermiere(InfermiereTO inf);
	
	List<PazienteTO> getAllPazienti();
	PazienteTO getPaziente(PazienteTO paz);
	PazienteTO modificaPaziente(PazienteTO paz);
}
