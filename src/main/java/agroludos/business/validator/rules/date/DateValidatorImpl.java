package agroludos.business.validator.rules.date;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

class DateValidatorImpl implements DateValidator {

	@Override
	public boolean isBefore(Date data1, Date data2) {
		LocalDate sDate = new DateTime(data1).toLocalDate();
		LocalDate eDate = new DateTime(data2).toLocalDate();

		return sDate.isBefore(eDate);
	}

}
