package agroludos.integration.dao.file.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import agroludos.integration.dao.file.CertificatoSRCDAO;
import agroludos.system.Conf;
import agroludos.to.CertFile;
import agroludos.to.CertificatoTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.TOFactory;

public class TxtCertificatoSRCDAO implements CertificatoSRCDAO{

	private TOFactory toFact;
	private Conf sysConf;

	TxtCertificatoSRCDAO(TOFactory toFact, Conf sysConf){
		this.toFact = toFact;
		this.sysConf = sysConf;
	}

	private String getCertPath(PartecipanteTO partTO){
		StringBuilder certPath = new StringBuilder(100);
		certPath.append(this.sysConf.getString("certPath"));
		certPath.append(partTO.getUsername());
		return certPath.toString();
	}

	private String getCertCont(CertificatoTO certTO, String certPath) throws IOException{
		String certificato = "";
		BufferedReader br = null;
		StringBuilder certCont = new StringBuilder(300);

		br = new BufferedReader(new FileReader(certPath));
		certCont = new StringBuilder(300);
		String line = br.readLine();

		while (line != null) {
			certCont.append(line);
			certCont.append(System.lineSeparator());
			line = br.readLine();
		}

		certificato = certCont.toString();

		if( br != null )
			br.close();

		return certificato;
	}

	@Override
	public CertificatoTO getCertificato(PartecipanteTO partTO) {
		CertFile certFile = this.toFact.createCertFile();
		CertificatoTO certTO = this.toFact.createCertificatoTO();

		String certPath = Paths.get(this.getCertPath(partTO)).toString();
		String certificato = "";

		try{
			try{
				certificato = this.getCertCont(certTO, certPath);
				certFile.setFile(new File(certPath));
				certTO.setCertificatoFile(certFile);
				certTO.setCertificatoCont(certificato);

			} catch(IOException e){
				//TODO loggare l'ioException
				certificato = this.sysConf.getString("srcContError");
				certTO.setCertificatoCont(certificato);
			}
		} catch(Exception e){
			e.printStackTrace();
		}

		return certTO;
	}

	//TODO rivedere IOException
	@Override
	public void salvaCertificato(PartecipanteTO partTO) throws IOException {
		String certInputPath = Paths.get(this.getCertPath(partTO)).toString();
		CertificatoTO certTO = this.toFact.createCertificatoTO();
		CertFile partCertFile = this.toFact.createCertFile();

		try{
			File userFile = new File(partTO.getSrc());

			String userFileName = FilenameUtils.getName(userFile.toString());

			File outDir = new File(certInputPath);

			FileUtils.deleteDirectory(outDir);
			FileUtils.forceMkdir(outDir);
			FileUtils.copyFileToDirectory(userFile, outDir);

			Path tempFilePath = Paths.get(outDir.toString() + "/" + userFileName);
			File outTempFile = FileUtils.getFile(tempFilePath.toString());
			String tempFileDir = FilenameUtils.getPath(outTempFile.getPath());

			StringBuilder certPath = new StringBuilder(100);
			certPath.append(tempFileDir);
			certPath.append(this.sysConf.getString("certFile"));
			
			String renamePath = certPath.toString();
			File certFile = new File(renamePath);
			outTempFile.renameTo(certFile);

			certTO.setCertificatoCont(this.getCertCont(certTO, renamePath));
			partCertFile.setFile(certFile);
			certTO.setCertificatoFile(partCertFile);
			partTO.setCertificato(certTO);

			partTO.setSrc(certPath.toString());
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}