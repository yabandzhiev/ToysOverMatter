package toysPackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Order {
	public int order_id;
	public int customer_id;
	public ArrayList<OrderItem> orderItems;
	
	public Order(int order_id, int customer_id, ArrayList<OrderItem> orderItems) {
		super();
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.orderItems = orderItems;
	}

	public ArrayList<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(ArrayList<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}



	public static ArrayList<Order> getAllOrders() {
		ArrayList<Order> orders = new ArrayList<Order>();
		String sql = "SELECT * FROM ORDERS;";
		DbContext.cn = DbContext.DoConnect();
		try {
			PreparedStatement state = DbContext.cn.prepareStatement(sql);
			ResultSet result = state.executeQuery();
			
			while (result.next()) {
				
				int order_id_db = result.getInt("ORDER_ID");
				int customer_id_db = result.getInt("CUSTOMER_ID");
				ArrayList<OrderItem> order_items_db = OrderItem.getOrderItems(order_id_db);
				
				Order order = new Order(order_id_db, customer_id_db, order_items_db);
				orders.add(order);
			}
			return orders;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static int addOrder(int customer_id, int toy_id, int quantity, double totalPrice) {
		String sqlInsert = String.format("INSERT INTO ORDERS (CUSTOMER_ID) VALUES (%d);", customer_id);
		String sqlGetOrderId = "SELECT SCOPE_IDENTITY();";
		String sqlInsertOrderItem = "";
		int order_id = 0;
		ResultSet result = null;
		
		DbContext.cn = DbContext.DoConnect();
		try {
			PreparedStatement state = DbContext.cn.prepareStatement(sqlInsert);
			state.execute();
			state = DbContext.cn.prepareStatement(sqlGetOrderId);
			result = state.executeQuery();
			
			if (result.next()) {
				order_id = result.getInt("SCOPE_IDENTITY()");
				sqlInsertOrderItem = String.format("INSERT INTO ORDER_ITEMS (ORDER_ID, TOY_ID, QUANTITY, LIST_PRICE) VALUES (%d, %d, %d, %s)", result.getInt(1), toy_id, quantity, totalPrice);
				state = DbContext.cn.prepareStatement(sqlInsertOrderItem);
				state.execute();
			}
			
			JOptionPane.showMessageDialog(null, "Item Added");
			return order_id;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return order_id;
	}
	
	public static void deleteOrder(int order_id) {
		String sql = String.format("DELETE FROM ORDERS WHERE ORDER_ID=%d;", order_id);
		String sqlDeleteOrderItem = String.format("DELETE FROM ORDER_ITEMS WHERE ORDER_ID=%d;", order_id);
		
		DbContext.cn = DbContext.DoConnect();
		try {
			PreparedStatement state = DbContext.cn.prepareStatement(sql);
			state.execute();
			
			DbContext.cn.prepareStatement(sqlDeleteOrderItem);
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
	
	public static void editOrder(int customer_id, int order_id) {
		String sqlUpdate = String.format("UPDATE ORDERS SET CUSTOMER_ID = %d WHERE ORDER_ID = %d;", customer_id, order_id);
		
		DbContext.cn = DbContext.DoConnect();
		try {
			PreparedStatement state = DbContext.cn.prepareStatement(sqlUpdate);
			state.execute();
			state = DbContext.cn.prepareStatement(sqlUpdate);
			state.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
