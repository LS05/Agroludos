package agroludos.integration.dao.file;

import java.io.IOException;

import agroludos.to.CertificatoTO;
import agroludos.to.PartecipanteTO;

/** 
 * Data Access Object per tutte le operazioni CRUD per quanto riguarda il certificato di sana e 
 * robusta competizione.
 * Sono presenti i metodi di lettura e scrittura.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface CertificatoSRCDAO {
	
	/**
	 * Restituisce il file del certificato del partecipante in input
	 * @param partTO
	 * @return
	 */
	public CertificatoTO getCertificato(PartecipanteTO partTO);
	
	/**
	 * Salva certificato del partecipante in input
	 * @param partTO
	 * @return
	 * @throws IOException
	 */
	public CertificatoTO salvaCertificato(PartecipanteTO partTO) throws IOException;
	
}
