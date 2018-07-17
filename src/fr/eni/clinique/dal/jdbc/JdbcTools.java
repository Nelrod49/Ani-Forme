package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import fr.eni.clinique.dal.jdbc.Settings;

public class JdbcTools{
	
	/*static{
		try{
			Class.forName(Settings.getProperty("driverdb"));
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}*/
	static{
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		Connection connection = null;
		/*String urldb = Settings.getProperty("urldb");
		connection = DriverManager.getConnection(
				urldb, Settings.getProperty("userdb"), 
				Settings.getProperty("passwordddb")
				);*/
		String urldb = "jdbc:sqlserver://localhost;databaseName=clinique;";
		connection = DriverManager.getConnection(
				urldb,"sa", 
				"Pa$$w0rd"
				);
		return connection;
	}
}
