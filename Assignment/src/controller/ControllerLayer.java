package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import properties.Hotel;
import service.ServerLayer;

public class ControllerLayer {
	
	private static final ServerLayer sl=new ServerLayer();

	public static boolean isValidName(String name) {
		String regex="[A-Z][a-z]+";
		return name.matches(regex);
	}
	public static boolean isValidPrice(Double double1) {
		return true;
	}
	public static void isValidInputData(Hotel h) {
		if(!isValidName(h.getPname())) {
			throw new RuntimeException("Check the name entered "+h.getPname());
		}
		if(!isValidPrice(h.getPrice())) {
			throw new RuntimeException("Check the entered price"+h.getPrice());
		}
		
	}
	
	public static void addItemToMenu(Hotel h) throws ClassNotFoundException {
		isValidInputData(h);
		List<Hotel> existingProduct=viewWithName(h.getPname());
		if(!existingProduct.isEmpty()) {
			throw new RuntimeException("Product already available "+h.getPname());
		}
		addItem(h);
		System.out.println("Item added successfully");
	}
	public static void addItem(Hotel h) throws ClassNotFoundException {
		try {
			Connection c=sl.establishConnection();
			PreparedStatement ps=c.prepareStatement("insert into hotel_menu(pname,price)values(?,?);");
			ps.setString(1, h.getPname());
			ps.setDouble(2, h.getPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static List<Hotel> viewWithName(String name) throws ClassNotFoundException {
		try {
		Connection c=sl.establishConnection();
		PreparedStatement ps=c.prepareStatement("select * from hotel_menu where pname=?;");
		ps.setString(1, name);
		ResultSet rs=ps.executeQuery();
		List<Hotel> hotel=new ArrayList<>();
		while(rs.next()) {
			Integer id=rs.getInt("id");
			String pname=rs.getString("pname");
			Double price=rs.getDouble("price");
			Hotel h=new Hotel(id,pname,price);
			hotel.add(h);
		}
		return hotel;
		}
		catch (SQLException e){
			throw new RuntimeException(e.getMessage());
		}
	}
	public static void addItemInToMenu() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Product name");
		String name=sc.next();
		System.out.println("Enter price");
		Double price=sc.nextDouble();
		Hotel hotel=new Hotel(name,price);
		try {
			addItemToMenu(hotel);
		} catch (ClassNotFoundException e) {
			
			System.out.println(e.getMessage());
		}
	}
	
}
