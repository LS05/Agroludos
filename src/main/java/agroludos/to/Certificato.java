package agroludos.to;
/**
 * Implementazione dell'interfaccia {@link CertificatoTO}.
 * .
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class Certificato implements CertificatoTO{
	private static final long serialVersionUID = -5477389191603846187L;
	private String certCont;
	private CertFile certFile;
	
	@Override
	public String getCertificatoCont() {
		return this.certCont;
	}

	@Override
	public void setCertificatoCont(String cont) {
		this.certCont = cont;
	}

	@Override
	public CertFile getCertificatoFile() {
		return this.certFile;
	}

	@Override
	public void setCertificatoFile(CertFile cert) {
		this.certFile = cert;
	}

}
