package com;

import java.sql.*;
import java.util.ArrayList;
import java.util.List; 

public class Dbconnect {
	
	static Connection conn = null;
	static Statement stmnt = null;
	static PreparedStatement prstmnt = null;
	
	public static void connect() throws ClassNotFoundException, SQLException {
		
		try {
		Class.forName("org.postgresql.Driver");		
		String url = "jdbc:postgresql://localhost/Dealer";
		String login = "postgres";
		String pass = "admin";		
        conn = DriverManager.getConnection(url, login, pass);
        System.out.println("Соединение установлено");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException s) {
			System.out.println(s);
		}
	}	
		
	public static List<Client> showdb() throws SQLException {
		
		String frquery = "SELECT * FROM clients";
		try {
		stmnt = conn.createStatement();
		stmnt.execute(frquery);
		ResultSet rst = stmnt.getResultSet();
		List<Client> list = new ArrayList<Client>();
		
		System.out.println("Данные из базы данных: ");
		
		while(rst.next()) {
			
			list.add(new Client(rst.getInt("id"),rst.getString("name")));		
			//System.out.print(rst.getInt("id"));
			//System.out.println(" " +rst.getString("name"));
		}
		return list;
		} catch (SQLException s) {
		System.out.println(s);
		}
		return null;

	}
	
	public static void updatedb(int id, String name) throws SQLException {
	
	try {
		String updquery = "UPDATE clients SET name=? WHERE id=?";
		prstmnt = conn.prepareStatement(updquery);
		prstmnt.setInt(2, id);
		prstmnt.setString(1, name);
		prstmnt.executeUpdate();
	} catch (SQLException e) {
		System.out.println(e);
	}
}

	public static void delete(int id) throws SQLException {
		
	try {
		String delquery = "DELETE FROM clients WHERE id = '"+id+"'";
		prstmnt = conn.prepareStatement(delquery);
		prstmnt.executeUpdate();
	} catch (SQLException e) {
		System.out.println(e);
	}
}
	
	public static void insert(int id, String name) throws SQLException {
		
	try {
		String insert = "INSERT INTO clients(id, name) VALUES('"+id+"', '"+name+"')";
		prstmnt = conn.prepareStatement(insert);
		//prstmnt.setInt(1, id);
		//prstmnt.setString(2, name);
		prstmnt.executeUpdate();
	}catch (SQLException e) {
		System.out.println(e);
	}
}
	
	static boolean checkstatus() throws SQLException {
		try {
		if(conn.isValid(0)|| conn != null)
			return true;
		}catch (SQLException e) {
			System.out.println("Not connected");	
		}
		return false;
	}
		
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		

		Dbconnect.connect();
	//	Dbconnect.updatedb();
		Dbconnect.showdb();
	//	Dbconnect.delete(56);
	//	Dbconnect.insert(12, "Gina");
	//	Dbconnect.showdb();
		
    	Client.clfromdb();
		}
	}

