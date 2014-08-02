package agroludos.integration.dao.xml;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;

import agroludos.integration.dao.config.FConfigurazioneDAO;
import agroludos.to.DatabaseTO;
import agroludos.utility.xml.XmlUtil;

public class XmlConfigurazioneDAO implements FConfigurazioneDAO{

	private Document doc;

	private String confPath = XmlData.getConfPath();

	private XMLConfigFile xmlFile;

	private XmlUtil utXml = new XmlUtil();


	XmlConfigurazioneDAO(){
		this.doc = utXml.getDocument(this.confPath);
		this.xmlFile = XMLConfigFile.getInstance();
	}

	@Override
	public boolean creaConfigurazione(DatabaseTO dbto) {

		boolean res = false;

		if(this.doc != null){
			res = xmlFile.setDocument(this.doc, dbto);
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
}