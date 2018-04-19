package liubailin.iwordmaster.about;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDb {
	 public static void main( String args[] )
	  {
	    Connection con = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      con = DriverManager.getConnection("jdbc:sqlite:D:/mircrosoft_bing_dic.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    System.out.println("Opened database successfully");
	    try {
			Statement statement = con.createStatement();
			ResultSet res = statement.executeQuery("select * from Dict where word='good'");
			while(res.next()){
				System.out.println(res.getString("autosugg"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	  }
}
