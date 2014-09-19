package agroludos.to;

public class StatoCompetizione implements StatoCompetizioneTO{
	private static final long serialVersionUID = 1L;
	private String nome;
	private Integer id;
	
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
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return "StatoCompetizione [nome=" + nome + ", id=" + id + "]";
	}

	@Override
	public int compareTo(StatoCompetizioneTO o) {
		// TODO Auto-generated method stub
		return 0;
	}	
}
