package agroludos.business.validator.rules.partecipante;

import agroludos.business.validator.rules.AgroludosRule;

public interface PRulesFactory {

	AgroludosRule getCfRule();

	AgroludosRule getDataSrcRule();

	AgroludosRule getIndirizzoRule();

	AgroludosRule getSessoRule();

	AgroludosRule getSrcRule();

	AgroludosRule getTesRule();

	AgroludosRule getDataNascRule();

}