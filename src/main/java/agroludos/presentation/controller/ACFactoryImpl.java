package agroludos.presentation.controller;

/**
 * Implementazione dell'interfaccia {@link ACFactory}
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ACFactoryImpl implements ACFactory{
	
	/**
	 * Istanza della classe ApplicationControllerImpl da restituire.
	 */
	private final static ApplicationControllerImpl APPLICATION_CONTROLLER = new ApplicationControllerImpl();

	@Override
	public ApplicationController getAC(){
		return APPLICATION_CONTROLLER;
	}
}