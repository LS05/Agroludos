package agroludos.business.validator.rules.partecipante;

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
	public AgroludosRule getCfRule(){
		if(cfRule == null)
			cfRule = new PartCFRule();
		return cfRule;	
	}

	@Override
	public AgroludosRule getSrcRule(){
		if(srcRule == null)
			srcRule = new PartSrcRule();
		return srcRule;
	}

	@Override
	public AgroludosRule getTesRule(){
		if(tesRule == null)
			tesRule = new PartTesRule();
		return tesRule;
	}

	@Override
	public AgroludosRule getDataSrcRule(){
		if(dataSrcRule == null)
			dataSrcRule = new PartDataSrcRule();
		return dataSrcRule;
	}

	@Override
	public AgroludosRule getIndirizzoRule(){
		if(indirizzoRule == null)
			indirizzoRule = new PartIndirizzoRule();
		return indirizzoRule;
	}

	@Override
	public AgroludosRule getSessoRule(){
		if(sessoRule == null)
			sessoRule = new PartSessoRule();
		return sessoRule;
	}

	@Override
	public AgroludosRule getDataNascRule(){
		if(dataNascRule == null)
			dataNascRule = new PartDataNascRule();
		return dataNascRule;
	}
}