package agroludos.to;

class StatoOptional implements StatoOptionalTO{

	private static final long serialVersionUID = 1L;
	private String nome;
	private Integer id;

	@Override
	public Integer getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getNome() {
		return nome;
	}
	
	@SuppressWarnings("unused")
	private void setNome(String nome){
		this.nome = nome;
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