package son.DAO;

import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DAOPostgreSQL implements DAOInterface {
	
	private Connection connection = null;
	private static DAOPostgreSQL instance = null;
	
	public static DAOPostgreSQL getInstance()  {
		if (instance ==null) {
			synchronized (DAOPostgreSQL.class){
				if (instance == null){
					instance = new DAOPostgreSQL();
				}
			}
		}
		return instance;
	}
	
	private DAOPostgreSQL(){
	
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? Ó + ÒInclude in your library path!");
			e.printStackTrace();
			return;
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");
		try {

			this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/son", "postgres",
			"jucatine");

			System.out.println("Successful Connected");
			} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

			}
		
		
	
	}
	
	

	public ArrayList<String> getCellList() throws SQLException{
		ArrayList<String> cells = new ArrayList<String>();
		
		Statement stat;
		ResultSet rs;
		ArrayList<String> output= new ArrayList<String>();
		try {
			stat = this.connection.createStatement();
			String query = "SELECT * FROM cm.cells";
			rs = stat.executeQuery(query);
			while (rs.next()) {
				output.add(rs.getString("cell_name"));
		    }
			return output;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
		return cells;
	
	}
	
	
	public boolean existCell(String cell) throws SQLException{		
		Statement stat;
		ResultSet rs;
		try {
			stat = this.connection.createStatement();
			String query = "SELECT * FROM cm.cells where cell_name = '" + cell + "'";
			rs = stat.executeQuery(query);
			while (rs.next()) {
				return true;
		    }
			return false;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	
	}
	

	

}
