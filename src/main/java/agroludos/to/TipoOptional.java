package agroludos.to;

/**
 * Implementazione dell'interfaccia {@link TipoOptionalTO}. Alcuni metodi non sono resi pubblici nell'interfaccia, in quanto chiamati
 * da Hibernate per settare o leggere i campi in base a quelli presenti nella tabella TipoOptional.
 * .
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class TipoOptional implements TipoOptionalTO{
	private static final long serialVersionUID = -9220739058941615067L;
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

	/**
	 * Utilizzato da hibernate per settare l'id tramite mapping
	 * @param id
	 */
	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int compareTo(TipoOptionalTO o) {
		return this.getNome().compareTo(o.getNome());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		TipoOptional other = (TipoOptional) obj;
		if (!nome.equalsIgnoreCase(other.getNome()))
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return "TipoOptional [nome=" + nome + ", id=" + id + "]";
	}
}