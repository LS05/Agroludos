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
			
			Query queryRuolo = this.session.getNamedQuery("getRuoloUtente").setInteger("idruolo", res.getTipo());
			List<String> ruolo = queryRuolo.list();
			res.setRuolo(ruolo.get(0));
		}
		
		return res;
	}

	@Override
	public <T>UtenteTO readByUsername(T username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T>UtenteTO readByID(T id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}