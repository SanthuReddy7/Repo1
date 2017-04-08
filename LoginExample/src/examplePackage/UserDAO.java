package examplePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {

	
	static Connection currentCon = null;
	static ResultSet rs = null;
	
	public static UserBean login(UserBean bean){
		
		//PREPARING SOME OBJECTS TO CONNECTION
		Statement stmt = null;
		
		String username = bean.getUsername();
		String password = bean.getPassword();
		
		String searchQuery = "select * from Users where username ="+username+" and password = "+password+"";

	//"Sysout prints the console: normally used to trace the process
		/*System.out.println("Your username is "+username);
		System.out.println("Your password is"+password);
		System.out.println("Query :"+searchQuery);*/
		try
		{
			//connet to database
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();
			
			//if user doesnot exit set the isValid to false
			if(!more)
			{
				System.out.println("Sorry , you are not a registered user! Please signup first");
				bean.setValid(false);
				
			}
			else if(more)
			{
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				
				System.out.println("Welcome" +firstName);
				bean.setFirstName(firstName);
				bean.setLastName(lastName);
				bean.setValid(true);
			}
		}
		
		catch (Exception ex){
			System.out.println("Login failed : An Exception has occurred!"+ex);
		}
		
		
		//some exception handling
		finally
		{
			if(rs!=null)
			{
				try{
					rs.close();
				}
				catch(Exception e){}
				rs = null;}
			if(stmt != null){
				try{
					stmt.close();
				}catch(Exception e){}
				stmt = null;}
			 if (currentCon != null) {
		            try {
		               currentCon.close();
		            } catch (Exception e) {
		            }

		            currentCon = null;
		         }
		      }

		return bean;
			
		      }			
					}
					
				
				
			
