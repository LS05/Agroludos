package agroludos.to;

import java.util.Date;

/**
 * L'interfaccia rappresenta l'entit&agrave Partecipante. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti una competizione).
 * Estende l'interfaccia {@link UtenteTO}
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface PartecipanteTO extends UtenteTO {

	/**
	 * 
	 * @return codice fiscale del partecipante
	 */
	public String getCf();

	/**
	 * Inserisce il codice fiscale del partecipante
	 * @param cf
	 */
	public void setCf(String cf);

	/**
	 * 
	 * @return l'indirizzo del partecipante
	 */
	public String getIndirizzo();

	/**
	 * inserisce l'indirizzo del partecipante
	 * @param indirizzo
	 */
	public void setIndirizzo(String indirizzo);

	/**
	 * 
	 * @return il sesso del partecipante 
	 */
	public String getSesso();

	/**
	 * inserisce il sesso del partecipante
	 * @param sesso
	 */
	public void setSesso(String sesso);

	/**
	 * 
	 * @return il numero di tessera sanitaria
	 */
	public String getNumTS();

	/**
	 * Inserisce il numero di tessera sanitaria
	 * @param numTS
	 */
	public void setNumTS(String numTS);

	/**
	 * 
	 * @return a data di rilascio del certificato si sana e robusta costituzione
	 */
	public Date getDataSRC();

	/**
	 * Inserisce la data di rilascio del certificato SRC
	 * @param dataSRC
	 */
	public void setDataSRC(Date dataSRC);

	/**
	 * 
	 * @return un'istanz di {@link CertificatoTO} che contiene le informazioni del certificato SRC del partecipante
	 * @see CertificatoTO
	 */
	public CertificatoTO getCertificato();
	
	/**
	 * Inserisce il certificato associato al partecipante
	 * @param certificato
	 * @see CertificatoTO
	 */
	public void setCertificato(CertificatoTO certificato);
	
	/**
	 * 
	 * @return il percorso del file di certificato
	 */
	public String getSrc();
	
	/**
	 * Inserisce il percorso del file di certificato
	 * @param src
	 */
	public void setSrc(String src);

	/**
	 * 
	 * @return la data di nascita del partecipante
	 */
	public Date getDataNasc();

	/**
	 * inserisce la data di nascita del partecipante
	 * @param dataNasc
	 */
	public void setDataNasc(Date dataNasc);

}