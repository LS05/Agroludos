package agroludos.to;

/**
 * Implementazione dell'interfaccia {@link ErrorMessageTO}. 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ErrorMessageImpl implements ErrorMessageTO{
	private static final long serialVersionUID = -3938174563420407458L;
	private String message;

	@Override
	public void setMessage(String message) {
		this.message = message;
		
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
