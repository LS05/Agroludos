package agroludos.integration.dao.file.xml;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import agroludos.integration.dao.file.FConfigurazioneDAO;
import agroludos.system.HibernateConf;
import agroludos.to.DatabaseTO;
import agroludos.utility.xml.XmlUtil;

class XmlConfigurazioneDAO implements FConfigurazioneDAO{

	private Document doc;

	private String confPath;

	private XmlUtil utXml;

	private HibernateConf sysConf;

	XmlConfigurazioneDAO(HibernateConf sysConf, XmlUtil utXml){
		this.utXml = utXml;
		this.sysConf = sysConf;
		this.confPath = sysConf.getConfPath();
		this.doc = this.utXml.getDocument(this.confPath);
	}

	@Override
	public boolean creaConfigurazione(DatabaseTO dbto) {

		boolean res = false;

		if(this.doc != null){
			res = XmlConfigFile.setHibernateDocument(this.doc, this.sysConf, dbto);
			try {
				this.utXml.writeFile(this.doc, this.confPath);
			} catch (TransformerException e) {
				res = false;
				e.printStackTrace();
			}
		} else {
			res = false;
		}

		return res;
	}
}