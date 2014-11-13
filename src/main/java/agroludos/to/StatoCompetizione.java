package agroludos.to;

class StatoCompetizione implements StatoCompetizioneTO{
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

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public int compareTo(StatoCompetizioneTO o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(100);
		builder.append("[id=").append(id).append(", nome=").append(nome)
		.append("]");
		return builder.toString();
	}
}