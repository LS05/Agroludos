package agroludos.integration.dao.db.mysql;

import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.UtenteTO;

public class MySqlManagerDiCompetizioneDAO extends MySqlAgroludosDAO implements ManagerDiCompetizioneDAO{

	@Override
	public boolean crea(ManagerDiCompetizioneTO mdcto) {
		boolean res = false;
		// TODO Aggiungere gestione eccezioni hibernate
		this.session.beginTransaction();
		this.session.save(mdcto);
		res = true;
		this.session.getTransaction().commit();
		return res;
	}

	@Override
	public ManagerDiCompetizioneTO read(UtenteTO uto) {
		// TODO Auto-generated method stub
		return null;
	}
	//private ServerTOFactory toFact = ServerTOFactory.getTOFactory();
}