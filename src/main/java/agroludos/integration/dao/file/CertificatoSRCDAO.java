package agroludos.integration.dao.file;

import java.io.IOException;

public interface CertificatoSRCDAO {
	
	public String getCertificato(String path) throws IOException;
	
	public void setCertificato(String inputPath, String outputPath) throws IOException;
	
}
