package agroludos.integration.dao.db.hib;

import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.to.TipoOptionalTO;

class HibTipoOptionalDAO extends HibAgroludosDAO<TipoOptionalTO> implements TipoOptionalDAO {

	HibTipoOptionalDAO(){
		this.setClasse(toFact.createTipoOptionalTO());
	}

}