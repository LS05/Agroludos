package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.PartecipanteTO;

class MySqlPartecipanteDAO extends MySqlUtenteDAO implements PartecipanteDAO {

	MySqlPartecipanteDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public boolean crea(PartecipanteTO parto) throws DatabaseException {
		return super.crea(parto);
	}

	@Override
	public PartecipanteTO getByUsername(String username) throws DatabaseException {
		return (PartecipanteTO) super.getByUsername(username);
	}

	@Override
	public PartecipanteTO readByID(Integer id) throws DatabaseException {
		return (PartecipanteTO) super.getByID(id);

	}

	@Override
	public boolean update(PartecipanteTO parto) throws DatabaseException {
		return super.update(parto);
	}


	@Override
	public boolean delete(PartecipanteTO parto) throws DatabaseException {
		parto.setStato(0);
		return super.update(parto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartecipanteTO> readAll() throws DatabaseException {
		List<?> list = super.executeQuery("getAllPartecipanti");		
		List<PartecipanteTO> res = (List<PartecipanteTO>)list;

		int index=0;
		for(Object cmp: list){
			this.setNomeRuolo(res.get(index));
			this.setNomeStatoUtente(res.get(index));
			index++;
		}
		return res;

	}

	@Override
	public PartecipanteTO readByCF(String cf) throws DatabaseException {

		List<String> param = new ArrayList<String>();
		param.add(cf);

		List<?> list = super.executeParamQuery("getPartecipanteByCF", param);
		PartecipanteTO res = (PartecipanteTO)list.get(0);
		
		this.setNomeRuolo(res);
		this.setNomeStatoUtente(res);
		
		return res;
	}
}