package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Query;

import agroludos.integration.dao.db.UtenteDAO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.UtenteTO;

public class MySqlUtenteDAO extends MySqlAgroludosDAO implements UtenteDAO {

	@Override
	public UtenteTO read(UtenteTO uto) {
		UtenteTO res = null;
		ManagerDiSistemaTO manTO = null;
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getManagerDiSistema").setString("username", uto.getUsername());
		
		List<ManagerDiSistemaTO> list = query.list();

		for(ManagerDiSistemaTO item : list){
			if(item.getUsername().equals(uto.getUsername()) && item.getPassword().equals(uto.getPassword()))
				manTO = item;
		}
		
		return res;
	}

	@Override
	public UtenteTO readByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UtenteTO readByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}