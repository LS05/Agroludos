package agroludos.business.validator.rules.competizione;

import agroludos.business.validator.rules.AgroludosRule;
/**
 * implementa {@link CompRulesFactory}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class CompRulesFactoryImpl implements CompRulesFactory {
	private final static CompNomeRule NOME_RULE = new CompNomeRule();
	private final static CompNumPartRule NUMPART_RULE = new CompNumPartRule();
	private final static CompCostoRule COSTO_RULE = new CompCostoRule();
	private final static CompDataRule DATA_RULE = new CompDataRule();

	@Override
	public AgroludosRule getNomeRule() {
		return NOME_RULE;
	}

	@Override
	public AgroludosRule getNumPartRule() {
		return NUMPART_RULE;
	}

	@Override
	public AgroludosRule getCostoRule() {
		return COSTO_RULE;
	}

	@Override
	public AgroludosRule getDataRule() { 
		return DATA_RULE;
	}
}