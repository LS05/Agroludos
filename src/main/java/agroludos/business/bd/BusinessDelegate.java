package agroludos.business.bd;

import java.util.List;

import agroludos.to.ConfigurazioneTO;
import agroludos.to.DatabaseTO;
import agroludos.to.UtenteTO;

public interface BusinessDelegate {
	boolean creaConfigurazione(DatabaseTO dbto) throws ApplicationException;
	boolean testConnessioneDB(DatabaseTO conf) throws ApplicationException;
	boolean inserisciManagerDiSistema(UtenteTO uto) throws ApplicationException;
//	int checkConfigurazione();
//	//        Gestione Infermieri    	//
//	List<InfermiereTO> getAllInfermieri();
//	InfermiereTO getInfermiere(String username);
//	boolean rimuoviInfermiere(InfermiereTO inf) throws ApplicationException;
//	boolean nuovoInfermiere(InfermiereTO inf) throws ApplicationException;
//	InfermiereTO modificaInfermiere(InfermiereTO inf) throws ApplicationException;
//	
//	//  	  Gestione Interventi      //
//	List<InterventoTO> getAllInterventi();
//	InterventoTO getIntervento(String id);
//	InterventoTO modificaIntervento(InterventoTO interv) throws ApplicationException;
//	boolean nuovoIntervento(InterventoTO interv) throws ApplicationException;
//	boolean rimuoviIntervento(InterventoTO interv) throws ApplicationException;
//	
//	//		   Gestione Pazienti        //
//	List<PazienteTO> getAllPazienti();
//	boolean nuovoPaziente(PazienteTO paz) throws ApplicationException;
//	PazienteTO getPaziente(PazienteTO paz);
//	PazienteTO modificaPaziente(PazienteTO paz) throws ApplicationException;
//	boolean rimuoviPaziente(String nlib) throws ApplicationException;
//	
//	//		   Gestione Pianificazione 	//
//	boolean creaPianificazione(PianificazioneTO pianificazione);
//	boolean inserisciPianificazione(PianificazioneTO pianificazione);
//	FileTO getPianificazione(String username) throws ApplicationException;
}
