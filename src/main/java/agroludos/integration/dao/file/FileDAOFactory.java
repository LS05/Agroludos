package agroludos.integration.dao.file;

public interface FileDAOFactory{
	FConfigurazioneDAO getConfigurazioneDAO();
	CertificatoSRCDAO getCertificatoSRCDAO();
}