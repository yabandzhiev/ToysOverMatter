package toysPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import javax.swing.JOptionPane;

public class DbContext {
	public static Connection cn = null;

	static ResultSet result = null;

	public static Connection DoConnect() {
		try {	
			cn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		return cn;
	}

	public static  void getAllModel() {
		String sql = "SELECT * FROM CUSTOMERS\r\n" + 
				"WHERE CUSTOMER_ID=(SELECT CUSTOMER_ID FROM ORDERS\r\n" + 
				"WHERE ORDER_ID=(SELECT TOP 1 ORDER_ID FROM ORDER_ITEMS\r\n" + 
				"WHERE ORDER_ID=1));";
		cn = DoConnect();
		try {
			PreparedStatement state = cn.prepareStatement(sql);
			result = state.executeQuery();
			
			if (result.next()) {
				System.out.println(result.getString("FIRST_NAME"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}//end method
}