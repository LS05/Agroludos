package agroludos.to;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IscrizioneTO extends AgroludosTO, Comparable<IscrizioneTO>{

	public Date getData();

	public void setData(Date data);

	public Integer getId();

	List<OptionalTO> getAllOptionals();

	StatoIscrizione getStatoIscrizione();

	PartecipanteTO getPartecipante();

	CompetizioneTO getCompetizione();

	void setStatoIscrizione(StatoIscrizioneTO statoIscrizione);

	void clearOptionals();

	void addOptional(OptionalTO optional);

	void setCompetizione(CompetizioneTO competizione);

	void setPartecipante(UtenteTO partecipante);

	void setOptionals(Set<OptionalTO> optionals);

	void setCosto(Double costoIsc);

	Double getCosto();

}