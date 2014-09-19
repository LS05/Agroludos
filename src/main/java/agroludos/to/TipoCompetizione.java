package agroludos.to;

class TipoCompetizione implements TipoCompetizioneTO{
	private static final long serialVersionUID = -5721300020452970477L;
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
	public int compareTo(TipoCompetizioneTO o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return "TipoCompetizione [descrizione=" + descrizione + ", nome="
				+ nome + ", id=" + id + "]";
	}
}