package agroludos.presentation.controller;

/**
 * L'interfaccia rappresenta il Factory per l'application controller.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface ACFactory {
	
	/**
	 * Il metodo restituisce un'istanza di una classe che implementa l'interfaccia
	 * {@link ApplicationController}
	 * 
	 * @return Classe che implementa l'interfaccia {@link ApplicationController}
	 */
	ApplicationController getAC();
}