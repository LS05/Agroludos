package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.UtenteDAO;
import agroludos.to.UtenteTO;

class MySqlUtenteDAO<T extends UtenteTO> extends MySqlAgroludosDAO<T> implements UtenteDAO<T>{
	
	MySqlUtenteDAO(Session session){
		super(session);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getUtente(UtenteTO uto) throws DatabaseException {
		UtenteTO res = null;

		List<String> param = new ArrayList<String>();
		param.add(uto.getUsername());
		param.add(uto.getPassword());

		List<T> list = this.executeParamQuery("getUtente", param);

		if(list.size() == 1){
			res = list.get(0);
		} else {
			res = toFact.createNullUTO();
		}

		return (T)res;
	}

	@SuppressWarnings("unchecked")
	private T getUtenteBy(String queryName, List<?> params) throws DatabaseException{

		UtenteTO res = null;
		List<T> list = super.executeParamQuery(queryName, params);

		if(list.size() == 0){
			
			//TODO toFact è null
			res = toFact.createNullUTO();
		} else {
			res = (UtenteTO)list.get(0);
		}

		return (T)res;
	}

	@Override
	public T getByUsername(String username) throws DatabaseException {

		List<String> param = new ArrayList<String>();
		param.add(username);

		return this.getUtenteBy("getByUsername", param);
	}

	@Override
	public T getByID(Integer id) throws DatabaseException {

		List<Integer> param = new ArrayList<Integer>();
		param.add(id);

		return getUtenteBy("getByID", param);
	}
	
	private boolean isNullUtente(UtenteTO user){
		return user.getId() == -1;
	}

	@Override
	public boolean esisteUsername(UtenteTO uto) throws DatabaseException {
		boolean res = false;

		List<String> param = new ArrayList<String>();
		param.add(uto.getUsername());

		UtenteTO user = this.getUtenteBy("getByUsername", param);

		res = !this.isNullUtente(user);

		return res;
	}

	@Override
	public boolean esisteEmail(UtenteTO uto) throws DatabaseException {
		boolean res = false;

		List<String> param = new ArrayList<String>();
		param.add(uto.getEmail());

		UtenteTO user = this.getUtenteBy("getByEmail", param);

		res = !this.isNullUtente(user);

		return res;
	}
	
}