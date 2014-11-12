package agroludos.business.validator.partecipante;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.partecipante.PRulesFactory;
import agroludos.business.validator.rules.utente.UserRulesFactory;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.TOFactory;

class PartValidator implements AgroludosValidator{
	private UserRulesFactory userRulesFact;
	private TOFactory toFact;
	private AgroludosRule nomeRule;

	PartValidator(UserRulesFactory userRulesFactory, PRulesFactory partRulesFactory, TOFactory toFactory){
		this.userRulesFact = userRulesFactory;
		this.toFact = toFactory;
		this.nomeRule = this.userRulesFact.getNomeRule();

		AgroludosRule cognomeRule = this.userRulesFact.getCognomeRule();
		AgroludosRule usernameRule = this.userRulesFact.getUsernameRule();
		AgroludosRule passwordRule = this.userRulesFact.getPasswordRule();
		AgroludosRule emailRule = this.userRulesFact.getEmailRule();
		AgroludosRule indirizzoRule = partRulesFactory.getIndirizzoRule();
		AgroludosRule dataSrcRule = partRulesFactory.getDataSrcRule();
		AgroludosRule cfRule = partRulesFactory.getCfRule();
		AgroludosRule srcRule = partRulesFactory.getSrcRule();
		AgroludosRule tesRule = partRulesFactory.getTesRule();
		AgroludosRule sessoRule = partRulesFactory.getSessoRule();
		AgroludosRule dataNascRule = partRulesFactory.getDataNascRule();


		this.nomeRule.setSuccessor(cognomeRule);
		cognomeRule.setSuccessor(cfRule);
		cfRule.setSuccessor(srcRule);
		srcRule.setSuccessor(tesRule);
		tesRule.setSuccessor(dataSrcRule);
		dataSrcRule.setSuccessor(indirizzoRule);
		indirizzoRule.setSuccessor(usernameRule);
		usernameRule.setSuccessor(passwordRule);
		passwordRule.setSuccessor(emailRule);
		emailRule.setSuccessor(sessoRule);
		sessoRule.setSuccessor(dataNascRule);
	}

	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		if(to instanceof PartecipanteTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			PartecipanteTO partecipante = (PartecipanteTO)to;
			this.nomeRule.validate(partecipante, errorTO);
			if(errorTO.hasErrors())
				throw new ValidationException(errorTO);
		}
	}
}