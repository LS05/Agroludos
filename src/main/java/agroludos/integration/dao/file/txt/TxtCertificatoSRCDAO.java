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

class TxtCertificatoSRCDAO implements CertificatoSRCDAO{

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

	private String getCertCont(String certPath) throws IOException{
		String certificato = "";
		BufferedReader br = null;
		StringBuilder certCont = new StringBuilder(300);

		br = new BufferedReader(new FileReader(certPath));
		String line = br.readLine();

		while (line != null) {
			certCont.append(line);
			certCont.append(System.lineSeparator());
			line = br.readLine();
		}

		certificato = certCont.toString();

		if(br != null){
			br.close();
		}

		return certificato;
	}

	@Override
	public CertificatoTO getCertificato(PartecipanteTO partTO) {
		CertFile certFile = this.toFact.createCertFile();
		CertificatoTO certTO = this.toFact.createCertificatoTO();

		StringBuilder pathBuilder = new StringBuilder(300);
		pathBuilder.append(this.getCertPath(partTO)).toString();
		pathBuilder.append("/");
		pathBuilder.append(this.sysConf.getString("certFile"));

		String certPath = Paths.get(pathBuilder.toString()).toString();

		String certificato = "";

		try{
			try{
				certificato = this.getCertCont(certPath);
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
	public CertificatoTO salvaCertificato(PartecipanteTO partTO) throws IOException {
		String certInputPath = Paths.get(this.getCertPath(partTO)).toString();
		CertificatoTO certTO = this.toFact.createCertificatoTO();
		CertFile partCertFile = this.toFact.createCertFile();

		File userFile = new File(partTO.getSrc());

		String userFileName = FilenameUtils.getName(userFile.toString());

		File outDir = new File(certInputPath);

		FileUtils.deleteDirectory(outDir);
		FileUtils.forceMkdir(outDir);
		FileUtils.copyFileToDirectory(userFile, outDir);

		StringBuilder pathBuilder = new StringBuilder(300);
		pathBuilder.append(outDir.toString());
		pathBuilder.append("/");
		pathBuilder.append(userFileName);

		Path tempFilePath = Paths.get(pathBuilder.toString());
		File outTempFile = FileUtils.getFile(tempFilePath.toString());
		String tempFileDir = FilenameUtils.getPath(outTempFile.getPath());

		pathBuilder = new StringBuilder(100);
		pathBuilder.append(tempFileDir);
		pathBuilder.append(this.sysConf.getString("certFile"));

		String renamePath = pathBuilder.toString();
		File certFile = new File(renamePath);
		outTempFile.renameTo(certFile);

		partCertFile.setFile(certFile);
		certTO.setCertificatoFile(partCertFile);


		return certTO;
	}
}