package agroludos.business.validator.rules.file;

import org.apache.commons.io.FilenameUtils;

class FileValidatorImpl implements FileValidator {

	@Override
	public boolean isOfFormat(String fileName, String format) {
		String ext = FilenameUtils.getExtension(fileName);
		return ext.equals(format);
	}

}
