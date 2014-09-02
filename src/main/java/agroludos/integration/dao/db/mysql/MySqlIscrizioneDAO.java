package agroludos.integration.dao.db.mysql;

import org.hibernate.Query;

import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.to.IscrizioneTO;

class MySqlIscrizioneDAO extends MySqlAgroludosDAO implements IscrizioneDAO{

	@Override
	public boolean crea(IscrizioneTO iscto) {
		boolean res = false;
		// TODO Aggiungere gestione eccezioni hibernate
		this.session.beginTransaction();
		this.session.save(iscto);
		res = true;
		this.session.getTransaction().commit();
		return res;
	}

	@Override
	public boolean update(IscrizioneTO iscto) {
		boolean res = false;
		this.session.beginTransaction();

		Query query = session.getNamedQuery("updateIscrizione");
		query.setParameter("data", iscto.getData());
		query.setParameter("stato", iscto.getStato());
		query.setParameter("id", iscto.getId());

		if(query.executeUpdate() == 1){
			res = true;
		}

		this.session.getTransaction().commit();

		return res;
	}

	@Override
	public boolean annullaIscrizione(IscrizioneTO iscto) {
		boolean res = false;
		this.session.beginTransaction();

		Query query = session.getNamedQuery("annullaIscrizione");
		query.setParameter("stato", 0);
		query.setParameter("id", iscto.getId());

		if(query.executeUpdate() == 1){
			res = true;
		}

		this.session.getTransaction().commit();

		return res;
	}

}
