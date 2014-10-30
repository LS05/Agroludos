package agroludos.business.validator.rules.mdc;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;

public interface MdcRulesFactory {

	AgroludosRule getStipendioRule() throws IOException;

}