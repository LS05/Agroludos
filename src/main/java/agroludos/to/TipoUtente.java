package agroludos.to;

/**
 * Implementazione dell'interfaccia {@link TipoUtenteTO}. Alcuni metodi non sono resi pubblici nell'interfaccia, in quanto chiamati
 * da Hibernate per settare o leggere i campi in base a quelli presenti nella tabella TipoUtente.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class TipoUtente implements TipoUtenteTO {

	private static final long serialVersionUID = 1L;
	private String nome;
	private Integer id;

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
