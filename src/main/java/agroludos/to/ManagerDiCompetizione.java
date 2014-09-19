package agroludos.to;

class ManagerDiCompetizione extends Utente implements ManagerDiCompetizioneTO {
	private static final long serialVersionUID = 8563751281065371435L;
	private Double stipendio;

	@Override
	public Double getStipendio() {
		return this.stipendio;
	}

	@Override
	public void setStipendio(Double stipendio) {
		this.stipendio = stipendio;
	}

	@Override
	public int compareTo(UtenteTO o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "ManagerDiCompetizione [stipendio=" + stipendio + ", id=" + id
				+ ", nome=" + nome + ", cognome=" + cognome + ", username="
				+ username + ", password=" + password + ", email=" + email
				+ "]";
	}
	
	
}