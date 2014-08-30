package agroludos.presentation.views.events;

import agroludos.to.AgroludosTO;
import agroludos.to.ManagerDiCompetizioneTO;

public class EditEvent {
	AgroludosTO data;
	
	public EditEvent(AgroludosTO agto){
		this.data = agto;
	}
	
	public ManagerDiCompetizioneTO getManagerData(){
		return (ManagerDiCompetizioneTO)data;
	}
}