package agroludos.to;

/**
 * Implementazione dell'interfaccia {@link ManagerDiCompetizioneTO}.
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
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
	public String toString() {
		return "ManagerDiCompetizione [stipendio=" + stipendio + ", id=" + id
				+ ", nome=" + nome + ", cognome=" + cognome + ", username="
				+ username + ", password=" + password + ", email=" + email
				+ "]";
	}
	
	
}