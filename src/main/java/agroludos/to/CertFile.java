package agroludos.to;

import java.io.File;
/**
 * L'interfaccia rappresenta il certificato associato ad ogni partecipante
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface CertFile {
	
	/**
	 * Inserisce il certificato
	 * @param certificato che rappresenta il certificato di un partecipante
	 */
	public void setFile(File certificato);

	/**
	 * 
	 * @return percorso del certificato
	 */
	public String getName();

}