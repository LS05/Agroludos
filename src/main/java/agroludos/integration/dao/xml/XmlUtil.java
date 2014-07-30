package agroludos.integration.dao.xml;

class XmlUtil {
	
	public static String hibDriver = "hibernate.connection.driver_class";
	public static String hibUsername = "hibernate.connection.username";
	public static String hibPassword = "hibernate.connection.password";
	public static String hibUrl = "hibernate.connection.url";
	public static String hibDialect = "hibernate.dialect";
	
	public static String getConfPath(){
		return "src/main/resources/hibernate.cfg.xml";
	}
	
	public static String getDriver(String tipoDB){
		String driver = null;
		
		if(tipoDB.toLowerCase().equals("mysql"))
			driver = "com.mysql.jdbc.Driver";
		
		return driver;
	}
	
	public static String getDialect(String tipoDB){
		String dialect = null;
		
		if(tipoDB.toLowerCase().equals("mysql"))
			dialect = "org.hibernate.dialect.MySQLDialect";
		
		return dialect;
	}
	
	public static String getUrl(String tipoDB){
		String url = null;
		
		if(tipoDB.toLowerCase().equals("mysql"))
			url = "jdbc:mysql://:";
		
		return url;
	
	}

}
