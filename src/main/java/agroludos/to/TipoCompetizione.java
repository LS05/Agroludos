package agroludos.to;

public class TipoCompetizione implements TipoCompetizioneTO{
	private String descrizione;
	private String nome;
	
	@Override
	public String getDescrizione() {
		return descrizione;
	}
	@Override
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public String getNome() {
		return nome;
	}
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
}
