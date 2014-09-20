package agroludos.business.as.gestorepartecipante;

import java.io.IOException;
import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.UserNotFoundException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.integration.dao.file.CertificatoSRCDAO;
import agroludos.integration.dao.file.FileDAOFactory;
import agroludos.integration.dao.file.FileFactory;
import agroludos.system.SystemConf;
import agroludos.to.PartecipanteTO;

class ASGestorePartecipante extends AgroludosAS implements LPartecipante, SPartecipante{

	private FileFactory fileFactory;
	private SystemConf sysConf;

	ASGestorePartecipante(SystemConf sysConf, FileFactory fileFactory){
		this.fileFactory = fileFactory;
		this.sysConf = sysConf;
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
	public boolean inserisciPartecipante(PartecipanteTO parto)
			throws DatabaseException {
		boolean res = false;
		//TODO Gestire scrittura percorso file del certificato
		PartecipanteDAO daoPar = this.getPartecipanteDAO();
		res = daoPar.create(parto);

		return res;
	}

	@Override
	public PartecipanteTO modificaPartecipante(PartecipanteTO parto)
			throws DatabaseException {
		PartecipanteDAO daoPar = this.getPartecipanteDAO();
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
}