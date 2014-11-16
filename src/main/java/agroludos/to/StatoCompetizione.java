package agroludos.to;

/**
 * Implementazione dell'interfaccia {@link StatoCompetizioneTO}. Alcuni metodi non sono resi pubblici nell'interfaccia, in quanto chiamati
 * da Hibernate per settare o leggere i campi in base a quelli presenti nella tabella.
 * .
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class StatoCompetizione implements StatoCompetizioneTO{
	private static final long serialVersionUID = 1L;
	private String nome;
	private Integer id;

	@Override
	public Integer getId() {
		return id;
	}

	/**
	 * Utilizzato da hibernate, setta l'id dello stato della competizione
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
	
	/**
	 * Utilizzato da hibernate per settare il nome dello stato tramite mapping
	 * @param nome
	 */
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