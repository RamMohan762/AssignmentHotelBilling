package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.ControllerLayer;
import properties.Hotel;


public class ServerLayer {
	
	private ControllerLayer cl=new ControllerLayer();

	public static void addItemInToMenu() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Product name");
		String name=sc.next();
		System.out.println("Enter price");
		Double price=sc.nextDouble();
		Hotel hotel=new Hotel(name,price);
		try {
			cl.addItemToMenu(hotel);
		} catch (ClassNotFoundException e) {
			
			System.out.println(e.getMessage());
		}
	}

	public static void viewMenu()  {
		Connection c;
		try {
			c = establishConnection();
		
		Statement st=c.createStatement();
		String query="select * from hotel_menu;";
		ResultSet rs=st.executeQuery(query);
		System.out.println("id     pname     price");
		
		while(rs.next()) {
			Integer id=rs.getInt("id");
			String pname=rs.getString("pname");
			Double price=rs.getDouble("price");
			Hotel hotel=new Hotel(id,pname,price);
			
			System.out.println(id+"\t"+pname+"\t"+price);
		}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}
	
	
public static void getPrice() {
		
		try {
	Connection c = establishConnection();
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Product name");
		String pname=scan.next();
		System.out.println("Enter quqntity");
		Integer quantity=scan.nextInt();
		String query="select price from hotel_menu where pname= ?;";
		PreparedStatement ps=c.prepareStatement(query);
		ps.setString(1,pname );
		ResultSet rs= ps.executeQuery();
		if(rs.next()) {
			Double price=rs.getDouble("price");
			price=quantity*price;
			System.out.println("The price of "+pname+" is "+price+" rupees.");
		}
		else {
			System.out.println("Product not found");
		}
		c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static Connection establishConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/set7_db","root","root");
		return c;
	}
	
}
