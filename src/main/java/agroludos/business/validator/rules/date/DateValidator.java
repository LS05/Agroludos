package agroludos.business.validator.rules.date;

import java.util.Date;

public interface DateValidator {
	boolean isBefore(Date data1, Date data2);
}
