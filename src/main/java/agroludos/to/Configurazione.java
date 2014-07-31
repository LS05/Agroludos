package agroludos.to;

class Configurazione implements ConfigurazioneTO{
	private String pathConf;
	private int idConf;
	private boolean statoConf;

	private String tipoDB;
	private String nomeDB;
	private String serverDB;
	private String portaDB;
	private String userDB;
	private String pwdDB;
	
	public String getTipoDB() {
		return tipoDB;
	}

	public void setTipoDB(String tipoDB) {
		this.tipoDB = tipoDB;
	}

	public String getNomeDB() {
		return nomeDB;
	}

	public void setNomeDB(String nomeDB) {
		this.nomeDB = nomeDB;
	}

	public String getServerDB() {
		return serverDB;
	}

	public void setServerDB(String serverDB) {
		this.serverDB = serverDB;
	}

	public String getPortaDB() {
		return portaDB;
	}

	public void setPortaDB(String portaDB) {
		this.portaDB = portaDB;
	}

	public String getUserDB() {
		return userDB;
	}

	public void setUserDB(String userDB) {
		this.userDB = userDB;
	}

	public String getPwdDB() {
		return pwdDB;
	}

	public void setPwdDB(String pwdDB) {
		this.pwdDB = pwdDB;
	}

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
