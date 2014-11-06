package agroludos.to;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class ManagerDiCompetizione extends Utente implements ManagerDiCompetizioneTO {
	private static final long serialVersionUID = 8563751281065371435L;
	private Double stipendio;
	
	private Set<Competizione> competizioni;

	@Override
	public Double getStipendio() {
		return this.stipendio;
	}

	@Override
	public void setStipendio(Double stipendio) {
		this.stipendio = stipendio;
	}
	
	@Override
	public List<CompetizioneTO> getAllCompetizioni() {
		List<CompetizioneTO> res = new ArrayList<CompetizioneTO>();
		
		for(Competizione item : this.competizioni){
			res.add(item);
		}
		
		return res;
	}
	
	public Set<Competizione> getCompetizioni() {
		return this.competizioni;
	}
	
	public void setCompetizioni(Set<Competizione> competizioni) {
		this.competizioni = competizioni;
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