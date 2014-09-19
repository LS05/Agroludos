package agroludos.to;

public class StatoUtente implements StatoUtenteTO{

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
		return "StatoUtente [nome=" + nome + ", id=" + id + "]";
	}
	
	@Override
	public int compareTo(StatoUtenteTO o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
