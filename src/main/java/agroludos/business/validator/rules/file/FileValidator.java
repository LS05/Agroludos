package agroludos.business.validator.rules.file;

/**
 * L'interfaccia gestisce gli errori sui file
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface FileValidator {
	/**
	 * 
	 * @param fileName
	 * @param format
	 * @return vero se il formato del file è del tipo "format" in input, false altrimenti
	 */
	boolean isOfFormat(String fileName, String format);
}
