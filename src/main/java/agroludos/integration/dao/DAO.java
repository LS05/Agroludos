package agroludos.integration.dao;


class DAO implements AgroludosDAO{
	
	@Override
	public String getDAOXmlPath(){
		return this.getClass().getResource("DAOFactory.xml").toString();
	}
	
	@Override
	public String getFilePath(){
		return this.getClass().getResource("FileDAOFactory.xml").toString();
	}
	
}
