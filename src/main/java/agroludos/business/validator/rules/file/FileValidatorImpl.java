package agroludos.business.validator.rules.file;

import org.apache.commons.io.FilenameUtils;

/**
 * implementa l'interfaccia {@link FileValidator}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class FileValidatorImpl implements FileValidator {

	@Override
	public boolean isOfFormat(String fileName, String format) {
		String ext = FilenameUtils.getExtension(fileName);
		return ext.equals(format);
	}

}
