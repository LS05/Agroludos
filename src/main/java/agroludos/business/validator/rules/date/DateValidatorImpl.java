package agroludos.business.validator.rules.date;

import java.util.Date;

import org.joda.time.DateMidnight;
import org.joda.time.LocalDate;

/**
 * implementa l'interfaccia {@link DateValidator}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class DateValidatorImpl implements DateValidator {

	@Override
	public boolean isBefore(Date data1, Date data2) {
		LocalDate sDate = new DateMidnight(data1).toLocalDate();
		LocalDate eDate = new DateMidnight(data2).toLocalDate();

		return sDate.isBefore(eDate);
	}

}
