package agroludos.system;

public interface SystemConf extends Conf{

	void setTipoDB(String tipoDB);

	String getTipoDB();

	String getTipoConf();

	String getTipoCert();
	
	String getLang();

}