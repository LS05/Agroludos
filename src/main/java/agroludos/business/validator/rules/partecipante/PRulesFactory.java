package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;

public interface PRulesFactory {

	AgroludosRule getEmailRule() throws IOException;

	AgroludosRule getNameRule() throws IOException;

	AgroludosRule getCfRule() throws IOException;

	AgroludosRule getSrcRule() throws IOException;

	AgroludosRule getTesRule() throws IOException;

}