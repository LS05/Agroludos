package agroludos.integration.dao.db.hib;

import java.util.ArrayList;
import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.OptionalDAO;
import agroludos.to.OptionalTO;
import agroludos.to.TipoOptionalTO;

class HibOptionalDAO extends HibAgroludosDAO<OptionalTO> implements OptionalDAO {

	HibOptionalDAO(){
		this.setClasse(toFact.createOptionalTO());
	}

	@Override
	public OptionalTO disattivaOptional(OptionalTO optTO) throws DatabaseException {
		return super.update(optTO);
	}

	@Override
	public List<OptionalTO> getOptionalAttiviByTipo(TipoOptionalTO toptTO) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(toptTO.getNome());

		List<OptionalTO> res = super.executeParamQuery("getOptionalAttiviByTipo", param);


		return res;
	}

	@Override
	public List<OptionalTO> getOptionalByTipo(TipoOptionalTO toptTO) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(toptTO.getNome());

		List<OptionalTO> res = super.executeParamQuery("getOptionalByTipo", param);


		return res;
	}
}