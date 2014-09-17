package agroludos.to;

class ManagerDiSistema extends Utente implements ManagerDiSistemaTO {
	
	private String indirizzo;
	
	@Override
	public String getIndirizzo() {
		return indirizzo;
	}

	@Override
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", nome=" + nome
				+ ", cognome=" + cognome + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", idruolo="
				+ idruolo + ", stato=" + stato + ", nomeRuolo=" + nomeRuolo
				+ ", nomeStatoUtente=" + nomeStatoUtente + "indirizzo=" + indirizzo + "]";
	}
}