package agroludos.integration.dao.file;

import agroludos.integration.dao.file.txt.TxtDAOFactory;

class FileFactoryImpl implements FileFactory{

	private static TxtDAOFactory txtFact;

	FileFactoryImpl(TxtDAOFactory txtDaoFact){
		txtFact = txtDaoFact;
	}

	@Override
	public FileDAOFactory getDAOFactory(String tipo){
		FileDAOFactory res = null;

		if( "txt".equals(tipo) ){
			res = txtFact;
		}

		return res;
	}
}