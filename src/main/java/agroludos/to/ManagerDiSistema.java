package agroludos.to;

class ManagerDiSistema extends Utente implements ManagerDiSistemaTO {
	
	ManagerDiSistema(){
		super();
	}
	
	@Override
	public String toString() {
		return "[Manager Di Sistema: " + super.toString() + "]";
	}
	
}