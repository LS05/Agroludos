package agroludos.to;

/**
 * Implementazione dell'interfaccia {@link StatoUtenteTO}. Alcuni metodi non sono resi pubblici nell'interfaccia, in quanto chiamati
 * da Hibernate per settare o leggere i campi in base a quelli presenti nella tabella StatoUtente.
 * .
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class StatoUtente implements StatoUtenteTO{

	private static final long serialVersionUID = 1L;
	private String nome;
	private Integer id;

	/**
	 * Utilizzato da hibernate per settare l'id dello stato tramite mapping
	 * @param nome
	 */
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

	/**
	 * Utilizzato da hibernate per settare il nome dello stato tramite mapping
	 * @param nome
	 */
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