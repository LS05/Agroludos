package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.partecipante.PartCFRule;
import agroludos.business.validator.rules.partecipante.PartEmailRule;
import agroludos.business.validator.rules.partecipante.PartNameRule;
import agroludos.business.validator.rules.partecipante.PartSrcRule;
import agroludos.business.validator.rules.partecipante.PartTesRule;

class PartRulesFactoryImpl implements PRulesFactory {
	private static PartEmailRule emailRule;
	private static PartNameRule nameRule;
	private static PartCFRule cfRule;
	private static PartSrcRule srcRule;
	private static PartTesRule tesRule;

	@Override
	public AgroludosRule getEmailRule() throws IOException{
		if(emailRule == null)
			emailRule = new PartEmailRule();
		return emailRule;	
	}

	@Override
	public AgroludosRule getNameRule() throws IOException{
		if(nameRule == null)
			nameRule = new PartNameRule();
		return nameRule;	
	}

	@Override
	public AgroludosRule getCfRule() throws IOException{
		if(cfRule == null)
			cfRule = new PartCFRule();
		return cfRule;	
	}

	@Override
	public AgroludosRule getSrcRule() throws IOException {
		if(srcRule == null)
			srcRule = new PartSrcRule();
		return srcRule;
	}

	@Override
	public AgroludosRule getTesRule() throws IOException {
		if(tesRule == null)
			tesRule = new PartTesRule();
		return tesRule;
	}
}