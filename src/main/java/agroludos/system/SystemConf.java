package agroludos.system;

/**
 * Gestisce il file di configurazione di sistema
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface SystemConf extends Conf{

	/**
	 * 
	 * @return il tipo di Data Base
	 */
	String getTipoDB();

	/**
	 * 
	 * @return il tipo di Configurazione
	 */
	String getTipoConf();

	/**
	 * 
	 * @return il tipo del file di certificato
	 */
	String getTipoCert();
	
	/**
	 * 
	 * @return la lingua
	 */
	String getLang();

}