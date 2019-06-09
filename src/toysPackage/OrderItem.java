package toysPackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class OrderItem {
	public int order_id;
	public int item_id;
	public int toy_id;
	public int quantity;
	public double list_price;
	
	public OrderItem(int order_id, int item_id, int toy_id, int quantity, double list_price) {
		super();
		this.order_id = order_id;
		this.item_id = item_id;
		this.toy_id = toy_id;
		this.quantity = quantity;
		this.list_price = list_price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getList_price() {
		return list_price;
	}
	public void setList_price(double list_price) {
		this.list_price = list_price;
	}

	public static ArrayList<OrderItem> getOrderItems(int order_id) {
		ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
		String sql = String.format("SELECT * from ORDER_ITEMS WHERE ORDER_ID=%d;", order_id) ;
		DbContext.cn = DbContext.DoConnect();
		try {
			PreparedStatement state = DbContext.cn.prepareStatement(sql);
			ResultSet result = state.executeQuery();
			
			while (result.next()) {
				int order_id_db = result.getInt("ORDER_ID");
				int item_id_db = result.getInt("ITEM_ID");
				int toy_id_db = result.getInt("TOY_ID");
				int quantity_db = result.getInt("QUANTITY");
				double list_price_db = result.getDouble("LIST_PRICE");
				
				OrderItem orderItem = new OrderItem(order_id_db, item_id_db, toy_id_db, quantity_db, list_price_db);
				orderItems.add(orderItem);
			}
			return orderItems;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getOrderId(int item_id) {
		String sqlInsert = String.format("SELECT ORDER_ID FROM ORDER_ITEMS WHERE ITEM_ID = %d;", item_id);
		int order_id = 0;
		ResultSet result = null;
		
		DbContext.cn = DbContext.DoConnect();
		try {
			PreparedStatement state = DbContext.cn.prepareStatement(sqlInsert);
			result = state.executeQuery();
			
			if (result.next()) {
				order_id = result.getInt("ORDER_ID");
			}
			
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
	
	public static void editOrderItem(int item_id, String egn, int toy_id, int quantity, double price) {
		String sqlUpdate = String.format("UPDATE ORDER_ITEMS SET TOY_ID = %d, QUANTITY = %d, LIST_PRICE = %s WHERE ITEM_ID = %d;", toy_id, quantity, price, item_id);
		
		DbContext.cn = DbContext.DoConnect();
		try {
			PreparedStatement state = DbContext.cn.prepareStatement(sqlUpdate);
			state.execute();
			
			JOptionPane.showMessageDialog(null, "Item Edited");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
