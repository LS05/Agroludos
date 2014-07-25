package agroludos.business.as.gestoreconfigurazione;

public class IntConfigurazione {
	private static ASConfigurazione asConf = new ASConfigurazione();
	
	public static LConfigurazione getLConfigurazioneI(){
		return asConf;
	}
	
}
