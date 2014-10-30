package agroludos.business.validator.rules.utente;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;

public interface UserRulesFactory {

	AgroludosRule getNameRule() throws IOException;

	AgroludosRule getCognomeRule() throws IOException;

	AgroludosRule getUsernameRule() throws IOException;

	AgroludosRule getPasswordRule() throws IOException;

	AgroludosRule getEmailRule() throws IOException;

}