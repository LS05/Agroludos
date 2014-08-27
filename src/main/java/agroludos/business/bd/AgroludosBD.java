package agroludos.business.bd;

import java.util.List;

import agroludos.business.as.gestoreconfigurazione.LConfigurazione;
import agroludos.business.as.gestoreconfigurazione.SConfigurazione;
import agroludos.business.as.gestoremdc.LManagerDiCompetizione;
import agroludos.business.as.gestoremdc.SManagerDiCompetizione;
import agroludos.business.as.gestoremds.SManagerDiSistema;
import agroludos.business.as.gestoremds.LManagerDiSistema;
import agroludos.exceptions.ApplicationException;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.to.DatabaseTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.UtenteTO;

public class AgroludosBD extends BusinessDelegate{

	private LConfigurazione lconfigurazione;
	private SConfigurazione sconfigurazione;
	
	private LManagerDiSistema lmanagerdisistema;
	private SManagerDiSistema smanagerdisistema;
	
	private LManagerDiCompetizione lmanagerdicompetizione;
	private SManagerDiCompetizione smanagerdicompetizione;

	AgroludosBD(){
		System.out.println("AgroludosBD");
	}
	
	public boolean checkConfigurazione() throws ApplicationException{
		boolean res = false;
		res = lconfigurazione.checkConfigurazione();
		return res;
	}
	
	public boolean confermaConfigurazione(DatabaseTO to) throws ApplicationException{
		System.out.println("AgroludosBD.confermaConfigurazione()");
		boolean res = sconfigurazione.inserisciConfigurazione(to);
		return res;
	}

	public boolean testConnessioneDB() throws ApplicationException{
		boolean res = false;
		res = lconfigurazione.testConnessioneDB();
		return res;
	}

	public boolean nuovoManagerDiSistema(ManagerDiSistemaTO uto) throws ApplicationException {
		System.out.println("AgroludosBD.nuovoManagerDiSistema()");
		boolean res = false;
		res = smanagerdisistema.inserisciManagerDiSistema(uto);
		return res;
	}
	
	public UtenteTO autenticazioneUtente(UtenteTO uto) throws ApplicationException{
		return lmanagerdisistema.getManagerDiSistema(uto);
	}
	
	public List<ManagerDiCompetizioneTO> getAllManagerDiCompetizione(){
		System.out.println("AgroludosBD.nuovoManagerDiSistema()");
		return lmanagerdicompetizione.getAllManagerCompetizione();
	}
	
	public void setLconfigurazione(LConfigurazione conf) {
		this.lconfigurazione = conf;
	}
	
	public void setSconfigurazione(SConfigurazione conf) {
		this.sconfigurazione = conf;
	}

	public void setLmanagerdisistema(LManagerDiSistema mansis) {
		this.lmanagerdisistema = mansis;
	}

	public void setSmanagerdisistema(SManagerDiSistema mansis) {
		this.smanagerdisistema = mansis;
	}
	
	public void setLmanagerdicompetizione(LManagerDiCompetizione lmanagerdicompetizione) {
		this.lmanagerdicompetizione = lmanagerdicompetizione;
	}

	public void setSmanagerdicompetizione(SManagerDiCompetizione smanagerdicompetizione) {
		this.smanagerdicompetizione = smanagerdicompetizione;
	}
}