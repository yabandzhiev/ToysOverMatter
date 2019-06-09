package toysPackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Toy {
	public int toy_id;
	public String toy_name;
	public double list_price;
	
	public Toy(int toy_id, String toy_name, double list_price) {
		super();
		this.toy_id = toy_id;
		this.toy_name = toy_name;
		this.list_price = list_price;
	}
	
	public String getToy_name() {
		return toy_name;
	}
	public void setToy_name(String toy_name) {
		this.toy_name = toy_name;
	}
	public double getList_price() {
		return list_price;
	}
	public void setList_price(double list_price) {
		this.list_price = list_price;
	}
	
	public static ArrayList<Toy> getAllToys() {
		ArrayList<Toy> toys = new ArrayList<Toy>();
		String sql = "SELECT * from TOYS;";
		DbContext.cn = DbContext.DoConnect();
		try {
			PreparedStatement state = DbContext.cn.prepareStatement(sql);
			ResultSet result = state.executeQuery();
			
			while (result.next()) {
				int toy_id = result.getInt("TOY_ID");
				String toy_name = result.getString("TOY_NAME");
				double list_price = result.getDouble("LIST_PRICE");
				
				Toy toy = new Toy(toy_id, toy_name, list_price);
				toys.add(toy);
			}
			return toys;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void addToy(String name, double price) {
		String sql = String.format("INSERT INTO TOYS (TOY_NAME, LIST_PRICE) VALUES ('%s', %s);", name, price);
		
		DbContext.cn = DbContext.DoConnect();
		try {
			PreparedStatement state = DbContext.cn.prepareStatement(sql);
			state.execute();
			JOptionPane.showMessageDialog(null, "Item Added");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteToy(String name) {
		String sql = String.format("DELETE FROM TOYS WHERE TOY_NAME='%s';", name);
		
		DbContext.cn = DbContext.DoConnect();
		try {
			PreparedStatement state = DbContext.cn.prepareStatement(sql);
			state.execute();
			JOptionPane.showMessageDialog(null, "Item Removed.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void editToy(String name, double price) {
		String sql = String.format("UPDATE TOYS SET TOY_NAME = '%s', LIST_PRICE = '%s' WHERE TOY_NAME = '%s';", name, price, name);
		
		DbContext.cn = DbContext.DoConnect();
		try {
			PreparedStatement state = DbContext.cn.prepareStatement(sql);
			state.execute();
			JOptionPane.showMessageDialog(null, "Item Edited.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}