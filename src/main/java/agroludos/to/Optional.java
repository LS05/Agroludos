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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Optional other = (Optional) obj;
		if(costo == other.costo){
			if( descrizione == other.descrizione){
				if( nome == other.nome){
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}	

	@Override
	public int compareTo(OptionalTO o) {
		return this.nome.compareTo(o.getNome());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[id=").append(id).append(", nome=").append(nome)
				.append(", descrizione=").append(descrizione)
				.append(", costo=").append(costo).append(", tipoOptional=")
				.append(tipoOptional).append(", statoOptional=")
				.append(statoOptional).append("]");
		return builder.toString();
	}

}