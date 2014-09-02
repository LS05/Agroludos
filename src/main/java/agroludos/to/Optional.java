package agroludos.to;

class Optional implements OptionalTO{

	private String nome;
	private String descrizione;
	private Double costo;

	private int stato;
	private int tipo;
	private int id;

	@Override
	public String getNome() {
		return nome;
	}
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public Integer getStato() {
		return stato;
	}
	@Override
	public void setStato(Integer stato) {
		this.stato = stato;
	}
	@Override
	public Integer getTipo() {
		return tipo;
	}
	@Override
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}



}
