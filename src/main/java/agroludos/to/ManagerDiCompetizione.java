package agroludos.to;

class ManagerDiCompetizione extends Utente implements ManagerDiCompetizioneTO {

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
		return "[stipendio=" + stipendio + ", id=" + id + ", nome=" + nome
				+ ", cognome=" + cognome + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", idruolo="
				+ idruolo + ", stato=" + stato + ", nomeRuolo=" + nomeRuolo
				+ ", nomeStatoUtente=" + nomeStatoUtente + "]";
	}
}