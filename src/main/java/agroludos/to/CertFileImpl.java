package agroludos.to;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
/**
 * Implementazione dell'interfaccia {@link CertFile}. 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class CertFileImpl implements CertFile{

	private File certificato;

	@Override
	public void setFile(File certificato){
		this.certificato = certificato;
	}

	@Override
	public String getName() {
		String path = "";
		if( this.certificato != null){
			path = FilenameUtils.getBaseName(this.certificato.getAbsolutePath());
		}
		return path;
	}
}