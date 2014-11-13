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
		StringBuilder builder = new StringBuilder(300);
		builder.append("[id=").append(id).append(", nome=").append(nome)
				.append(", cognome=").append(cognome).append(", email=")
				.append(email).append(", password=").append(password)
				.append(", username=").append(username).append(", indirizzo=")
				.append(indirizzo).append(", telefono=").append(telefono)
				.append("]");
		return builder.toString();
	}

}