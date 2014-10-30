package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.partecipante.PartCFRule;
import agroludos.business.validator.rules.partecipante.PartSrcRule;
import agroludos.business.validator.rules.partecipante.PartTesRule;

class PartRulesFactoryImpl implements PRulesFactory {
	private static PartCFRule cfRule;
	private static PartSrcRule srcRule;
	private static PartTesRule tesRule;
	private static PartDataSrcRule dataSrcRule;
	private static PartIndirizzoRule indirizzoRule;
	private static PartSessoRule sessoRule;
	private static PartDataNascRule dataNascRule;

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

	@Override
	public AgroludosRule getDataSrcRule() throws IOException {
		if(dataSrcRule == null)
			dataSrcRule = new PartDataSrcRule();
		return dataSrcRule;
	}

	@Override
	public AgroludosRule getIndirizzoRule() throws IOException {
		if(indirizzoRule == null)
			indirizzoRule = new PartIndirizzoRule();
		return indirizzoRule;
	}

	@Override
	public AgroludosRule getSessoRule() throws IOException {
		if(sessoRule == null)
			sessoRule = new PartSessoRule();
		return sessoRule;
	}

	@Override
	public AgroludosRule getDataNascRule() throws IOException {
		if(dataNascRule == null)
			dataNascRule = new PartDataNascRule();
		return dataNascRule;
	}
}