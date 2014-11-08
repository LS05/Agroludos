package agroludos.integration.dao.file.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
		certPath.append(this.sysConf.getString("certFile"));
		return certPath.toString();
	}

	@Override
	public CertificatoTO getCertificato(PartecipanteTO partTO) {
		CertFile certFile = this.toFact.createCertFile();
		CertificatoTO certTO = this.toFact.createCertificatoTO();
		StringBuilder certCont = new StringBuilder();

		String certPath = Paths.get(this.getCertPath(partTO)).toString();

		BufferedReader br = null;
		String certificato = "";

		try{
			try{
				br = new BufferedReader(new FileReader(certPath));
				certCont = new StringBuilder(300);
				String line = br.readLine();

				while (line != null) {
					certCont.append(line);
					certCont.append(System.lineSeparator());
					line = br.readLine();
				}

				certificato = certCont.toString();

				certFile.setFile(new File(certPath));
				certTO.setCertificatoFile(certFile);
				certTO.setCertificatoCont(certificato);

			} catch(IOException e){
				//TODO loggare l'ioException
				certificato = this.sysConf.getString("srcContError");
				certTO.setCertificatoCont(certificato);
			} finally{
				if( br != null )
					br.close();
			}
		} catch(Exception e){
			e.printStackTrace();
		}

		return certTO;
	}

	//TODO rivedere IOException
	@Override
	public void salvaCertificato(PartecipanteTO partTO) throws IOException {
		String certPath = Paths.get(this.getCertPath(partTO)).toString();

		File in = new File(partTO.getSrc());

		String inFileName = FilenameUtils.getName(in.toString());

		File out = new File(certPath);
		try{
			FileUtils.deleteDirectory(out);
			FileUtils.forceMkdir(out);
			FileUtils.copyFileToDirectory(in, out);
		} catch(IOException e){
			e.printStackTrace();
		}

		File outFileName = FileUtils.getFile(out.toString() + "/" + inFileName);
		String absCertPath = FilenameUtils.getFullPath(outFileName.getAbsolutePath());

		String renamePath = absCertPath + "certificato.txt";
		outFileName.renameTo(new File(renamePath));

		partTO.setSrc(out.toString());
	}
}