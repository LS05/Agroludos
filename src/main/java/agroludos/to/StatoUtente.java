package agroludos.to;

class StatoUtente implements StatoUtenteTO{

	private static final long serialVersionUID = 1L;
	private String nome;
	private Integer id;

	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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