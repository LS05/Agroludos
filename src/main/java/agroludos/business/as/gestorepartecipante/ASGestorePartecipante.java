package agroludos.business.as.gestorepartecipante;

import java.io.IOException;
import java.util.List;

import org.joda.time.DateMidnight;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.business.UserNotFoundException;
import agroludos.exceptions.business.UtenteEsistenteException;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.integration.dao.db.TipoUtenteDAO;
import agroludos.integration.dao.file.CertificatoSRCDAO;
import agroludos.integration.dao.file.FileDAOFactory;
import agroludos.integration.dao.file.FileFactory;
import agroludos.to.CertificatoTO;
import agroludos.to.PartecipanteTO;
import agroludos.utility.PasswordEncryption;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore dei Partecipanti.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare il funzionamento
 * dei servizi andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * Il gestore utilizza una serie di {@link AgroludosTO} (Transfer Object o Data Transfer Object
 * la cui tipologia dipende dal servizio) e sfrutta il {@link PartecipanteDAO} 
 * (Data Access Object) per occuparsi della persistenza di tali oggetti.</br>
 * Il Gestore dei Partecipanti espone la logica di business riguardante i Partecipanti 
 * tramite due interfacce. L'interfaccia {@link LPartecipante} fornisce i servizi 
 * di lettura, mentre l'interfaccia {@link SPartecipante} offre i servizi di scrittura.</br>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ASGestorePartecipante extends AgroludosAS implements LPartecipante, SPartecipante{
	private PasswordEncryption pwdEnc;
	private FileFactory fileFactory;
	private AgroludosValidator validator;

	/**
	 * Il costruttore inizializza le variabili pwdEnc, fileFactory e validator
	 * @param pwdEnc
	 * @param fileFactory
	 * @param validator
	 */
	ASGestorePartecipante(PasswordEncryption pwdEnc, FileFactory fileFactory, AgroludosValidator validator){
		this.pwdEnc = pwdEnc;
		this.fileFactory = fileFactory;
		this.validator = validator;
	}

	/**
	 * 
	 * @return istanza di {@link PartecipanteDAO}
	 * @throws DatabaseException
	 */
	private PartecipanteDAO getPartecipanteDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getPartecipanteDAO();
	}

	/**
	 * 
	 * @return istanza di {@link TipoUtenteDAO}
	 * @throws DatabaseException
	 */
	private TipoUtenteDAO getTipoUtenteDAO() throws DatabaseException {
		DBDAOFactory daoTipoUtente = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return daoTipoUtente.getTipoUtenteDAO();
	}

	/**
	 * 
	 * @return istanza di {@link CertificatoSRCDAO}
	 */
	private CertificatoSRCDAO getCertificatoSRCDAO() {
		FileDAOFactory daoFile = this.fileFactory.getDAOFactory(this.sysConf.getTipoCert());
		return daoFile.getCertificatoSRCDAO();
	}

	/**
	 * Il metodo effettua la validazione del partecipante in input, salva il certificato in memoria
	 * e imposta il suo percorso nel {@link PartecipanteTO} associato al partecipante
	 * @param partTO
	 * @throws DatabaseException
	 * @throws ValidationException
	 * @throws IOException
	 */
	private void setDatiPartecipante(PartecipanteTO partTO)
			throws DatabaseException, ValidationException, IOException{

		this.validator.validate(partTO);

		CertificatoSRCDAO daoCert = this.getCertificatoSRCDAO();

		daoCert.salvaCertificato(partTO);

		partTO.setCertificato(daoCert.getCertificato(partTO));

	}

	/**
	 * Il metodo inserisce un partecipanti nella sorgente dati tramite il DAO {@link PartecipanteDAO}.
	 * Prima di effettuare l'inserimento controlla che non esistano già username e email, altrimenti 
	 * solleva l'eccezione {@link UtenteEsistenteException}, e effettua la validazione tramite {@link #setDatiPartecipante(PartecipanteTO)}
	 */
	@Override
	public PartecipanteTO inserisciPartecipante(PartecipanteTO partTO)
			throws DatabaseException, ValidationException, IOException {

		PartecipanteDAO daoPar = this.getPartecipanteDAO();

		if( daoPar.esisteEmail(partTO)){
			throw new UtenteEsistenteException("Email già esistente");
		}

		if( daoPar.esisteUsername(partTO) ){
			throw new UtenteEsistenteException("Username già esistente");
		}

		TipoUtenteDAO daoTipoUtente = this.getTipoUtenteDAO();

		this.setDatiPartecipante(partTO);

		String inputPassword = partTO.getPassword();
		partTO.setPassword(this.pwdEnc.encryptPassword(inputPassword));

		partTO.setTipoUtente(daoTipoUtente.getTipoUtentePart());

		return daoPar.create(partTO);

	}

	/**
	 * Il metodo dopo aver validato il partecipante in input tramite {@link #setDatiPartecipante(PartecipanteTO)}
	 * effettua la modifica tramite il DAO {@link PartecipanteDAO}
	 */
	@Override
	public PartecipanteTO modificaPartecipante(PartecipanteTO partTO)
			throws DatabaseException, ValidationException, IOException {

		PartecipanteDAO daoPar = this.getPartecipanteDAO();

		this.setDatiPartecipante(partTO);

		PartecipanteTO part = (PartecipanteTO)daoPar.update(partTO);

		return part;
	}

	/**
	 * TODO
	 * @param partTO
	 * @return
	 * @throws UserNotFoundException
	 * @throws IOException
	 */
	private PartecipanteTO getSupp(PartecipanteTO partTO) 
			throws UserNotFoundException, IOException{
		CertificatoSRCDAO certFile = this.getCertificatoSRCDAO();

		if(partTO.getId() != -1){
			partTO.setCertificato(certFile.getCertificato(partTO));
			partTO.setCertificato(null);
		} else {
			throw new UserNotFoundException();
		}
		return partTO;
	}

	/**
	 * Il metodo restituisce un Partecipante utilizzando l'username in input
	 */
	@Override
	public PartecipanteTO getPartecipante(PartecipanteTO parTO)
			throws DatabaseException, IOException, UserNotFoundException {

		PartecipanteDAO daoPar = this.getPartecipanteDAO();
		PartecipanteTO user = daoPar.getByUsername(parTO.getUsername());

		return getSupp(user);
	}

	@Override
	public List<PartecipanteTO> getAllPartecipante() 
			throws DatabaseException {

		PartecipanteDAO daoPar = this.getPartecipanteDAO();
		CertificatoSRCDAO certDao = this.getCertificatoSRCDAO();
		List<PartecipanteTO> res = daoPar.getAllPartecipante();

		for(PartecipanteTO part : res){
			part.setCertificato(certDao.getCertificato(part));
		}

		return res;
	}

	@Override
	public CertificatoTO getCertificatoSRC(PartecipanteTO parTO)
			throws DatabaseException {

		CertificatoSRCDAO certDao = this.getCertificatoSRCDAO();

		return certDao.getCertificato(parTO);

	}

	@Override
	public boolean isCertificatoValido(PartecipanteTO parTO){
		DateMidnight dataSrc = new DateMidnight(parTO.getDataSRC());
		DateMidnight today = new DateMidnight();
		boolean res = false;
		if( today.isBefore(dataSrc.plusYears(1))){
			res = true;
		} else{
			res = false;
		}
		return res;
	}

}