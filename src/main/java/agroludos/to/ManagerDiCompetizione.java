package agroludos.to;

class ManagerDiCompetizione extends Utente implements ManagerDiCompetizioneTO {
	
	ManagerDiCompetizione(){
		super();
	}
	
	@Override
	public String toString() {
		return "[Manager Di Competizione: " + super.toString() + "]";
	}
	
}