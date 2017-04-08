package examplePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	static Connection con;
	
	
	public static Connection getConnection()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","system1");
		}
		catch( SQLException ex){
			ex.printStackTrace();
			
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
		}
	
		return con;
		
	}
}
