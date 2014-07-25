package agroludos.business.bd;

public class BDFactory {
	public static BusinessDelegate getBD(){
		return new AdisysBD();
	}
}
