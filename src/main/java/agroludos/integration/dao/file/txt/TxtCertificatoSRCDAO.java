package agroludos.integration.dao.file.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import agroludos.integration.dao.file.CertificatoSRCDAO;

public class TxtCertificatoSRCDAO implements CertificatoSRCDAO{

	@Override
	//TODO Non dovrebbe ritornare un CertificatoTO?
	public String getCertificato(String path) throws IOException {
		BufferedReader br = null;
		String certificato = "";

		try{
			br = new BufferedReader(new FileReader(path));
			StringBuilder sb = new StringBuilder(300);
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}

			certificato = sb.toString();
		} catch(IOException e){
			throw e;
		} finally{
			br.close();
		}

		return certificato;
	}

	@Override
	public void setCertificato(String inputPath, String outputPath) throws IOException {
		File in = new File(inputPath);
		File out = new File(outputPath);
		FileUtils.copyFileToDirectory(in, out);
	}
}