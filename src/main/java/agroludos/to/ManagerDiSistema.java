package agroludos.to;

class ManagerDiSistema extends Utente implements ManagerDiSistemaTO {
	private static final long serialVersionUID = -7505581083991050221L;
	private String indirizzo;
	private String telefono;
	
	@Override
	public String getIndirizzo() {
		return indirizzo;
	}

	@Override
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Override
	public String getTelefono() {
		return telefono;
	}

	@Override
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
		return "ManagerDiSistema [indirizzo=" + indirizzo + ", id=" + id
				+ ", nome=" + nome + ", cognome=" + cognome + ", username="
				+ username + ", password=" + password + ", email=" + email
				+ "]";
	}

	
}