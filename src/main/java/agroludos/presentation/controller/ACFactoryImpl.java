package agroludos.presentation.controller;

class ACFactoryImpl implements ACFactory{

	private final static ApplicationControllerImpl APPLICATION_CONTROLLER = new ApplicationControllerImpl();

	@Override
	public ApplicationController getAC(){
		return APPLICATION_CONTROLLER;
	}
}