package agroludos.to;

class Optional implements OptionalTO{
	private static final long serialVersionUID = 4962907532320055278L;
	private String nome;
	private String descrizione;
	private Double costo;
	private Integer id;
	private TipoOptional tipoOptional;
	private StatoOptional statoOptional;

	@Override
	public TipoOptional getTipoOptional() {
		return tipoOptional;
	}

	@Override
	public void setTipoOptional(TipoOptionalTO tipoOptional) {
		this.tipoOptional = (TipoOptional)tipoOptional;
	}

	@Override
	public StatoOptional getStatoOptional() {
		return statoOptional;
	}
	
	@Override
	public void setStatoOptional(StatoOptionalTO statoOptional) {
		this.statoOptional = (StatoOptional)statoOptional;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	public String getDescrizione() {
		return descrizione;
	}

	@Override
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public Double getCosto() {
		return costo;
	}

	@Override
	public void setCosto(Double costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "Optional [nome=" + nome + ", descrizione=" + descrizione
				+ ", costo=" + costo + ", id=" + id + ", tipoOptional="
				+ tipoOptional + ", statoOptional=" + statoOptional + "]";
	}

	@Override
	public int compareTo(OptionalTO o) {
		// TODO Auto-generated method stub
		return 0;
	}
}