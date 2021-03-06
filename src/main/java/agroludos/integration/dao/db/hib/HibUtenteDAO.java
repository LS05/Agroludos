package agroludos.integration.dao.db.hib;

import java.util.ArrayList;
import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.UtenteDAO;
import agroludos.to.UtenteTO;

class HibUtenteDAO<T extends UtenteTO> extends HibAgroludosDAO<T> implements UtenteDAO<T>{

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
	public T getByEmail(String email) throws DatabaseException {

		List<String> param = new ArrayList<String>();
		param.add(email);

		return this.getUtenteBy("getByEmail", param);
	}
	
	@Override
	public T getByID(Integer id) throws DatabaseException {

		List<Integer> param = new ArrayList<Integer>();
		param.add(id);

		return getUtenteBy("getByID", param);
	}
	
	/**
	 * 
	 * @param user
	 * @return vero se si tratta di un utente nullo, falso altrimenti
	 */
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