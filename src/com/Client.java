package com;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Client {
	
	String name;
	int id;
	
	Client(int id,String name){
		this.id = id;
		this.name = name;
			}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	static void clfromdb() throws ClassNotFoundException, SQLException{
		
		List<Client> list = new ArrayList<Client>();
		
		if(Dbconnect.checkstatus()) {
			System.out.println("Already connected");
		} else {
			Dbconnect.connect();
		}
		
		list = Dbconnect.showdb();
		System.out.println("Данные из arraylist");
			
		for(Client arr: list) {
			System.out.print(arr.getId());
			System.out.println(" " + arr.getName());
		}
		
	}


	
	
}


