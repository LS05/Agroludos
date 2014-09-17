package agroludos.to;

import java.io.Serializable;

/**
 * L'interfaccia ha lo scopo di uniformare tutti i transfer-object utilizzati
 * nell'applicazione.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Marker_interface_pattern">http://en.wikipedia.org/wiki/Marker_interface_pattern</a>
 */

public interface AgroludosTO extends Serializable{
	String toString();
}