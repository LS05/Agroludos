package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Query;

import agroludos.integration.dao.db.UtenteDAO;
import agroludos.to.UtenteTO;

class MySqlUtenteDAO extends MySqlAgroludosDAO implements UtenteDAO {

	@Override
	public UtenteTO autenticazione(UtenteTO uto) {
		UtenteTO res = null;
		
		Query query = this.session.getNamedQuery("getUtente").setString("username", uto.getUsername())
				.setString("password", uto.getPassword());

		List<UtenteTO> list = query.list();
		
		if(list.size() == 1){
			for(UtenteTO item : list){
				if(item.getUsername().equals(uto.getUsername()) && item.getPassword().equals(uto.getPassword()))
					res = item;
			}
			
		}
		
		return res;
	}

	protected <T, U> UtenteTO readByUsername(T username, U ruolo) {
		UtenteTO res = null;
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getByUsername");
		query.setParameter("username", username);
		query.setParameter("tipo", ruolo);
		List<UtenteTO> list = query.list();
		res = list.get(0);
		return res;
	}

	protected <T, U> UtenteTO readByID(T id, U ruolo) {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getByID");
		query.setParameter("id", id);
		query.setParameter("idruolo", ruolo);
		List<UtenteTO> list = query.list();
		return list.get(0);
	}

	@Override
	public <T> boolean checkUsername(T username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> boolean checkEmail(T email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean crea(UtenteTO uto) {
		boolean res = false;
		// TODO Aggiungere gestione eccezioni hibernate
		this.session.beginTransaction();
		this.session.save(uto);
		res = true;
		this.session.getTransaction().commit();
		return res;
	}
}