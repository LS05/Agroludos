package agroludos.business.as.gestorepartecipante;

import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.UtenteEsistenteException;
import agroludos.exceptions.UserNotFoundException;
import agroludos.exceptions.ValidationException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.integration.dao.file.CertificatoSRCDAO;
import agroludos.integration.dao.file.FileDAOFactory;
import agroludos.integration.dao.file.FileFactory;
import agroludos.to.CertificatoTO;
import agroludos.to.PartecipanteTO;

class ASGestorePartecipante extends AgroludosAS implements LPartecipante, SPartecipante{

	private FileFactory fileFactory;
	private AgroludosValidator validator;

	ASGestorePartecipante(FileFactory fileFactory, AgroludosValidator validator){
		this.fileFactory = fileFactory;
		this.validator = validator;
	}

	private PartecipanteDAO getPartecipanteDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getPartecipanteDAO();
	}

	private CertificatoSRCDAO getCertificatoSRCDAO() {
		FileDAOFactory daoFile = this.fileFactory.getDAOFactory(this.sysConf.getTipoCert());
		return daoFile.getCertificatoSRCDAO();
	}

	@Override
	public PartecipanteTO inserisciPartecipante(PartecipanteTO partTO)
			throws DatabaseException, ValidationException, IOException {

		PartecipanteDAO daoPar = this.getPartecipanteDAO();

		if( !daoPar.esisteEmail(partTO) && !daoPar.esisteUsername(partTO) ){ 

			this.validator.validate(partTO);

			CertificatoSRCDAO daoCert = this.getCertificatoSRCDAO();

			daoCert.salvaCertificato(partTO);
			partTO.setCertificato(daoCert.getCertificato(partTO));

		} else {
			throw new UtenteEsistenteException();
		}

		return daoPar.create(partTO);

	}

	@Override
	public PartecipanteTO modificaPartecipante(PartecipanteTO parto)
			throws DatabaseException, ValidationException {

		PartecipanteDAO daoPar = this.getPartecipanteDAO();

		this.validator.validate(parto);

		PartecipanteTO part = (PartecipanteTO)daoPar.update(parto);

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
	public PartecipanteTO getPartecipanteById(PartecipanteTO parTO)
			throws DatabaseException, UserNotFoundException, IOException {

		PartecipanteDAO daoPar = this.getPartecipanteDAO();
		PartecipanteTO user = daoPar.getByID(parTO.getId());

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
		DateTime dataSrc = new DateTime(parTO.getDataSRC());
		DateTime today = new DateTime().withTimeAtStartOfDay();
		if( dataSrc.isBefore(today) ){
			return false;
		} else{
			return true;
		}
	}

}