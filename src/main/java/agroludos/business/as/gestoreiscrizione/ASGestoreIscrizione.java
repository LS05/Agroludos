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
import agroludos.to.PartecipanteTO;
import agroludos.to.StatoIscrizioneTO;
import agroludos.utility.email.AgroludosMail;

class ASGestoreIscrizione extends AgroludosAS implements LIscrizione, SIscrizione{

	private AgroludosValidator validator;
	private AgroludosMail agroludosMail;

	ASGestoreIscrizione(AgroludosValidator validator, AgroludosMail agroludosMail){
		this.validator = validator;
		this.agroludosMail = agroludosMail;
	}

	private DBDAOFactory getDBDaoFactory() throws DatabaseException{
		return this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
	}

	private IscrizioneDAO getIscrizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.getDBDaoFactory();
		return dbDAOFact.getIscrizioneDAO();
	}

	private StatoIscrizioneDAO getStatoIscrizioneDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.getDBDaoFactory();
		return dbDAOFact.getStatoIscrizioneDAO();
	}

	private CompetizioneDAO getCompetizioneDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.getDBDaoFactory();
		return dbDAOFact.getCompetizioneDAO();
	}

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
			if(!iscrizioneEsistente(iscTO)){
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
		}

		return iscTO;
	}

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

	private IscrizioneTO modificaIscrizione(IscrizioneTO iscTO)
			throws DatabaseException {
		IscrizioneDAO iscDAO = getIscrizioneDAO();
		return iscDAO.update(iscTO);
	}

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

	private IscrizioneTO eliminaIscrizione(IscrizioneTO iscTO)
			throws DatabaseException {

		IscrizioneDAO iscDAO = getIscrizioneDAO();

		StatoIscrizioneDAO statoIscDAO = getStatoIscrizioneDAO();
		StatoIscrizioneTO siTO = statoIscDAO.getStatoDisattivo();

		iscTO.setStatoIscrizione(siTO);

		return iscDAO.annullaIscrizione(iscTO);
	}

	@Override
	public IscrizioneTO eliminaIscrizioneByMdc(IscrizioneTO iscTO) 
			throws DatabaseException{

		iscTO = eliminaIscrizione(iscTO);
		return iscTO;
	}

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

	private boolean isCompetizioneAperta(CompetizioneTO cmp)
			throws ChiuseIscrizioniException {

		boolean res = true;

		if(cmp.getStatoCompetizione().getId()!=1){
			throw new ChiuseIscrizioniException();
		}

		return res;
	}

	private boolean iscrizioneEsistente(IscrizioneTO isc) 
			throws DatabaseException, IscrizioneEsistenteException {

		boolean res = false;
		List<IscrizioneTO> listIscCmp = getIscrizioneDAO().getIscrizioniAttiveCmp(isc.getCompetizione());

		for(IscrizioneTO tempIsc: listIscCmp){
			if(tempIsc.getPartecipante().getId()==isc.getPartecipante().getId()){
				throw new IscrizioneEsistenteException();
			}
		}

		return res;
	}

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

	@Override
	public IscrizioneTO esisteIscrizione(IscrizioneTO iscTO) throws DatabaseException {
		IscrizioneTO res = null;
		if(iscTO.getId() != null){
			res = getIscrizioneDAO().findOne(iscTO.getId());
		}
		return res;
	}
}