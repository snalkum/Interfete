package proiectinterfete;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexiune {
    public static Connection getConnection() throws Exception {
	
		String URL = "jdbc:mysql://localhost:3306/student";
		Connection c = DriverManager.getConnection(URL,"root","xtremez");
		
		return c;
	}
}
