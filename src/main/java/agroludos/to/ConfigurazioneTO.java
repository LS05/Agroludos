package agroludos.to;

public interface ConfigurazioneTO {
	
	String getPathConf();
	boolean getStatoConf();
	void setStatoConf(boolean statoConf);
	void setPathConf(String path);
	public String getTipoDB();
	public void setTipoDB(String tipoDB);
	public String getNomeDB();
	public void setNomeDB(String nomeDB);
	public String getServerDB();
	public void setServerDB(String serverDB);
	public String getPortaDB();
	public void setPortaDB(String portaDB);
	public String getUserDB();
	public void setUserDB(String userDB);
	public String getPwdDB();
	public void setPwdDB(String pwdDB);
	
}
