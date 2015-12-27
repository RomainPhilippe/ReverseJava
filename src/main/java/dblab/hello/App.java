package dblab.hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App
{
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/sakila";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "";

	public static void main( String[] args )
	{
		System.out.println( "Hello World!" );
		if (args.length >0)
		{
			int i=0;
			for (String argument : args)
			{
				System.out.format("argument [%d]: %s %n", i++, argument);
			}


			Connection conn = null;
			Statement stmt = null;
			try{
				//STEP 2: Register JDBC driver
				Class.forName("com.mysql.jdbc.Driver");

				//STEP 3: Open a connection
				System.out.println("Connecting to a selected database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				System.out.println("Connected database successfully...");

				//STEP 4: Execute a query
				System.out.println("Creating statement...");
				stmt = conn.createStatement();

				String sql=null;
				
				if(args[0].startsWith("SELECT")){
					sql = args[0];
					ResultSet rs = stmt.executeQuery(sql);
					//STEP 5: Extract data from result set
					System.out.println(queryHeader(rs));
					//
					
					while(rs.next()){
						//Retrieve by column name
						String last_name  = rs.getString("last_name");

						//Display values
						System.out.println("last_name: " + last_name);
					}
					rs.close();
				}else{
					sql = args[0];
					stmt.executeUpdate(sql);
				}

				
				
				
			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
			}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
			}finally{
				//finally block used to close resources
				try{
					if(stmt!=null)
						conn.close();
				}catch(SQLException se){
				}// do nothing
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					se.printStackTrace();
				}//end finally try
			}//end try







		}
	}
	
	public static final String queryHeader(ResultSet rs){
        String out = "";
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            for(int i=1; i<cols; i++){
                out += rsmd.getColumnName(i);
                if(i<cols-1)out += ",";
                out += " ";
            }
        } catch (SQLException e) {
            out = " ";
            //e.printStackTrace();
        }
        return "[ " + out + "]";
    }
}
