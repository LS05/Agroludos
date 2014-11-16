package agroludos.to;

/**
 * Implementazione dell'interfaccia {@link StatoOptionalTO}. Alcuni metodi non sono resi pubblici nell'interfaccia, in quanto chiamati
 * da Hibernate per settare o leggere i campi in base a quelli presenti nella tabella StatoOptional.
 * .
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class StatoOptional implements StatoOptionalTO{

	private static final long serialVersionUID = 1L;
	private String nome;
	private Integer id;

	@Override
	public Integer getId() {
		return id;
	}

	/**
	 * Utilizzato da hibernate per settare l'id tramite mapping
	 * @param nome
	 */
	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getNome() {
		return nome;
	}
	
	/**
	 * Utilizzato da hibernate per settare il nome dello stato tramite mapping
	 * @param nome
	 */
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