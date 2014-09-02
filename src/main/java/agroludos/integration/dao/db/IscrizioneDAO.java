package agroludos.integration.dao.db;


import agroludos.to.IscrizioneTO;

public interface IscrizioneDAO {
	boolean crea(IscrizioneTO iscto);

	boolean update(IscrizioneTO iscto);

	boolean annullaIscrizione(IscrizioneTO iscto);

}
