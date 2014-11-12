package agroludos.to;

class TipoUtente implements TipoUtenteTO {

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

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "TipoUtente [nome=" + nome + ", id=" + id + "]";
	}

	@Override
	public int compareTo(TipoUtenteTO o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
