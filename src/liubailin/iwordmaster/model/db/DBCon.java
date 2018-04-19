package liubailin.iwordmaster.model.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCon {
	
	public String dbName(){
		return "mircrosoft_bing_dic.db";
	}
	public String getPath() {	
		String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		File file = new File(path);
		path = file.getParent()+File.separatorChar;
		return path;
	}
	
	public Connection getConnection(){	
		Connection con = null;
	    try {
		      Class.forName("org.sqlite.JDBC");
		      con = DriverManager.getConnection("jdbc:sqlite:"+getPath()+dbName());
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ":" + e.getMessage() );
		    }
		return con;
	}
	
	public ResultSet doQuery(String sql, Object[] objs){
		ResultSet res = null;
		Connection con = getConnection();
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			for(int i = 0; i < objs.length; i++) {
				statement.setObject(i+1,objs[i]);
			}
			res = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
	
	public int doUpdate(String sql,Object[] objs){
		return -1;
	}
	
	public static void main(String[] args) {
		System.out.println(new DBCon().getPath());
	}
}
