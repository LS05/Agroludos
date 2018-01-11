package agroludos.to;

/**
 * L'interfaccia rappresenta l'entit&agrave Certificato. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti unn certificato).
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface CertificatoTO extends AgroludosTO{
	
	/**
	 * 
	 * @return un'istanza di {@link CertFile}
	 */
	CertFile getCertificatoFile();
	
	/**
	 * Il metodo inserisce un {@link CertFile} all'interno del {@link CertificatoTO}
	 * @param cert
	 */
	void setCertificatoFile(CertFile cert);
	
	/**
	 * TODO
	 * @return
	 */
	String getCertificatoCont();
	
	/**
	 * TODO
	 * @param cont
	 */
	void setCertificatoCont(String cont);
}
