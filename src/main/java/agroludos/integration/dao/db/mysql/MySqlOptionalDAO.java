package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.OptionalDAO;
import agroludos.to.OptionalTO;
import agroludos.to.TipoOptionalTO;

class MySqlOptionalDAO extends MySqlAgroludosDAO<OptionalTO> implements OptionalDAO {

	MySqlOptionalDAO(){
		this.setClasse(toFact.createOptionalTO());
	}

	@Override
	public OptionalTO disattivaOptional(OptionalTO optTO) throws DatabaseException {
		return super.update(optTO);
	}

	@Override
	public List<OptionalTO> getOptionalAttiviByTipo(TipoOptionalTO toptTO) throws DatabaseException {
		List<TipoOptionalTO> param = new ArrayList<TipoOptionalTO>();
		param.add(toptTO);

		List<OptionalTO> res = super.executeParamQuery("getOptionalAttiviByTipo", param);


		return res;
	}

	@Override
	public List<OptionalTO> getOptionalByTipo(TipoOptionalTO toptTO) throws DatabaseException {
		List<TipoOptionalTO> param = new ArrayList<TipoOptionalTO>();
		param.add(toptTO);

		List<OptionalTO> res = super.executeParamQuery("getOptionalByTipo", param);


		return res;
	}
}