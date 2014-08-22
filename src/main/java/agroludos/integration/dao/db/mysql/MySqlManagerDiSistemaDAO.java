package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Query;

import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.UtenteTO;

public class MySqlManagerDiSistemaDAO extends MySqlAgroludosDAO implements ManagerDiSistemaDAO{
	
	@Override
	public boolean crea(ManagerDiSistemaTO mdsto) {
		boolean res = false;
		// TODO Aggiungere gestione eccezioni hibernate
		this.session.beginTransaction();
		this.session.save(mdsto);
		res = true;
		this.session.getTransaction().commit();
		return res;
	}

	@Override
	public ManagerDiSistemaTO read(UtenteTO uto) {
		ManagerDiSistemaTO res = null;
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getManagerDiSistema").setString("username", uto.getUsername());
		List<ManagerDiSistemaTO> list = query.list();
		
		for(ManagerDiSistemaTO item : list){
			if(item.getUsername().equals(uto.getUsername()))
				res = item;
		}
		
		this.session.getTransaction().commit();
		return res;
	}
	
	

}
