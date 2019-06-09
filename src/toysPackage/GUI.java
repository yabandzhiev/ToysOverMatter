package toysPackage;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JOptionPane;
import org.eclipse.wb.swt.SWTResourceManager;

public class GUI {

	protected Shell shlTom;
	private Text text;
	private Text text_1;
	private Text text_2;
	private boolean hideThirdField = false;
	private String selectedCategory = new String();
	private Table table;
	private TableItem[] selectedItem;
	private String category = new String();
	private boolean priceSortAscending = true;
	
	// Load DB values
	private ArrayList<Toy> toys = Toy.getAllToys();
	private ArrayList<Customer> customers = Customer.getAllCustomers();
	private ArrayList<Order> orders = Order.getAllOrders();
	private Text text_3;
	private Text text_4;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlTom.open();
		shlTom.layout();
		while (!shlTom.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {

		shlTom = new Shell();
		shlTom.setImage(SWTResourceManager.getImage("C:\\Users\\sorrr\\eclipse-workspace\\ToysOverMatter\\driver\\tumblr_peg3gzvXyA1tc5gvpo3_250.png"));
		shlTom.setSize(850, 700);
		shlTom.setText("TOM");
		shlTom.setLayout(null);

		Composite composite_2 = new Composite(shlTom, SWT.NONE);
		composite_2.setBounds(0, 0, 834, 661);
		
				Label lblDynamic1 = new Label(composite_2, SWT.NONE);
				lblDynamic1.setLocation(59, 89);
				lblDynamic1.setSize(75, 15);
				lblDynamic1.setText("Name:");
				
						text = new Text(composite_2, SWT.BORDER);
						text.setLocation(199, 138);
						text.setSize(197, 21);
						
								Label lblDynamic2 = new Label(composite_2, SWT.NONE);
								lblDynamic2.setLocation(62, 145);
								lblDynamic2.setSize(99, 15);
								lblDynamic2.setText("Price:");
								
										text_1 = new Text(composite_2, SWT.BORDER);
										text_1.setLocation(201, 84);
										text_1.setSize(197, 21);
										
										Label lblNewLabel = new Label(composite_2, SWT.NONE);
										lblNewLabel.setBounds(546, 141, 0, 0);
										lblNewLabel.setText("Order:");

										Label lblPrice = new Label(composite_2, SWT.NONE);
										lblPrice.setBounds(546, 89, 0, 0);
										lblPrice.setText("Price:");
										
										text_3 = new Text(composite_2, SWT.BORDER);
										text_3.setBounds(607, 138, 0, 0);
										text_3.setEditable(false);
										
										text_4 = new Text(composite_2, SWT.BORDER);
										text_4.setBounds(607, 89, 0, 0);
										
												Label lblDynamic3 = new Label(composite_2, SWT.NONE);
												lblDynamic3.setSize(0, 0);
												text_2 = new Text(composite_2, SWT.BORDER);
												text_2.setSize(0, 0);
												
												table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
												table.setLocation(94, 279);
												table.setSize(576, 273);
												table.addSelectionListener(new SelectionAdapter() {
													@Override
													public void widgetSelected(SelectionEvent e) {
														selectedItem = table.getSelection();
														
														if (selectedCategory.equals("Toy")) {
															String toyName = selectedItem[0].getText();
															text_1.setText(toyName);
															
															
															for (Toy toy : toys) {
																if (toy.toy_name.equalsIgnoreCase(toyName)) {
																	text.setText(Double.toString(toy.list_price));
																	break;
																}
															}
														} else if (selectedCategory.equals("Customer")) {
															String egn = selectedItem[0].getText();
															text_2.setText(egn);
	
															
															for (Customer customer : customers) {
																if (customer.egn.equals(egn)) {
																	text.setText(customer.first_name);
																	text_1.setText(customer.last_name);
																	break;
																}
															}
														} else if (selectedCategory.equals("Order")) {
															int item_id = Integer.parseInt(selectedItem[0].getText());
															text_3.setText(Integer.toString(item_id));
															
															
															
															for (Order order : orders) {
																
																for (OrderItem orderItem : order.orderItems) {
																	if (orderItem.item_id == item_id) {
																		text_4.setText(Double.toString(orderItem.list_price));
																		
																		text_2.setText(Integer.toString(orderItem.quantity));
																		
																		for (Customer customer : customers) {
																			if (order.customer_id == customer.customer_id) {
																				text_1.setText(customer.egn);
																				break;
																			}
																		}
																		for (Toy toy : toys) {
																			if (toy.toy_id == orderItem.toy_id) {
																				text.setText(toy.toy_name);
																				break;
																			}
																		}
																		
																		break;
																	}
																}
															}
														}
														
													}
												});
												table.setHeaderVisible(true);
												table.setLinesVisible(true);
												
												TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
												TableColumn tblclmnNewColumnTwo = new TableColumn(table, SWT.NONE);
												TableColumn tblclmnNewColumnThree = new TableColumn(table, SWT.NONE);
												TableColumn tblclmnNewColumnFour = new TableColumn(table, SWT.NONE);
												TableColumn tblclmnNewColumnFive = new TableColumn(table, SWT.NONE);
												
												
												
														Button btnToy = new Button(composite_2, SWT.NONE);
														btnToy.setLocation(71, 43);
														btnToy.setSize(75, 25);
														btnToy.addSelectionListener(new SelectionAdapter() {
															@Override
															public void widgetSelected(SelectionEvent e) {
																
																if (!selectedCategory.equals("Toy")) {
																	table.removeAll();
																}
																if (category.equals("Toy")) {
																	return;
																}
																
																lblDynamic1.setText("Name:");
																lblDynamic2.setText("Price:");
																text.setText("");
																text_1.setText("");
																text_2.setText("");
																text_3.setText("");
																text_4.setText("");
																
																lblNewLabel.setSize(0, 0);
																text_3.setSize(0, 0);
																lblPrice.setSize(0, 0);
																text_4.setSize(0, 0);

																if (hideThirdField) {
																	lblDynamic3.setBounds(0, 0, 0, 0);
																	lblDynamic3.setText("");
																	text_2.setBounds(0, 0, 0, 0);
																	hideThirdField = false;
																}	
																
																selectedCategory = "Toy";
																category = "Toy";
																
																tblclmnNewColumn.setWidth(126);
																tblclmnNewColumn.setText("Toy");
																tblclmnNewColumnTwo.setText("Price");
																tblclmnNewColumnTwo.setWidth(126);										
																tblclmnNewColumnThree.setWidth(0);
																tblclmnNewColumnFour.setWidth(0);
																tblclmnNewColumnFive.setWidth(0);
																
																 
																
																for (Toy toy : toys) {
																	TableItem tableItem = new TableItem(table, SWT.NONE);
																	tableItem.setText(toy.getToy_name());
																	tableItem.setText(1,Double.toString(toy.list_price));
																}
															}
														});
														btnToy.setText("Toy");
																						//CUSTOMER
																Button btnCustomer = new Button(composite_2, SWT.NONE);
																btnCustomer.setLocation(248, 49);
																btnCustomer.setSize(75, 25);
																btnCustomer.addSelectionListener(new SelectionAdapter() {
																	@Override
																	public void widgetSelected(SelectionEvent e) {
																		
																		if (!selectedCategory.equals("Customer")) {
																			table.removeAll();
																		}
																		if (category.equals("Customer")) {
																			return;
																		}
																		
																		lblDynamic1.setText("First Name:");
																		lblDynamic2.setText("Last Name:");
																		lblDynamic3.setBounds(10, 170, 75, 15);
																		lblDynamic3.setText("EGN:");
																		hideThirdField = true;
																		text.setText("");
																		text_1.setText("");
																		text_2.setText("");
																		text_3.setText("");
																		text_4.setText("");

																		text_2.setBounds(201, 170, 197, 21);
																		
																		lblNewLabel.setSize(0, 0);
																		text_3.setSize(0, 0);
																		lblPrice.setSize(0, 0);
																		text_4.setSize(0, 0);
																		
																		selectedCategory = "Customer";
																		category = "Customer";
																		
																		tblclmnNewColumn.setWidth(83);
																		tblclmnNewColumn.setText("EGN");
																		tblclmnNewColumnTwo.setWidth(83);
																		tblclmnNewColumnTwo.setText("First Name");
																		tblclmnNewColumnThree.setWidth(82);
																		tblclmnNewColumnThree.setText("Last Name");
																		tblclmnNewColumnFour.setWidth(0);
																		tblclmnNewColumnFive.setWidth(0);
																		
																		for (Customer customer : customers) {
																			TableItem tableItem = new TableItem(table, SWT.None);
																			tableItem.setText(customer.egn);
																			tableItem.setText(1, customer.getFirst_name());
																			tableItem.setText(2, customer.getLast_name());
																		}
																	}
																});
																btnCustomer.setText("Customer");
																
																		Button btnOrder = new Button(composite_2, SWT.NONE);
																		btnOrder.setLocation(430, 53);
																		btnOrder.setSize(75, 25);
																		btnOrder.addSelectionListener(new SelectionAdapter() {	
																			@Override
																			public void widgetSelected(SelectionEvent e) {
																				
																				if (!selectedCategory.equals("Order")) {
																					table.removeAll();
																				}
																				if (category.equals("Order")) {
																					return;
																				}
																				
																				lblDynamic1.setText("EGN:");
																				lblDynamic2.setText("Toy:");
																				lblDynamic3.setBounds(10, 170, 75, 15);
																				lblDynamic3.setText("Quantity:");
																				hideThirdField = true;
																				text.setText("");
																				text_1.setText("");
																				text_2.setText("");
																				text_3.setText("");
																				text_4.setText("");
																				
																				text_2.setBounds(201, 170, 197, 21);
																				
																				
																				lblNewLabel.setSize(55, 15);
																				text_3.setSize(177, 21);
																				lblPrice.setSize(55, 15);
																				text_4.setSize(177, 21);
																				
																				selectedCategory = "Order";
																				category = "Order";
																				
																				tblclmnNewColumn.setWidth(50);
																				tblclmnNewColumn.setText("Order");
																				tblclmnNewColumnTwo.setWidth(100);
																				tblclmnNewColumnTwo.setText("Customer EGN");
																				tblclmnNewColumnThree.setWidth(50);
																				tblclmnNewColumnThree.setText("Toy");
																				tblclmnNewColumnFour.setWidth(75);
																				tblclmnNewColumnFour.setText("Quantity");
																				tblclmnNewColumnFive.setWidth(60);
																				tblclmnNewColumnFive.setText("Price");							
																				
																				for (Order order : orders) {
																					for (OrderItem orderItem : order.orderItems) {
																						
																						
																						
																						TableItem tableItem = new TableItem(table, SWT.NONE);
																						
																						tableItem.setText(Integer.toString(orderItem.item_id));
																						
																						for (Customer customer : customers) {
																							if (order.customer_id == customer.customer_id) {
																								tableItem.setText(1, customer.egn);
																								break;
																							}
																						}
																						for (Toy toy : toys) {
																							if (orderItem.toy_id == toy.toy_id) {
																								tableItem.setText(2, toy.toy_name);
																								break;
																							}
																						}
																						
																						tableItem.setText(3, Integer.toString(orderItem.quantity));
																						tableItem.setText(4, Double.toString(orderItem.list_price));

																					}
																				}

																			}
																		});
																		btnOrder.setText("Order");
																		
																		tblclmnNewColumnTwo.addSelectionListener(new SelectionAdapter() {
																			@Override
																			public void widgetSelected(SelectionEvent e) {
																				if (selectedCategory.equals("Toy")) {
																					
																					table.removeAll();
																					if (priceSortAscending) {
																						toys.sort(Comparator.comparingDouble(Toy::getList_price).reversed());
																						for (Toy toy : toys) {
								
																							TableItem tableItem = new TableItem(table, SWT.NONE);
																							tableItem.setText(toy.getToy_name());
																							tableItem.setText(1,Double.toString(toy.list_price));
																							priceSortAscending = false;
																						}
																					} else {
																						toys.sort(Comparator.comparingDouble(Toy::getList_price));
																						for (Toy toy : toys) {
								
																							TableItem tableItem = new TableItem(table, SWT.NONE);
																							tableItem.setText(toy.getToy_name());
																							tableItem.setText(1,Double.toString(toy.list_price));
																							priceSortAscending = true;
																						}
																					}
																				}
																			}
																		});
																		
																				Button btnAdd = new Button(composite_2, SWT.NONE);
																				btnAdd.setLocation(47, 238);
																				btnAdd.setSize(75, 25);
																				btnAdd.addSelectionListener(new SelectionAdapter() {
																					@Override
																					public void widgetSelected(SelectionEvent e) {
																						if (selectedCategory.equals("Toy")) {
																							String name = text_1.getText();
																							
																							
	
																							if (!text.getText().matches("[0-9]+") && !text.getText().matches("[0-9]+.[0-9]+"))  {
																								JOptionPane.showMessageDialog(null, "Error: Wrong Price!");
																								return;
																							}
																							Double price = Double.parseDouble(text.getText());
																							
																							if (!(name.trim().length() > 0)) {
																								JOptionPane.showMessageDialog(null, "Error: Empty Toy Name!");
																								return;
																							}
																							
																							
																							Toy.addToy(name, price);
																							toys = Toy.getAllToys();
																							text.setText("");
																							text_1.setText("");
																							
																							TableItem tableItem = new TableItem(table, SWT.NONE);
																							tableItem.setText(name);
																							tableItem.setText(1, Double.toString(price));
																							
																						} else if (selectedCategory.equals("Customer")) {
																							String firstName = text.getText();
																							String lastName = text_1.getText();
																							String egn = text_2.getText();
																							
																							if (!(firstName.trim().length() > 0)) {
																								JOptionPane.showMessageDialog(null, "Error: Empty First Name!");
																								return;
																							} else if (!(lastName.trim().length() > 0)) {
																								JOptionPane.showMessageDialog(null, "Error: Empty Last Name!");
																								return;
																							} else if (!egn.matches("[0-9]+") || egn.length() != 10) {
																								JOptionPane.showMessageDialog(null, "Error: Wrong EGN!");
																								return;
																							}
																							
																							
																							Customer.addCustomer(firstName, lastName, egn);
																							customers = Customer.getAllCustomers();
																							
																							text.setText("");
																							text_1.setText("");
																							text_2.setText("");
																							
																							TableItem tableItem = new TableItem(table, SWT.NONE);
																							tableItem.setText(1, firstName);
																							tableItem.setText(2, lastName);
																							tableItem.setText(0, egn);
																						} else if(selectedCategory.equals("Order")) {
																							String egn = text_1.getText();
																							String toy_name = text.getText();
																							int quantity = Integer.parseInt(text_2.getText());
																							int customer_id = 0;
																							int toy_id = 0;
																							double totalPrice = 0;
																							int order_id;
																							
																							
																							if (!egn.matches("[0-9]+") || egn.length() != 10) {
																								JOptionPane.showMessageDialog(null, "Error: Wrong EGN!");
																								return;
																							} else if (!(toy_name.trim().length() > 0)) {
																								JOptionPane.showMessageDialog(null, "Error: Empty Toy Name!");
																								return;
																							} else if (quantity < 1) {
																								JOptionPane.showMessageDialog(null, "Error: Invalid Quantity!");
																								return;
																							}
																							
																							
																							for (Customer customer : customers) {
																								if (customer.egn.equals(egn)) {
																									customer_id = customer.customer_id;
																									break;
																								}
																							}
																							for (Toy toy : toys) {
																								if (toy.toy_name.equalsIgnoreCase(toy_name)) {
																									toy_id = toy.toy_id;
																									totalPrice = toy.list_price * quantity;
																									break;
																								}
																							}
																							
																							if(customer_id == 0 || toy_id == 0) {
																								return;
																							}
																							
																							
																							order_id = Order.addOrder(customer_id, toy_id, quantity, totalPrice);
																							orders = Order.getAllOrders();
																							
																							text.setText("");
																							text_1.setText("");
																							text_2.setText("");
																							
																							TableItem tableItem = new TableItem(table, SWT.NONE);
																							tableItem.setText(Integer.toString(order_id));
																							tableItem.setText(1, egn);
																							tableItem.setText(2, toy_name);
																							tableItem.setText(3, Integer.toString(quantity));
																							tableItem.setText(4, Double.toString(totalPrice));
																						}
																					}
																				});
																				btnAdd.setText("Add");
																				
																						Button btnDelete = new Button(composite_2, SWT.NONE);
																						btnDelete.setLocation(160, 238);
																						btnDelete.setSize(75, 25);
																						btnDelete.addSelectionListener(new SelectionAdapter() {
																							@Override
																							public void widgetSelected(SelectionEvent e) {
																								
																								if (selectedCategory.equals("Toy")) {
																									String name = selectedItem[0].getText();
																									
																									
																									if (!(name.trim().length() > 0)) {
																										JOptionPane.showMessageDialog(null, "Error: Toy Name is empty!");
																										return;
																									}
																									
																									Toy.deleteToy(name);
																									toys = Toy.getAllToys();
																									selectedItem[0].dispose();
																								} else if (selectedCategory.equals("Customer")) {
																									String egn = selectedItem[0].getText();
																									
																									if (!egn.matches("[0-9]+") || egn.length() != 10) {
																										JOptionPane.showMessageDialog(null, "Error: Wrong EGN!");
																										return;
																									}
																									
																									Customer.deleteCustomer(egn);
																									customers = Customer.getAllCustomers();
																									selectedItem[0].dispose();
																								} else if (selectedCategory.equals("Order")) {
																									int item_id = Integer.parseInt(selectedItem[0].getText());	
																									int order_id = 0;
																									
																									for (Order order : orders) {
																										for (OrderItem orderItem : order.orderItems) {
																											if (orderItem.item_id == item_id) {
																												order_id = orderItem.order_id;
																												break;
																											}
																										}
																									}
																									
																									
																									Order.deleteOrder(order_id);
																									orders = Order.getAllOrders();
																									selectedItem[0].dispose();
																								}
																							}
																						});
																						btnDelete.setText("Delete");
																						
																								Button btnEdit = new Button(composite_2, SWT.NONE);
																								btnEdit.setLocation(345, 238);
																								btnEdit.setSize(75, 25);
																								btnEdit.setText("Edit");
																								
																								btnEdit.addSelectionListener(new SelectionAdapter() {
																									@Override
																									public void widgetSelected(SelectionEvent e) {
																										if (selectedCategory.equals("Toy")) {
																											String name = text_1.getText();
																												
																											
																											if (!text.getText().matches("[0-9]+") && !text.getText().matches("[0-9]+.[0-9]+"))  {
																												JOptionPane.showMessageDialog(null, "Error: Wrong Price!");
																												return;
																											}
																											Double price = Double.parseDouble(text.getText());
																											
																											if (!(name.trim().length() > 0)) {
																												JOptionPane.showMessageDialog(null, "Error: Empty Toy Name!");
																												return;
																											}
																											
																											Toy.editToy(name, price);
																											toys = Toy.getAllToys();
																											
																											table.removeAll();
																											for (Toy toy : toys) {
																												TableItem tableItem = new TableItem(table, SWT.NONE);
																												tableItem.setText(toy.getToy_name());
																												tableItem.setText(1,Double.toString(toy.list_price));
																											}
																											
																										} else if (selectedCategory.equals("Customer")) {
																											String firstName = text.getText();
																											String lastName = text_1.getText();
																											String egn = text_2.getText();
																											boolean egnExists = false;
																											
																											;
																											
																											if (!(firstName.trim().length() > 0)) {
																												JOptionPane.showMessageDialog(null, "Error: Empty First Name!");
																												return;
																											} else if (!(lastName.trim().length() > 0)) {
																												JOptionPane.showMessageDialog(null, "Error: Empty Last Name!");
																												return;
																											} else if (!egn.matches("[0-9]+") || egn.length() != 10) {
																												JOptionPane.showMessageDialog(null, "Error: Wrong EGN!");
																												return;
																											}
																											
																											for (Customer customer : customers) {
																												if (customer.egn.equals(egn)) {
																													egnExists = true;
																													break;
																												}
																											}
																											if (!egnExists) {
																												JOptionPane.showMessageDialog(null, "Error: EGN doesn't exist!");
																												return;
																											}
																											
																											Customer.editCustomer(firstName, lastName, egn);
																											customers = Customer.getAllCustomers();
																											
																											table.removeAll();
																											for (Customer customer : customers) {
																												TableItem tableItem = new TableItem(table, SWT.None);
																												tableItem.setText(customer.egn);
																												tableItem.setText(1, customer.getFirst_name());
																												tableItem.setText(2, customer.getLast_name());
																											}
																											
																										}
																										else if (selectedCategory.equals("Order")) {
																											String egn = text_1.getText();
																											String toy_name = text.getText();
																											int item_id = Integer.parseInt(text_3.getText());
																											int quantity = Integer.parseInt(text_2.getText());
																											double price = Double.parseDouble(text_4.getText());
																											int toy_id = -1;
																											int customer_id = -1;
																											
																											if (!egn.matches("[0-9]+") || egn.length() != 10) {
																												JOptionPane.showMessageDialog(null, "Error: Wrong EGN!");
																												return;
																											} else if (!(toy_name.trim().length() > 0)) {
																												JOptionPane.showMessageDialog(null, "Error: Empty Toy Name!");
																												return;
																											} else if (quantity < 1) {
																												JOptionPane.showMessageDialog(null, "Error: Invalid Quantity!");
																												return;
																											}
																											
																											for (Toy toy : toys) {
																												if (toy.toy_name.equalsIgnoreCase(toy_name)) {
																													toy_id = toy.toy_id;
																													break;
																												}
																											}
																											
																											if (toy_id == -1) {
																												JOptionPane.showMessageDialog(null, "Error: Toy doesn't exist!");
																												return;
																											}
																											
																											
																											
																											for (Customer customer : customers) {
																												if (egn.equals(customer.egn)) {
																													customer_id = customer.customer_id;
																													break;
																												}
																											}
																											if (customer_id == -1) {
																												JOptionPane.showMessageDialog(null, "Error: EGN doesn't exist!");
																												return;
																											}
																											
																											int order_id = OrderItem.getOrderId(item_id);
																											Order.editOrder(customer_id, order_id);
																											OrderItem.editOrderItem(item_id, egn, toy_id, quantity, price);
																											
																											
																											orders = Order.getAllOrders();
																											
																											table.removeAll();
																											for (Order order : orders) {
																												for (OrderItem orderItem : order.orderItems) {
																													
																													
																													
																													TableItem tableItem = new TableItem(table, SWT.NONE);
																													
																													tableItem.setText(Integer.toString(orderItem.item_id));
																													
																													for (Customer customer : customers) {
																														if (order.customer_id == customer.customer_id) {
																															tableItem.setText(1, customer.egn);
																															break;
																														}
																													}
																													for (Toy toy : toys) {
																														if (orderItem.toy_id == toy.toy_id) {
																															tableItem.setText(2, toy.toy_name);
																															break;
																														}
																													}
																													
																													tableItem.setText(3, Integer.toString(orderItem.quantity));
																													tableItem.setText(4, Double.toString(orderItem.list_price));

																												}
																											}
																											
																										}
																										
																									}
																								});

	}
}
