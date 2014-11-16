package agroludos.business.as.gestoreiscrizione;

import java.text.MessageFormat;
import java.util.List;

import org.joda.time.DateMidnight;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.business.CSRCScadutoException;
import agroludos.exceptions.business.ChiuseIscrizioniException;
import agroludos.exceptions.business.IscrizioneEsistenteException;
import agroludos.exceptions.business.NmaxRaggiuntoException;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.integration.dao.db.StatoIscrizioneDAO;
import agroludos.to.CompetizioneTO;
import agroludos.to.EmailTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.StatoIscrizioneTO;
import agroludos.utility.email.AgroludosMail;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore delle Iscrizioni.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare il funzionamento
 * dei servizi andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * Il gestore utilizza una serie di {@link AgroludosTO} (Transfer Object o Data Transfer Object
 * la cui tipologia dipende dal servizio) e sfrutta il {@link IscrizioneDAO} 
 * (Data Access Object) per occuparsi della persistenza di tali oggetti.</br>
 * Il Gestore della Iscrizioni espone la logica di business riguardante le iscrizioni 
 * tramite due interfacce. L'interfaccia {@link LIscrizione} fornisce i servizi 
 * di lettura, mentre l'interfaccia {@link SIscrizione} offre i servizi di scrittura.</br>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ASGestoreIscrizione extends AgroludosAS implements LIscrizione, SIscrizione{

	private AgroludosValidator validator;
	private AgroludosMail agroludosMail;

	/**
	 * Il costrute inizializza gli attributi validator e agroludosMail
	 * 
	 * @param validator
	 * @param agroludosMail
	 * @see agroludos.business.validator.AgroludosValidator
	 * @see agroludos.utility.email.AgroludosMail
	 */	
	ASGestoreIscrizione(AgroludosValidator validator, AgroludosMail agroludosMail){
		this.validator = validator;
		this.agroludosMail = agroludosMail;
	}

	/**
	 * Il metodo restuisce un'istanza di DBDAOFactory
	 * 
	 * @return DBDAOFactory
	 * @throws DatabaseException
	 * @see agroludos.integration.dao.db.DBDAOFactory
	 */
	private DBDAOFactory getDBDaoFactory() throws DatabaseException{
		return this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
	}

	/**
	 * Il metodo restituisce un'istanza di IscrizioneDAO
	 * 
	 * @return IscrizioneDAO
	 * @throws DatabaseException
	 * @see agroludos.integration.dao.db.IscrizioneDAO
	 */
	private IscrizioneDAO getIscrizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.getDBDaoFactory();
		return dbDAOFact.getIscrizioneDAO();
	}

	/**
	 * Il metodo restituisce un'istanza di StatoIscrizioneDAO
	 * 
	 * @return StatoIscrizioneDAO
	 * @throws DatabaseException
	 * @see agroludos.integration.dao.db.StatoIscrizioneDAO
	 */
	private StatoIscrizioneDAO getStatoIscrizioneDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.getDBDaoFactory();
		return dbDAOFact.getStatoIscrizioneDAO();
	}

	/**
	 * Il metodo restituisce un'istanza di CompetizioneDAO
	 * 
	 * @return CompetizioneDAO
	 * @throws DatabaseException
	 * @see agroludos.integration.dao.db.CompetizioneDAO
	 */
	private CompetizioneDAO getCompetizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.getDBDaoFactory();
		return dbDAOFact.getCompetizioneDAO();
	}

	/**
	 * Il metodo inserisce un'iscrizione nella sorgente dati utilizzando il DAO {@link IscrizioneDAO}
	 * <br>Nel dettaglio:<br>
	 * Prima di inserire l'iscrizione controlla che siano rispettati i seguenti vincoli:<br>
	 * La competizione è "aperta alle iscrizioni";<br>
	 * L'iscrizione non è già presente nella competizione utilizzando {@link #esisteIscrizione(IscrizioneTO)};<br>
	 * Il certificato di Sana e Robusta Costituzione non scada prima dell'inizio della competizione;<br>
	 * Non si sia raggiunto il massimo di iscritti.<br>
	 * Infine invia una email al Manager di Competizione della competizione in cui si deve inserire
	 * l'iscrizione.
	 * 
	 * @see IscrizioneDAO
	 * @see CompetizioneDAO
	 * @see StatoIscrizioneDAO
	 * @see EmailTO
	 */
	@Override
	public IscrizioneTO inserisciIscrizione(IscrizioneTO iscTO)
			throws DatabaseException, ValidationException {

		IscrizioneDAO iscDAO = getIscrizioneDAO();
		CompetizioneDAO cmpDAO = getCompetizioneDAO();

		CompetizioneTO cmp = cmpDAO.readById(iscTO.getCompetizione().getId());

		//pseudocodice
		//getCompetizioneById() dal dao
		// 1.competizione aperta alle iscrizioni 
		//private boolean isCompetizioneAperta(CompetizioneTO cmp) throw new ChiuseIscrizioniException();
		// 2.iscrizione esistente 
		//private boolean iscrizioneEsistente(IscrizioneTO isc) throw new IscrizioneEsistenteException();
		// 3.numero massimo raggiunto
		//private boolean numeroMaxRaggiunto(CompetizioneTO cmp) throw new NumeroMaxRaggiuntoException();
		//validator ?
		//setStatoIscrizione a 1
		//crea
		if(isCompetizioneAperta(cmp)){
			esisteIscrizione(iscTO);
			if(!numeroMaxRaggiunto(cmp)){
				if(isCertificatoValido(iscTO.getPartecipante(),iscTO.getCompetizione())){
					this.validator.validate(iscTO);

					StatoIscrizioneDAO statoIscDAO = getStatoIscrizioneDAO();

					iscTO.setStatoIscrizione(statoIscDAO.getStatoAttivo());

					iscTO = iscDAO.create(iscTO);

					EmailTO mail = this.toFact.createEmailTO();
					String partUsername = iscTO.getPartecipante().getUsername();
					String compNome = iscTO.getCompetizione().getNome();

					String iscrObj = this.sysConf.getString("mailIscrizioneSubj");
					String mailObj = MessageFormat.format(iscrObj, partUsername, compNome);
					mail.setOggetto(mailObj);

					String iscrMsg = "";
					String mailMsg = "";

					if(iscTO.getAllOptionals().size() > 0){
						iscrMsg = this.sysConf.getString("mailIscrizioneMsg");
						mailMsg = MessageFormat.format(iscrMsg,
								iscTO.getPartecipante().toString(), 
								iscTO.getCosto().toString(), 
								iscTO.getAllOptionals().toString());
					} else {
						iscrMsg = this.sysConf.getString("mailIscrizioneMsgNoOpt");
						mailMsg = MessageFormat.format(iscrMsg,
								iscTO.getPartecipante().toString(), 
								iscTO.getCosto().toString());
					}

					mail.setMessage(mailMsg);

					mail.addDestinatario(iscTO.getCompetizione().getManagerDiCompetizione());

					this.agroludosMail.sendEmail(mail);
				}else{
					throw new CSRCScadutoException("Il certifica scadrà prima dell'inizio della competizione!\nAggiorna.");
				}
			}
		}
		return iscTO;
	}

	/**
	 * Il metodo restituisce true se il certificato del partecipante in input è valido 
	 * fino alla data dell'inizio della competizione in input.
	 * Altrimenti restituisce false
	 * 
	 * @param parTO
	 * @param cmp
	 * @return boolean
	 */
	private boolean isCertificatoValido(PartecipanteTO parTO, CompetizioneTO cmp){
		DateMidnight dataSrc = new DateMidnight(parTO.getDataSRC());
		DateMidnight dataCmp = new DateMidnight(cmp.getData());
		boolean res = false;
		if( dataCmp.isBefore(dataSrc.plusYears(1))){
			res = true;
		} else{
			res = false;
		}
		return res;
	}

	/**
	 * Il metodo modifica l'iscrizione mediante il DAO {@link IscrizioneDAO} e restituisce
	 * l'iscrizione modificata.
	 * @param iscTO
	 * @return {@link IscrizioneTO}
	 * @throws DatabaseException
	 */
	private IscrizioneTO modificaIscrizione(IscrizioneTO iscTO)
			throws DatabaseException {
		IscrizioneDAO iscDAO = getIscrizioneDAO();
		return iscDAO.update(iscTO);
	}

	/**
	 * Il metodo viene sempre invocato da un Manager di competizione e effettua la modifica di una
	 * iscrizione. Dopodichè invia una email al partecipante informandolo degli optional modificati o 
	 * rimossi
	 * 
	 * @see EmailTO
	 * @see IscrizioneTO
	 * @see ManagerDiCompetizioneTO
	 */
	@Override
	public IscrizioneTO modificaIscrizioneByMdc(IscrizioneTO iscTO) 
			throws DatabaseException {

		iscTO = modificaIscrizione(iscTO);

		EmailTO mail = this.toFact.createEmailTO();

		String mailSubj = this.sysConf.getString("mailModIscrMdcSubj");
		mail.setOggetto(mailSubj);

		String mailMsg = this.sysConf.getString("mailModIscrMdcMsg");
		String realMsg = MessageFormat.format(mailMsg, 
				iscTO.getPartecipante().getUsername(), 
				iscTO.getCompetizione().getNome(), 
				iscTO.getAllOptionals().toString(), 
				iscTO.getCosto());
		mail.setMessage(realMsg);
		mail.addDestinatario(iscTO.getPartecipante());

		this.agroludosMail.sendEmail(mail);

		return iscTO;
	}

	/**
	 * Il metodo viene sempre invocato da un partecipante e effettua la modifica di una
	 * iscrizione. Dopodichè invia una email al Manager della competizione 
	 * informandolo degli optional modificati o rimossi
	 * 
	 * @see EmailTO
	 * @see IscrizioneTO
	 * @see PartecipanteTO
	 */
	@Override
	public IscrizioneTO modificaIscrizioneByPartecipante(IscrizioneTO iscTO)
			throws DatabaseException {
		iscTO = modificaIscrizione(iscTO);

		EmailTO mail = this.toFact.createEmailTO();

		String mailSubj = this.sysConf.getString("mailModIscrPartSubj");
		mail.setOggetto(mailSubj);

		String mailMsg = this.sysConf.getString("mailModIscrPartMsg");
		String realMsg = MessageFormat.format(mailMsg, 
				iscTO.getPartecipante().getUsername(), 
				iscTO.getCompetizione().getNome(), 
				iscTO.getAllOptionals().toString());
		mail.setMessage(realMsg);
		mail.addDestinatario(iscTO.getCompetizione().getManagerDiCompetizione());
		this.agroludosMail.sendEmail(mail);

		return iscTO;
	}

	/**
	 * Il metodo elimina un'iscrizione dalla competizione attraverso il DAO {@link IscrizioneDAO}
	 * e restituisce l'iscrizione eliminata.
	 * 
	 * @param iscTO
	 * @return IscrizioneTO
	 * @throws DatabaseException
	 */
	private IscrizioneTO eliminaIscrizione(IscrizioneTO iscTO)
			throws DatabaseException {

		IscrizioneDAO iscDAO = getIscrizioneDAO();

		StatoIscrizioneDAO statoIscDAO = getStatoIscrizioneDAO();
		StatoIscrizioneTO siTO = statoIscDAO.getStatoDisattivo();

		iscTO.setStatoIscrizione(siTO);

		return iscDAO.annullaIscrizione(iscTO);
	}

	/**
	 * Il metodo è sempre invocato da un Manager di Competizione, il metodo quindi effettua la rimozione
	 * dell'iscrizione invocando {@link #eliminaIscrizione(IscrizioneTO)}
	 */
	@Override
	public IscrizioneTO eliminaIscrizioneByMdc(IscrizioneTO iscTO) 
			throws DatabaseException{

		iscTO = eliminaIscrizione(iscTO);
		return iscTO;
	}


	/**
	 * Il metodo è sempre invocato da un Partecipante, il metodo quindi effettua la rimozione
	 * dell'iscrizione invocando {@link #eliminaIscrizione(IscrizioneTO)} e infine invia
	 * una email al Manager della competizione informandolo dell'eliminazione effettuata
	 */
	@Override
	public IscrizioneTO eliminaIscrizioneByPartecipante(IscrizioneTO iscTO) 
			throws DatabaseException{

		iscTO = eliminaIscrizione(iscTO);

		EmailTO mail = this.toFact.createEmailTO();

		String emailSubj = this.sysConf.getString("mailAnnullaIscrSubj");
		mail.setOggetto(emailSubj);

		String mailMsg = this.sysConf.getString("mailAnnullaIscrPartMsg");
		String realMsg = MessageFormat.format(mailMsg, 
				iscTO.getPartecipante().getUsername(), 
				iscTO.getCompetizione().getNome());
		mail.setMessage(realMsg);

		mail.addDestinatario(iscTO.getCompetizione().getManagerDiCompetizione());

		this.agroludosMail.sendEmail(mail);

		return iscTO;
	}

	@Override
	public List<IscrizioneTO> getAllIscrizione() throws DatabaseException {
		IscrizioneDAO daoMan = getIscrizioneDAO(); 
		return daoMan.getAll();
	}

	@Override
	public List<IscrizioneTO> getAllIscrizioniAttive(PartecipanteTO parTO)
			throws DatabaseException {

		IscrizioneDAO daoMan = getIscrizioneDAO(); 
		return daoMan.getAllIscrizioniAttive(parTO);
	}

	/**
	 * il metodo restituisce vero se la competizione è "aperta alle iscrizioni" altrimenti
	 * soolleva l'eccezione {@link ChiuseIscrizioniException}
	 * 
	 * @param cmp
	 * @return boolean
	 * @throws ChiuseIscrizioniException
	 */
	private boolean isCompetizioneAperta(CompetizioneTO cmp)
			throws ChiuseIscrizioniException {

		boolean res = true;

		if(cmp.getStatoCompetizione().getId()!=1){
			throw new ChiuseIscrizioniException();
		}

		return res;
	}

	/**
	 * Il metodo restituisce true se esiste giò una iscrizione nella competizione associata all'iscrizione
	 * in input, altrimenti solleva l'eccezione {@link IscrizioneEsistenteException}
	 * @param isc
	 * @return boolean
	 * @throws DatabaseException
	 * @throws IscrizioneEsistenteException
	 */
	@Override
	public IscrizioneTO esisteIscrizione(IscrizioneTO isc) 
			throws DatabaseException, IscrizioneEsistenteException {

		List<IscrizioneTO> listIscCmp = getIscrizioneDAO().getIscrizioniAttiveCmp(isc.getCompetizione());

		for(IscrizioneTO tempIsc: listIscCmp){
			if(tempIsc.getPartecipante().getId()==isc.getPartecipante().getId()){
				throw new IscrizioneEsistenteException();
			}
		}

		return isc;
	}

	/**
	 * Il metodo restituisce false se nella competizione in input non è stato raggiunto il massimo numer
	 * di iscrizioni altrimenti solleva l'eccezione {@link NmaxRaggiuntoException}
	 * 
	 * @param cmp
	 * @return boolean
	 * @throws DatabaseException
	 * @throws NmaxRaggiuntoException
	 */
	private boolean numeroMaxRaggiunto(CompetizioneTO cmp) 
			throws DatabaseException, NmaxRaggiuntoException {

		boolean res = false;
		List<IscrizioneTO> listIscCmp = getIscrizioneDAO().getIscrizioniAttiveCmp(cmp);

		if(listIscCmp.size()==cmp.getNmax()){
			throw new NmaxRaggiuntoException();
		}

		return res;
	}

	@Override
	public List<IscrizioneTO> getAllIscrizioniAttiveByCmp(CompetizioneTO cmpTO)
			throws DatabaseException {

		List<IscrizioneTO> listIscCmp = getIscrizioneDAO().getIscrizioniAttiveCmp(cmpTO);
		return listIscCmp;
	}

	@Override
	public List<IscrizioneTO> getAllIscrizioniPartecipante(PartecipanteTO parTO)
			throws DatabaseException {
		List<IscrizioneTO> listIscCmp = getIscrizioneDAO().getAllIscrizioniPartecipante(parTO);
		return listIscCmp;
	}

}