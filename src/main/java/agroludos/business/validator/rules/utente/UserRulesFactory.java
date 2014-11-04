package agroludos.business.validator.rules.utente;

import agroludos.business.validator.rules.AgroludosRule;

public interface UserRulesFactory {

	AgroludosRule getNomeRule();

	AgroludosRule getCognomeRule();

	AgroludosRule getUsernameRule();

	AgroludosRule getPasswordRule();

	AgroludosRule getEmailRule();

}