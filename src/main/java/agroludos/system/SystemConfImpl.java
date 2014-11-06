package agroludos.system;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;

class SystemConfImpl implements SystemConf, HibernateConf, RulesErrorsConf, ReqConf{
	private CompositeConfiguration config;

	SystemConfImpl() throws ConfigurationException{
		this.config = new CompositeConfiguration();

		this.config.addConfiguration(new SystemConfiguration());
		this.config.addConfiguration(new PropertiesConfiguration("properties/main.properties"));

	}

	public void setTipoDB(String tipoDB){
		this.config.setProperty("db", tipoDB);
	}

	public String getTipoDB(){
		return this.config.getString("db");
	}

	public String getTipoConf(){
		return this.config.getString("configurazione");
	}

	public String getTipoCert(){
		return this.config.getString("certificato");
	}

	@Override
	public String getConfPath() {
		return this.config.getString("confPath");
	}

	@Override
	public String getDriver() {
		return this.config.getString("dbDriver");
	}

	@Override
	public String getHibDriver() {
		return this.config.getString("hibDriver");
	}

	@Override
	public String getHibDialect() {
		return this.config.getString("hibDialect");
	}

	@Override
	public String getDialect() {
		return this.config.getString("dbDialect");
	}

	@Override
	public String getHibUrl() {
		return this.config.getString("hibUrl");
	}

	@Override
	public String getUrl() {
		return this.config.getString("dbUrl");
	}

	@Override
	public String getHibUsername() {
		return this.config.getString("hibUsername");
	}

	@Override
	public String getHibPassword() {
		return this.config.getString("hibPassword");
	}

	@Override
	public String getErrorMessage(String ruleName) {
		return this.config.getString(ruleName);
	}

	@Override
	public String getErrorKey(String ruleName) {
		return this.config.getString(ruleName);
	}

	@Override
	public String getRequest(String requestName) {
		return this.config.getString(requestName);
	}

	@Override
	public String getRule(String ruleName) {
		return this.config.getString(ruleName);
	}

	@Override
	public String getString(String key) {
		return this.config.getString(key);
	}

	@Override
	public String getLang() {
		return this.config.getString("lang");
	}
}