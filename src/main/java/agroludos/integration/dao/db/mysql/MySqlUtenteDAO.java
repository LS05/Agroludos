package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.UserNotFoundException;
import agroludos.integration.dao.db.UtenteDAO;
import agroludos.to.AgroludosTO;
import agroludos.to.UtenteTO;

class MySqlUtenteDAO extends MySqlAgroludosDAO implements UtenteDAO {
	
	@Override
	public boolean crea(UtenteTO uto) throws DatabaseException {
		return super.create(uto);
	}
	
	@Override
	public UtenteTO autenticazione(UtenteTO uto) throws UserNotFoundException {
		UtenteTO res = null;

		Query query = this.session.getNamedQuery("getUtente").setString("username", uto.getUsername())
				.setString("password", uto.getPassword());

		List<UtenteTO> list = query.list();

		if(list.size() == 1){
			for(UtenteTO item : list){
				if(item.getUsername().equals(uto.getUsername()) && item.getPassword().equals(uto.getPassword()))
					res = item;
			}

		} else {
			throw new UserNotFoundException("Username e/o Password errati!");
		}

		return res;
	}

	private UtenteTO getUtenteBy(String queryName, List<?> params) throws DatabaseException{
		UtenteTO res = null;
		List<AgroludosTO> list = super.executeParamQuery(queryName, params);

		if(list.size() == 0){
			res = this.toFact.createNullUTO();
		} else {
			res = (UtenteTO)list.get(0);
		}

		return res;
	}

	@Override
	public UtenteTO getByUsername(String username) throws DatabaseException {

		List<String> param = new ArrayList<String>();
		param.add(username);

		return this.getUtenteBy("getByUsername", param);
	}

	@Override
	public UtenteTO readByID(Integer id) throws DatabaseException {

		List<Integer> param = new ArrayList<Integer>();
		param.add(id);

		return getUtenteBy("getByID", param);
	}

	@Override
	public boolean esisteUsername(UtenteTO uto) throws DatabaseException {
		boolean res = false;

		List<String> param = new ArrayList<String>();
		param.add(uto.getUsername());

		UtenteTO user = this.getUtenteBy("getByUsername", param);

		if(user.getUsername() == "")
			res = false;
		else
			res = true;

		return res;
	}

	@Override
	public boolean esisteEmail(UtenteTO uto) throws DatabaseException {
		boolean res = false;

		List<String> param = new ArrayList<String>();
		param.add(uto.getEmail());

		UtenteTO user = this.getUtenteBy("getByEmail", param);

		if(user.getUsername() == "")
			res = false;
		else
			res = true;

		return res;
	}
}