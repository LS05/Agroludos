package agroludos.business.validator.rules.partecipante;

import agroludos.business.validator.rules.AgroludosRule;

class PartRulesFactoryImpl implements PRulesFactory {
	private final static PartCFRule CF_RULE = new PartCFRule();
	private final static PartSrcRule SRC_RULE = new PartSrcRule();
	private final static PartTesRule TES_RULE = new PartTesRule();
	private final static PartDataSrcRule DATASRC_RULE = new PartDataSrcRule();
	private final static PartIndirizzoRule INDIRIZZO_RULE = new PartIndirizzoRule();
	private final static PartSessoRule SESSO_RULE = new PartSessoRule();
	private final static PartDataNascRule DATANASC_RULE = new PartDataNascRule();

	@Override
	public AgroludosRule getCfRule(){
		return CF_RULE;	
	}

	@Override
	public AgroludosRule getSrcRule(){
		return SRC_RULE;
	}

	@Override
	public AgroludosRule getTesRule(){
		return TES_RULE;
	}

	@Override
	public AgroludosRule getDataSrcRule(){
		return DATASRC_RULE;
	}

	@Override
	public AgroludosRule getIndirizzoRule(){
		return INDIRIZZO_RULE;
	}

	@Override
	public AgroludosRule getSessoRule(){
		return SESSO_RULE;
	}

	@Override
	public AgroludosRule getDataNascRule(){
		return DATANASC_RULE;
	}
}