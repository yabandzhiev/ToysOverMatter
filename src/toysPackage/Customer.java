package toysPackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Customer {
	public int customer_id;
	public String first_name;
	public String last_name;
	public String egn;
	
	public Customer(int customer_id, String first_name, String last_name, String egn) {
		super();
		this.customer_id = customer_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.egn = egn;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEgn() {
		return egn;
	}
	public void setEgn(String egn) {
		this.egn = egn;
	}
	
	public static ArrayList<Customer> getAllCustomers() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		String sql = "SELECT * from CUSTOMERS;";
		DbContext.cn = DbContext.DoConnect();
		try {
			PreparedStatement state = DbContext.cn.prepareStatement(sql);
			ResultSet result = state.executeQuery();
			
			while (result.next()) {
				int customer_id = result.getInt("CUSTOMER_ID");
				String first_name = result.getString("FIRST_NAME");
				String last_name = result.getString("LAST_NAME");
				String egn = result.getString("EGN");
				
				Customer customer = new Customer(customer_id, first_name, last_name, egn);
				customers.add(customer);
			}
			return customers;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void addCustomer(String firstName, String lastName, String egn) {
		String sql = String.format("INSERT INTO CUSTOMERS (FIRST_NAME, LAST_NAME, EGN) VALUES ('%s', '%s', '%s');", firstName, lastName, egn);
		
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
	
	public static void deleteCustomer(String egn) {
		String sql = String.format("DELETE FROM CUSTOMERS WHERE EGN='%s';", egn);
		
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
	public static void editCustomer(String firstName, String lastName, String egn) {
		String sql = String.format("UPDATE Customers SET FIRST_NAME = '%s', LAST_NAME = '%s' WHERE EGN = '%s';", firstName, lastName, egn);
		
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
