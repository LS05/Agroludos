package agroludos.integration.dao.file;

import java.io.IOException;

import agroludos.to.CertificatoTO;
import agroludos.to.PartecipanteTO;

public interface CertificatoSRCDAO {
	
	public CertificatoTO getCertificato(PartecipanteTO partTO) throws IOException;
	
	public void salvaCertificato(PartecipanteTO partTO) throws IOException;
	
}
