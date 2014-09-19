package agroludos.to;

public class StatoOptional implements StatoOptionalTO{

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
	
	void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "StatoOptional [nome=" + nome + ", id=" + id + "]";
	}

	@Override
	public int compareTo(StatoCompetizioneTO o) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
