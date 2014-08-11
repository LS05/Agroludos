package agroludos.business.bd;

import agroludos.business.as.gestoreconfigurazione.LConfigurazione;
import agroludos.business.as.gestoreconfigurazione.SConfigurazione;
import agroludos.business.as.gestoremds.SManagerDiSistema;
import agroludos.business.as.gestoremds.LManagerDiSistema;
import agroludos.to.AgroludosTO;
import agroludos.to.DatabaseTO;
import agroludos.to.UtenteTO;

public class AgroludosBD extends BusinessDelegate{

	private LConfigurazione lconfigurazione;
	private SConfigurazione sconfigurazione;
	
	private LManagerDiSistema lmanagerdisistema;
	private SManagerDiSistema smanagerdisistema;

	AgroludosBD(){
		System.out.println("AgroludosBD");
	}
	
	public boolean checkConfigurazione() throws ApplicationException{
		boolean res = false;
		res = lconfigurazione.checkConfigurazione();
		return res;
	}
	
	public boolean confermaConfigurazione(DatabaseTO to) throws ApplicationException{
		System.out.println("confermaConfigurazione()");
		sconfigurazione.inserisciConfigurazione(to);
		return false;
	}

	public boolean testConnessioneDB(DatabaseTO dbto) throws ApplicationException{
		boolean res = false;
//		res = LConfigurazione.testDBConnection(dbto);
		return res;
	}

	public boolean creaConfigurazione(DatabaseTO dbto) throws ApplicationException {
		boolean res = false;
		res = sconfigurazione.inserisciConfigurazione(dbto);
//		res = LConfigurazione.testDBConnection(dbto);
		return res;
	}

	public boolean inserisciManagerDiSistema(UtenteTO uto) throws ApplicationException {
		boolean res = false;
		res = smanagerdisistema.inserisciManagerDiSistema(uto);
		return res;
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
}