package agroludos.presentation.controller;

class ApplicationControllerFactory implements ACFactory{

	private static AgroludosAC applicationController = new AgroludosAC();

	@Override
	public ApplicationController getAC(){
		return applicationController;
	}
}