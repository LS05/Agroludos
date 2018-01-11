package agroludos.business.validator.rules.date;

import java.util.Date;

/**
 * Interfaccia che gestisce gli errori sulle date
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface DateValidator {
	/**
	 * 
	 * @param data1
	 * @param data2
	 * @return vero se data1 è antecedente a data2, false altrimenti
	 */
	boolean isBefore(Date data1, Date data2);
}
