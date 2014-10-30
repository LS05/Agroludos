package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;

public interface PRulesFactory {

	AgroludosRule getCfRule() throws IOException;

	AgroludosRule getDataSrcRule() throws IOException;

	AgroludosRule getIndirizzoRule() throws IOException;

	AgroludosRule getSessoRule() throws IOException;

	AgroludosRule getSrcRule() throws IOException;

	AgroludosRule getTesRule() throws IOException;

	AgroludosRule getDataNascRule() throws IOException;

}