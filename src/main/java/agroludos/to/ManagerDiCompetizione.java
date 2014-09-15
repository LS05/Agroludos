package agroludos.to;

class ManagerDiCompetizione extends Utente implements ManagerDiCompetizioneTO {
	
	private Double stipendio;
	
	ManagerDiCompetizione(){
		super();
	}
	
	@Override
	public String toString() {
		return "[Manager Di Competizione: " + super.toString() + "]";
	}

	@Override
	public Double getStipendio() {
		return this.stipendio;
	}

	@Override
	public void setStipendio(Double stipendio) {
		this.stipendio = stipendio;
	}
	
}