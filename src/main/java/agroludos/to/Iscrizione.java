package agroludos.to;

import java.util.Date;

class Iscrizione implements IscrizioneTO{

	private Date data;
	
	@Override
	public Date getData() {
		return data;
	}
	@Override
	public void setData(Date data) {
		this.data = data;
	}

}
