package agroludos.to;

import java.util.Date;
import java.util.List;

public interface CompetizioneTO extends AgroludosTO{
	
	public String getNome();
	
	public void setNome(String nome);
	
	public Date getData();
	
	public void setData(Date data);
	
	public String getDescrizione();
	
	public void setDescrizione(String descrizione);
	
	public Double getCosto();
	
	public void setCosto(Double costo);
	
	Integer getStato();
	
	void setStato(Integer stato);

	Integer getTipo();
	
	void setTipo(Integer tipo);
	
	Integer getMdc();
	
	void setMdc(Integer mdc);
	
	Integer getId();
	
	int getNmin();
	
	void setNmin(int nmin);
	
	int getNmax();
	
	void setNmax(int nmax);
	
	void addOptional(OptionalTO optional);

	List<PartecipanteTO> getAllIscritti();

	List<OptionalTO> getAllOptionals();

	List<IscrizioneTO> getAllIscrizioni();
	
	String getNomeStato();
	String getNomeTipo();

	void setNomeStato(String nomeStato);

	void setNomeTipo(String nomeTipo);
	
	
}