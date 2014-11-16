package agroludos.to;

/**
 * L'interfaccia rappresenta un'interfaccia comune per TipoCompetizioneTO e TipoOptionalTO.<br/>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface TipiAgroludosTO extends AgroludosTO{
	/**
	 * 
	 * @return nome del tipo
	 */
	public String getNome();
}
