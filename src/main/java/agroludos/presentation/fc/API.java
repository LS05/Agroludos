package agroludos.presentation.fc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adisys.server.to.InfermiereTO;
import adisys.server.to.PazienteTO;
import agroludos.presentation.req.FrameRequest;

class API implements AdisysAPI{
	
	private FrameRequest richiesta;
	private FC frontController = FC.getInstance();
	
	@Override
	public List<InfermiereTO> getAllInfermieri() {
		this.richiesta = new FrameRequest("getAllInfermieri");
		Object res = this.frontController.eseguiRichiesta(richiesta);
		if(res instanceof List)
			return (List<InfermiereTO>)res;
		else
			return new ArrayList<InfermiereTO>();	 
	}

	@Override
	public void modificaInfermiere(InfermiereTO inf) {
		Map<String, String> infData = new HashMap<String, String>();
		infData.put("nome", inf.getNome());
		infData.put("cognome", inf.getCognome());
		infData.put("id", inf.getID());
		this.richiesta = new FrameRequest(infData, "modificaInfermiere");
		Object res = this.frontController.eseguiRichiesta(richiesta);
	}

	@Override
	public List<PazienteTO> getAllPazienti() {
		this.richiesta = new FrameRequest("getAllPazienti");
		Object res = this.frontController.eseguiRichiesta(richiesta);
		if(res instanceof List)
			return (List<PazienteTO>)res;
		else
			return new ArrayList<PazienteTO>();	
	}

	@Override
	public PazienteTO getPaziente(PazienteTO paz) {
		PazienteTO pz = null;
		Map<String, String> pazData = new HashMap<String, String>();
		pazData.put("id", paz.getID());
		this.richiesta = new FrameRequest(pazData, "getPaziente");
		Object res = this.frontController.eseguiRichiesta(richiesta);
		if(res instanceof PazienteTO)
			pz = (PazienteTO)res;
		else
			pz = null;
		return pz;
	}

	@Override
	public PazienteTO modificaPaziente(PazienteTO paz) {
		PazienteTO pz = null;
		Map<String, String> pazData = new HashMap<String, String>();
		pazData.put("id", paz.getID());
		this.richiesta = new FrameRequest(pazData, "confermaModificaPaziente");
		Object res = this.frontController.eseguiRichiesta(richiesta);
		return null;
	}	
}