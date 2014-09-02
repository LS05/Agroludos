package agroludos.to;

import java.util.Date;

class Iscrizione implements IscrizioneTO{

	private Date data;
	private Integer id;
	private Integer stato;
	
	@Override
	public Date getData() {
		return data;
	}
	@Override
	public void setData(Date data) {
		this.data = data;
	}
	@Override
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public Integer getStato() {
		return stato;
	}
	@Override
	public void setStato(Integer stato) {
		this.stato = stato;
	}

}
