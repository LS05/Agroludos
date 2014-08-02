package agroludos.business.as.gestoremds;

public class IntManagerDiSistema {
	private static ASGestoreManagerDiSistema asMdS = new ASGestoreManagerDiSistema();

	public static LManagerDiSistema getLManagerDiSistema(){
		return asMdS;
	}

	public static SManagerDiSistema getSManagerDiSistema(){
		return asMdS;
	}
}
