package agroludos.integration.dao.certificato;

import agroludos.factory.Factory;
import agroludos.integration.dao.txt.TxtDAOFactory;
import agroludos.integration.dao.xml.XmlDAOFactory;
import agroludos.system.SystemConf;

class CertificatoDAO extends Factory {
	private static SystemConf sysConf = SystemConf.getInstance();
	private static CertificatoDAO cerInst;
	private static CertificatoDAOFactory dao;
	
	private CertificatoDAO() {
		super("file");
		String tipoDB = sysConf.getTipoDB();
		Class<? extends CertificatoDAOFactory> c = this.getClass("agroludos.integration.dao." + tipoDB, this.initData(tipoDB));
		
		try {
			dao = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static CertificatoDAO getInstance(){
		if(cerInst == null)
			cerInst = new CertificatoDAO();
		return cerInst;
	}

	public static CertificatoDAOFactory getDAOFactory(){
		return dao;
	}
	
	protected String getXMLPath(){
		return this.getClass().getResource("FileDAOFactory.xml").toString();
	}
}