package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

public interface PRulesFactory {

	PartEmailRule getEmailRule() throws IOException;

	PartNameRule getNameRule() throws IOException;

	PartCFRule getCfRule() throws IOException;

	PartSrcRule getSrcRule() throws IOException;

	PartTesRule getTesRule() throws IOException;

}