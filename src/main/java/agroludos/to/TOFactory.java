package agroludos.to;

public class TOFactory {
	public static DatabaseTO getDatabaseTO(){
		return new Database();
	}
}
