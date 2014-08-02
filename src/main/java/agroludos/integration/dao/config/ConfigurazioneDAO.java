package agroludos.integration.dao.config;

import agroludos.factory.Factory;
import agroludos.integration.dao.AgroludosDAO;
import agroludos.integration.dao.IntAgroludosDAO;
import agroludos.system.SystemConf;

class ConfigurazioneDAO extends Factory{
	private static SystemConf sysConf = SystemConf.getInstance();
	private static ConfigurazioneDAOFactory dao;
	private static ConfigurazioneDAO confInst;
	private static AgroludosDAO agDAO;
	private static String tipoFile;
	private ConfigurazioneDAO(){ 
		super("file");
		agDAO = IntAgroludosDAO.getAgroludosDAOI();
		tipoFile = sysConf.getTipoConf();
		Class<? extends ConfigurazioneDAOFactory> c = this.getClass(getClassPath(), this.initData(tipoFile));
		
		try {
			dao = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ConfigurazioneDAO getInstance(){
		if(confInst == null)
			confInst = new ConfigurazioneDAO();
		return confInst;
	}

	
	public static ConfigurazioneDAOFactory getDAOFactory(){
		return dao;
	}
	
	private String getClassPath(){
		return "agroludos.integration.dao." + tipoFile + ".";
	}

	protected String getXMLPath(){
		return agDAO.getFileXmlPath();
	}
}