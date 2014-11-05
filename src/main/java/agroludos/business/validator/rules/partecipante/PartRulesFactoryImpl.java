package agroludos.business.validator.rules.partecipante;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.partecipante.PartCFRule;
import agroludos.business.validator.rules.partecipante.PartSrcRule;
import agroludos.business.validator.rules.partecipante.PartTesRule;

class PartRulesFactoryImpl implements PRulesFactory {
	private final static PartCFRule cfRule = new PartCFRule();;
	private final static PartSrcRule srcRule = new PartSrcRule();;
	private final static PartTesRule tesRule = new PartTesRule();;
	private final static PartDataSrcRule dataSrcRule = new PartDataSrcRule();;
	private final static PartIndirizzoRule indirizzoRule = new PartIndirizzoRule();;
	private final static PartSessoRule sessoRule = new PartSessoRule();;
	private final static PartDataNascRule dataNascRule = new PartDataNascRule();;

	@Override
	public AgroludosRule getCfRule(){
		return cfRule;	
	}

	@Override
	public AgroludosRule getSrcRule(){
		return srcRule;
	}

	@Override
	public AgroludosRule getTesRule(){
		return tesRule;
	}

	@Override
	public AgroludosRule getDataSrcRule(){
		return dataSrcRule;
	}

	@Override
	public AgroludosRule getIndirizzoRule(){
		return indirizzoRule;
	}

	@Override
	public AgroludosRule getSessoRule(){
		return sessoRule;
	}

	@Override
	public AgroludosRule getDataNascRule(){
		return dataNascRule;
	}
}