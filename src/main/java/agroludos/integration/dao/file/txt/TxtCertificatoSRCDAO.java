package agroludos.integration.dao.file.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import agroludos.integration.dao.file.CertificatoSRCDAO;
import agroludos.to.CertFile;
import agroludos.to.CertificatoTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.TOFactory;

public class TxtCertificatoSRCDAO implements CertificatoSRCDAO{

	private TOFactory toFact;

	TxtCertificatoSRCDAO(TOFactory toFact){
		this.toFact = toFact;
	}

	@Override
	public CertificatoTO getCertificato(PartecipanteTO partTO) 
			throws IOException {
		StringBuilder certCont = new StringBuilder();
		StringBuilder certPath = new StringBuilder(100);

		certPath.append("utenti/certificati/");
		certPath.append(partTO.getUsername());
		certPath.append("/");
		certPath.append("certificato.txt");

		BufferedReader br = null;
		String certificato = "";

		try{
			br = new BufferedReader(new FileReader(certPath.toString()));
			certCont = new StringBuilder(300);
			String line = br.readLine();

			while (line != null) {
				certCont.append(line);
				certCont.append(System.lineSeparator());
				line = br.readLine();
			}

			certificato = certCont.toString();
		} catch(IOException e){
			throw e;
		} finally{
			br.close();
		}

		CertFile certFile = this.toFact.createCertFile();
		CertificatoTO certTO = this.toFact.createCertificatoTO();

		certFile.setFile(new File(certPath.toString()));
		certTO.setCertificatoFile(certFile);
		certTO.setCertificatoCont(certificato);

		return certTO;
	}

	@Override
	public void salvaCertificato(PartecipanteTO partTO) throws IOException {
		StringBuilder sb = new StringBuilder(100);
		sb.append("utenti/certificati/");
		sb.append(partTO.getUsername());
		sb.append("/");

		File in = new File(partTO.getSrc());

		String inFileName = FilenameUtils.getName(in.toString());

		File out = new File(sb.toString());
		try{
			FileUtils.deleteDirectory(out);
			FileUtils.forceMkdir(out);
			FileUtils.copyFileToDirectory(in, out);
		} catch(IOException e){
			e.printStackTrace();
		}

		File outFileName = FileUtils.getFile(out.toString() + "/" + inFileName);
		String certPath = FilenameUtils.getFullPath(outFileName.getAbsolutePath());
		
		String renamePath = certPath + "certificato.txt";
		outFileName.renameTo(new File(renamePath));
		
		partTO.setSrc(out.toString() + "/certificato.txt");
	}
}