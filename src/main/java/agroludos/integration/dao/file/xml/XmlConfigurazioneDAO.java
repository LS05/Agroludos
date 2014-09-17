package agroludos.integration.dao.file.xml;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;

import agroludos.integration.dao.file.FConfigurazioneDAO;
import agroludos.to.DatabaseTO;
import agroludos.utility.xml.XmlUtil;

class XmlConfigurazioneDAO implements FConfigurazioneDAO{

	private Document doc;

	private String confPath;

	private XMLConfigFile xmlFile;

	private XmlUtil utXml;

	@Override
	public boolean creaConfigurazione(DatabaseTO dbto) {

		boolean res = false;

		if(this.doc != null){
			res = xmlFile.setHibernateDocument(this.doc, dbto);
			try {
				utXml.writeFile(this.doc, this.confPath);
			} catch (TransformerException e) {
				res = false;
				e.printStackTrace();
			}
		} else {
			res = false;
		}

		return res;
	}

	@Override
	public String getConfPath() {
		return this.confPath;
	}

	public void setUtXml(XmlUtil utXml) {
		this.utXml = utXml;
		this.doc = utXml.getDocument(this.confPath);
	}

	public void setXmlFile(XMLConfigFile xmlFile) {
		this.xmlFile = xmlFile;
		this.confPath = xmlFile.getConfPath();
	}
}