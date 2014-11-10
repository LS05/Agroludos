package agroludos.business.as.gestoreiscrizione;

import java.text.MessageFormat;
import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.ChiuseIscrizioniException;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.IscrizioneEsistenteException;
import agroludos.exceptions.NmaxRaggiuntoException;
import agroludos.exceptions.ValidationException;
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
					this.validator.validate(iscTO);

					StatoIscrizioneDAO statoIscDAO = getStatoIscrizioneDAO();

					List<StatoIscrizioneTO> listSI = statoIscDAO.getAll();

					iscTO.setStatoIscrizione(listSI.get(1));

					iscTO = iscDAO.create(iscTO);
					
					//TODO invia email
					EmailTO mail = this.toFact.createEmailTO();
					String partUsername = iscTO.getPartecipante().getUsername();
					String compNome = iscTO.getCompetizione().getNome();
					
					String iscrObj = this.sysConf.getString("mailIscrizioneSubj");
					String mailObj = MessageFormat.format(iscrObj, partUsername, compNome);
					mail.setOggetto(mailObj);
					
					String iscrMsg = this.sysConf.getString("mailIscrizioneMsg");
					String mailMsg = MessageFormat.format(iscrMsg,
							iscTO.getPartecipante().toString(), 
							iscTO.getCosto(), 
							iscTO.getAllOptionals().toString());
					mail.setMessage(mailMsg);
					
					mail.addDestinatario(iscTO.getCompetizione().getManagerDiCompetizione());
					
					this.agroludosMail.sendEmail(mail);
				}
			}
		}
		return iscTO;
	}


	@Override
	public IscrizioneTO modificaIscrizioneByMdc(IscrizioneTO iscTO)
			throws DatabaseException {
		iscTO = modificaIscrizione(iscTO);

		//TODO invia email
		EmailTO mail = toFact.createEmailTO();
		mail.setOggetto("Modifica iscrizione");
		mail.setMessage(iscTO.getPartecipante().getUsername() + " abbiamo modificato l'iscrizione"
				+ " alla competizione " + iscTO.getCompetizione().getNome()
				+ " cambiando gli optional. I nuovi optional sono i seguenti: "
				+ iscTO.getAllOptionals().toString()
				+ " e il costo totale dell'iscrizione ora è: "
				+ iscTO.getCosto());
		
		mail.addDestinatario(iscTO.getPartecipante());

		return iscTO;
	}

	@Override
	public IscrizioneTO modificaIscrizioneByPartecipante(IscrizioneTO iscTO)
			throws DatabaseException {
		iscTO = modificaIscrizione(iscTO);

		//TODO invia email
		EmailTO mail = toFact.createEmailTO();
		mail.setOggetto("Modifca optional");
		mail.setMessage(iscTO.getPartecipante().getUsername() + " ha modificato l'iscrizione "
				+ "alla competizione " + iscTO.getCompetizione().getNome()
				+ " scegliendo i seguenti optional: "
				+ iscTO.getAllOptionals().toString());

		mail.addDestinatario(iscTO.getCompetizione().getManagerDiCompetizione());

		return iscTO;
	}

	private IscrizioneTO modificaIscrizione(IscrizioneTO iscTO)
			throws DatabaseException {
		IscrizioneDAO iscDAO = getIscrizioneDAO();
		return iscDAO.update(iscTO);
	}
	
	@Override
	public IscrizioneTO eliminaIscrizioneByMdc(IscrizioneTO iscTO) throws DatabaseException{
		iscTO = eliminaIscrizione(iscTO);

		//TODO invia email
		EmailTO mail = toFact.createEmailTO();
		mail.setOggetto("Iscrizione annullata");
		mail.setMessage(iscTO.getPartecipante().getUsername() + " abbiamo annullato l'iscrizione "
				+ "alla competizione " + iscTO.getCompetizione().getNome()
				+ "per i seguenti motivi: ");

		mail.addDestinatario(iscTO.getPartecipante());

		return iscTO;
	}

	@Override
	public IscrizioneTO eliminaIscrizioneByPartecipante(IscrizioneTO iscTO) throws DatabaseException{
		iscTO = eliminaIscrizione(iscTO);

		//TODO invia email
		EmailTO mail = toFact.createEmailTO();
		mail.setOggetto("Iscrizione annullata");
		mail.setMessage(iscTO.getPartecipante().getUsername() + " ha annullato l'iscrizione "
				+ "alla competizione " + iscTO.getCompetizione().getNome());

		mail.addDestinatario(iscTO.getCompetizione().getManagerDiCompetizione());

		return iscTO;
	}

	private IscrizioneTO eliminaIscrizione(IscrizioneTO iscTO)
			throws DatabaseException {

		IscrizioneDAO iscDAO = getIscrizioneDAO();

		StatoIscrizioneDAO statoIscDAO = getStatoIscrizioneDAO();
		StatoIscrizioneTO siTO = statoIscDAO.getAll().get(0);

		iscTO.setStatoIscrizione(siTO);

		return iscDAO.annullaIscrizione(iscTO);
	}


	@Override
	public List<IscrizioneTO> getAllIscrizione() throws DatabaseException {
		IscrizioneDAO daoMan = getIscrizioneDAO(); 
		return daoMan.getAll();
	}

	@Override
	public List<IscrizioneTO> getAllIscrizioniAttive(PartecipanteTO parTO) throws DatabaseException {
		IscrizioneDAO daoMan = getIscrizioneDAO(); 
		return daoMan.getAllIscrizioniAttive(parTO);
	}

	private boolean isCompetizioneAperta(CompetizioneTO cmp) throws ChiuseIscrizioniException {
		boolean res = true;
		if(cmp.getStatoCompetizione().getId()!=1)
			throw new ChiuseIscrizioniException();

		return res;
	}

	private boolean iscrizioneEsistente(IscrizioneTO isc) throws DatabaseException, IscrizioneEsistenteException {
		boolean res = false;
		List<IscrizioneTO> listIscCmp = getIscrizioneDAO().getIscrizioniAttiveCmp(isc.getCompetizione());

		for(IscrizioneTO tempIsc: listIscCmp){
			if(tempIsc.getPartecipante().getId()==isc.getPartecipante().getId())
				throw new IscrizioneEsistenteException();
		}
		return res;
	}

	private boolean numeroMaxRaggiunto(CompetizioneTO cmp) throws DatabaseException, NmaxRaggiuntoException {
		boolean res = false;
		List<IscrizioneTO> listIscCmp = getIscrizioneDAO().getIscrizioniAttiveCmp(cmp);

		if(listIscCmp.size()==cmp.getNmax())
			throw new NmaxRaggiuntoException();

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