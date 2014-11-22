package agroludos.system;

/**
 * Permette di accedere al file di proprietà di sistema per ottenere informazioni
 * quali: tipo di database usato, tipo file del certificato e lingua del sistema.
 * 
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