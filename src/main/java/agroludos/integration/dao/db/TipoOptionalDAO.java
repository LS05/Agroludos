package agroludos.integration.dao.db;

import java.util.List;

import agroludos.to.TipoOptionalTO;

public interface TipoOptionalDAO {

	boolean crea(TipoOptionalTO topto);

	List<TipoOptionalTO> readAll();
}
