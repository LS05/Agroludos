package agroludos.system;

public interface HibernateConf extends Conf{

	public String getConfPath();

	public String getDriver();

	public String getDialect();

	public String getUrl();

	public String getHibDriver();

	public String getHibUsername();

	public String getHibPassword();

	public String getHibUrl();

	public String getHibDialect();

}