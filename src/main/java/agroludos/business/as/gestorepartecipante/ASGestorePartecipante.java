package agroludos.business.as.gestorepartecipante;

import java.io.IOException;
import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.UserNotFoundException;
import agroludos.exceptions.ValidationException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.integration.dao.file.CertificatoSRCDAO;
import agroludos.integration.dao.file.FileDAOFactory;
import agroludos.integration.dao.file.FileFactory;
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
	public PartecipanteTO inserisciPartecipante(PartecipanteTO parto)
			throws DatabaseException, ValidationException {

		PartecipanteDAO daoPar = this.getPartecipanteDAO();

		this.validator.validate(parto);

		return daoPar.create(parto);

	}

	@Override
	public PartecipanteTO modificaPartecipante(PartecipanteTO parto)
			throws DatabaseException, ValidationException {

		PartecipanteDAO daoPar = this.getPartecipanteDAO();
		this.validator.validate(parto);

		PartecipanteTO part = (PartecipanteTO)daoPar.update(parto);

		return part;
	}

	private PartecipanteTO getSupp(PartecipanteTO user) 
			throws UserNotFoundException, IOException{
		CertificatoSRCDAO certFile = this.getCertificatoSRCDAO();

		if(user.getId() != -1){
			user.setCertificato(certFile.getCertificato(user.getSrc()));
		} else {
			throw new UserNotFoundException();
		}
		return user;
	}

	@Override
	public PartecipanteTO getPartecipante(PartecipanteTO parto)
			throws DatabaseException, IOException, UserNotFoundException {

		PartecipanteDAO daoPar = this.getPartecipanteDAO();
		PartecipanteTO user = daoPar.getByUsername(parto.getUsername());

		return getSupp(user);
	}

	@Override
	public PartecipanteTO getPartecipanteById(PartecipanteTO parto)
			throws DatabaseException, UserNotFoundException, IOException {

		PartecipanteDAO daoPar = this.getPartecipanteDAO();
		PartecipanteTO user = daoPar.getByID(parto.getId());

		return getSupp(user);
	}

	@Override
	public List<PartecipanteTO> getAllPartecipante() throws DatabaseException, IOException {

		PartecipanteDAO daoPar = this.getPartecipanteDAO();
		CertificatoSRCDAO certDao = this.getCertificatoSRCDAO();
		List<PartecipanteTO> res = daoPar.getAll();

		for(PartecipanteTO part : res){
			part.setCertificato(certDao.getCertificato(part.getSrc()));
		}

		return res;
	}

	//TODO deve ritornare un CertificatoTO
	@Override
	public String getCertificatoSRC(PartecipanteTO parto)
			throws DatabaseException, IOException {

		CertificatoSRCDAO certDao = this.getCertificatoSRCDAO();
		return certDao.getCertificato(parto.getSrc());

	}

	

}