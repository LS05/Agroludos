package agroludos.utility.xml;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

public interface XmlUtil {

	Document getDocument(String path);

	void writeFile(Document doc, String path) throws TransformerException;

}
