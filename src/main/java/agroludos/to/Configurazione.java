package agroludos.to;

class Configurazione implements ConfigurazioneTO{
	private String pathConf;
	private int idConf;
	private boolean statoConf;
	
	public boolean getStatoConf() {
		return statoConf;
	}

	public void setStatoConf(boolean statoConf) {
		this.statoConf = statoConf;
	}
	
	public String getPathConf() {
		return pathConf;
	}

	public void setPathConf(String pathConf) {
		this.pathConf = pathConf;
	}

	public int getIdConf() {
		return idConf;
	}

	public void setIdConf(int idConf) {
		this.idConf = idConf;
	}
}
