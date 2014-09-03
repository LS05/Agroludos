package agroludos.to;

class TipoCompetizione implements TipoCompetizioneTO{
	private String descrizione;
	private String nome;
	private Integer id;
	

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
	@Override
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "TipoCompetizione [nome=" + nome + ", descrizione="
				+ descrizione + "]";
	}
	
	
	
}
