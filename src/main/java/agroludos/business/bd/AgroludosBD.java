package agroludos.business.bd;

import java.util.List;

import agroludos.business.as.gestoreconfigurazione.LConfigurazione;
import agroludos.business.as.gestoreconfigurazione.SConfigurazione;
import agroludos.business.as.gestoreconfigurazione.IntConfigurazione;
import agroludos.to.ConfigurazioneTO;
import agroludos.to.DatabaseTO;

class AgroludosBD implements BusinessDelegate{
//	
//	private static LUtenteI LUtente;
//	private static SUtenteI SUtente;
//
//	private static SInterventiI SInterventi;
//	private static LInterventiI LInterventi;
//	
//	private static LPazienteI LPazienti;
//	private static SPazienteI SPazienti;
//	
//	private static ODatiI ODati;
//	private static LDatiI IDati;
//	
//	private static LServerI LServer;
//	private static SServerI SServer;
	
//	private static LInfermiereI LInfermiere;
//	private static SInfermiereI SInfermiere;
	
	private static LConfigurazione LConfigurazione;
	private static SConfigurazione SConfigurazione;

	AgroludosBD(){
		LConfigurazione = IntConfigurazione.getLConfigurazioneI();
//		LUtente = IntUtente.getLUtenteI();
//		SUtente = IntUtente.getSUtenteI();
	}

	@Override
	public boolean checkConnessioneDB(DatabaseTO conf) throws ApplicationException{
		boolean res = false;
		LConfigurazione.testDBConnection(conf);
		return res;
	}

//	public List<InfermiereTO> getAllInfermieri() {
//		List<InfermiereTO> userList = LInfermiere.getAllInfermieri();
//		return userList;
//	}
//
//	public InfermiereTO getInfermiere(String username) {
//		return LInfermiere.getDati(username);
//	}
//
//	public boolean rimuoviInfermiere(InfermiereTO user) throws ApplicationException{
//		boolean res = false;
//		try {
//			res = SInfermiere.rimuoviInfermiere(user);
//		} catch (DataDeleteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return res;
//	}
//
//	public boolean nuovoInfermiere(InfermiereTO user) throws ApplicationException {
//		boolean res = false;
//		try {
//			res = SInfermiere.nuovoInfermiere(user);
//		} catch (DataFormatException e) {
//			res = false;
//			throw new ApplicationException(e.getMessage());
//		}
//		return res;
//	}
//
//	public InfermiereTO modificaInfermiere(InfermiereTO user) throws ApplicationException {
//		boolean res = false;
//		InfermiereTO upUser = null;
//		try {
//			res = SInfermiere.modificaInfermiere(user);
//		} catch (DataFormatException e) {
//			throw new ApplicationException(e.getMessage());
//		}
//		if(res)
//			upUser = LInfermiere.getDati(user.getID());
//		return upUser;
//	}
//
//	public List<InterventoTO> getAllInterventi() {
//		List<InterventoTO> userList = LInterventi.getAllInterventi();
//		return userList;
//	}
//
//	public InterventoTO getIntervento(String id) {
//		return LInterventi.getIntervento(id);
//	}
//
//	public InterventoTO modificaIntervento(InterventoTO interv) throws ApplicationException{
//		boolean res = false;
//		InterventoTO intv = null;
//		
//		try {
//			res = SInterventi.modificaIntervento(interv);
//		} catch (DataFormatException e) {
//			throw new ApplicationException(e.getMessage());
//		}
//		if(res)
//			intv = LInterventi.getDati(interv.getID());
//
//		return intv;
//	}
//
//	public boolean nuovoIntervento(InterventoTO interv) throws ApplicationException {
//		boolean res = false;
//		try {
//			res = SInterventi.nuovoIntervento(interv);
//		} catch (DataFormatException e) {
//			res = false;
//			throw new ApplicationException(e.getMessage());
//		}
//
//		return res;
//	}
//
//	public boolean rimuoviIntervento(InterventoTO interv) throws ApplicationException {
//		boolean res = false;
//		try {
//			res = SInterventi.rimuoviIntervento(interv);
//		} catch (DataDeleteException e) {
//			throw new ApplicationException(e.getMessage());
//		}
//		return res;
//	}
//
//	public List<PazienteTO> getAllPazienti() {
//		List<PazienteTO> userList = LPazienti.getAllPazienti();
//		return userList;
//	}
//
//	public boolean nuovoPaziente(PazienteTO paz) throws ApplicationException {
//		boolean res = false;
//		try {
//			res = SPazienti.nuovoPaziente(paz);
//		} catch (DataFormatException e) {
//			res = false;
//			throw new ApplicationException(e.getMessage());
//		}
//
//		return res;
//	}
//	
//	public PazienteTO getPaziente(PazienteTO paz) {
//		return LPazienti.getPaziente(paz);
//	}
//
//	public PazienteTO modificaPaziente(PazienteTO paz) throws ApplicationException {
//		boolean res = false;
//		PazienteTO paziente = null;
//		
//		try {
//			res = SPazienti.modificaPaziente(paz);
//		} catch (DataFormatException e) {
//			throw new ApplicationException(e.getMessage());
//		}
//		if(res)
//			paziente = LPazienti.getPaziente(paz);
//
//		return paziente;
//	}
//
//	public boolean rimuoviPaziente(String nlib) throws ApplicationException {
//		boolean res = false;
//		try {
//			res = SPazienti.rimuoviPaziente(nlib);
//		} catch (DataDeleteException e) {
//			throw new ApplicationException(e.getMessage());
//		}
//		return res;
//	}
//
//	public boolean creaPianificazione(PianificazioneTO pianificazione) {
//		return ODati.creaPianificazione(pianificazione);
//	}
//	
//	public boolean inserisciPianificazione(PianificazioneTO pianificazione) {
//		return ODati.inserisciPianificazione(pianificazione);
//	}
//	
//	public FileTO getPianificazione(String username) throws ApplicationException {
//		FileTO file = null;
//		
//		try {
//			file = IDati.getPianificazione(username);
//		} catch (ReadPianificazioneException e) {
//			throw new ApplicationException(e.getMessage());
//		}
//		
//		return file;
//	}
}