package agroludos.to;


public interface TipoCompetizioneTO extends TipiAgroludosTO, AgroludosTO, Comparable<TipoCompetizioneTO>{
	
	public String getNome();
	
	public void setNome(String nome);
	
	public String getDescrizione();
	
	public void setDescrizione(String descrizione);
	
	public Integer getId();
}
