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

class ASGestorePartecipante extends AgroludosAS implements LPartecipante, SPartecipante{
	private PasswordEncryption pwdEnc;
	private FileFactory fileFactory;
	private AgroludosValidator validator;

	ASGestorePartecipante(PasswordEncryption pwdEnc, FileFactory fileFactory, AgroludosValidator validator){
		this.pwdEnc = pwdEnc;
		this.fileFactory = fileFactory;
		this.validator = validator;
	}

	private PartecipanteDAO getPartecipanteDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getPartecipanteDAO();
	}

	private TipoUtenteDAO getTipoUtenteDAO() throws DatabaseException {
		DBDAOFactory daoTipoUtente = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return daoTipoUtente.getTipoUtenteDAO();
	}

	private CertificatoSRCDAO getCertificatoSRCDAO() {
		FileDAOFactory daoFile = this.fileFactory.getDAOFactory(this.sysConf.getTipoCert());
		return daoFile.getCertificatoSRCDAO();
	}

	private void setDatiPartecipante(PartecipanteTO partTO)
			throws DatabaseException, ValidationException, IOException{

		this.validator.validate(partTO);

		CertificatoSRCDAO daoCert = this.getCertificatoSRCDAO();

		daoCert.salvaCertificato(partTO);

		partTO.setCertificato(daoCert.getCertificato(partTO));

	}

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

	@Override
	public PartecipanteTO modificaPartecipante(PartecipanteTO partTO)
			throws DatabaseException, ValidationException, IOException {

		PartecipanteDAO daoPar = this.getPartecipanteDAO();

		this.setDatiPartecipante(partTO);

		PartecipanteTO part = (PartecipanteTO)daoPar.update(partTO);

		return part;
	}

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
		List<PartecipanteTO> res = daoPar.getAll();

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
		if( dataSrc.isBefore(today) ){
			res = false;
		} else{
			res = true;
		}
		return res;
	}

}