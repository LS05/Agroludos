package agroludos.presentation.controller;

class ACFactoryImpl implements ACFactory{

	private final static ApplicationControllerImpl applicationController = new ApplicationControllerImpl();

	@Override
	public ApplicationController getAC(){
		return applicationController;
	}
}