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
	private static PartCognomeRule cognomeRule;
	private static PartUsernameRule usernameRule;
	private static PartDataSrcRule dataSrcRule;
	private static PartIndirizzoRule indirizzoRule;
	private static PartPasswordRule passwordRule;
	private static PartSessoRule sessoRule;
	private static PartDataNascRule dataNascRule;

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
	
	@Override
	public AgroludosRule getCognomeRule() throws IOException {
		if(cognomeRule == null)
			cognomeRule = new PartCognomeRule();
		return cognomeRule;
	}
	
	@Override
	public AgroludosRule getUsernameRule() throws IOException {
		if(usernameRule == null)
			usernameRule = new PartUsernameRule();
		return usernameRule;
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
	public AgroludosRule getPasswordRule() throws IOException {
		if(passwordRule == null)
			passwordRule = new PartPasswordRule();
		return passwordRule;
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