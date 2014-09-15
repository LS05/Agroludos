package agroludos.to;

class ManagerDiSistema extends Utente implements ManagerDiSistemaTO {
	
	private String indirizzo;

	ManagerDiSistema(){
		super();
	}
	
	@Override
	public String toString() {
		return "[Manager Di Sistema: " + super.toString() + "]";
	}
	
	@Override
	public String getIndirizzo() {
		return indirizzo;
	}

	@Override
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
}