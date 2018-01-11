package agroludos.to;

/**
 * Implementazione dell'interfaccia {@link TipoCompetizioneTO}. Alcuni metodi non sono resi pubblici nell'interfaccia, in quanto chiamati
 * da Hibernate per settare o leggere i campi in base a quelli presenti nella tabella TipoCompetizione.
 * .
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class TipoCompetizione implements TipoCompetizioneTO{
	private static final long serialVersionUID = -5721300020452970477L;
	private String descrizione;
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
	public String getDescrizione() {
		return descrizione;
	}

	@Override
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
	public int compareTo(TipoCompetizioneTO o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "TipoCompetizione [descrizione=" + descrizione + ", nome="
				+ nome + ", id=" + id + "]";
	}
}