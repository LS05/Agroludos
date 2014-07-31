package agroludos.business.as.gestoreconfigurazione;

public class IntConfigurazione {
	private static ASGestoreConfigurazione asConf = new ASGestoreConfigurazione();

	public static LConfigurazione getLConfigurazioneI(){
		return asConf;
	}

	public static SConfigurazione getSConfigurazioneI(){
		return asConf;
	}
}
