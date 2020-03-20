package utility;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionManager {

	// This method helps to fetch jdbc properties from the property file
	public static Properties loadPropertiesFile() throws Exception {
		Properties prop = new Properties();
		InputStream in = ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
		prop.load(in);
		in.close(); 
		return prop;
	}

	// This method helps to connect with the database
	public final static Connection getConnection() throws Exception {
		
		Properties prop= null;
		
		prop = loadPropertiesFile();
		
		final String driver = prop.getProperty("driver");
		final String url = prop.getProperty("url");
		
		Class.forName(driver);  // Registering the jdbc driver
		Connection con = null;
		con = DriverManager.getConnection(url); // Connecting to database
		
		if(con!=null)
		{
			
			return con;
		}
		else
		{
			
			return null;
		}
		
		
		
	}
	
	
	
	
}
