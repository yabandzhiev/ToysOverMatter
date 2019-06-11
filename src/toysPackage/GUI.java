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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class GUI {

	protected Shell shlTom;
	private Text inputField;
	private Text inputField_1;
	private Text inputField_2;
	private boolean hideThirdField = false;
	private String selectedCategory = "Toy";
	private Table table;
	private TableItem[] selectedItem;
	private String category = new String();
	private boolean priceSortAscending = true;
	
	// Load DB values
	private ArrayList<Toy> toys = Toy.getAllToys();
	private ArrayList<Customer> customers = Customer.getAllCustomers();
	private ArrayList<Order> orders = Order.getAllOrders();
	private Text inputField_3;
	private Text inputField_4;

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
		
				Label inputLabel1 = new Label(composite_2, SWT.NONE);
				inputLabel1.setLocation(59, 89);
				inputLabel1.setSize(75, 15);
				inputLabel1.setText("Name:");
				
						inputField = new Text(composite_2, SWT.BORDER);
						inputField.setLocation(199, 138);
						inputField.setSize(197, 21);
						
								Label inputLabel2 = new Label(composite_2, SWT.NONE);
								inputLabel2.setLocation(62, 145);
								inputLabel2.setSize(72, 15);
								inputLabel2.setText("Price:");
								
										inputField_1 = new Text(composite_2, SWT.BORDER);
										inputField_1.setLocation(201, 84);
										inputField_1.setSize(197, 21);
										
										Label dynamicOrderLbl = new Label(composite_2, SWT.NONE);
										dynamicOrderLbl.setBounds(546, 141, 0, 0);
										dynamicOrderLbl.setText("Order:");

										Label dynamicOrderPriceLbl = new Label(composite_2, SWT.NONE);
										dynamicOrderPriceLbl.setBounds(546, 89, 0, 0);
										dynamicOrderPriceLbl.setText("Price:");
										
										inputField_3 = new Text(composite_2, SWT.BORDER);
										inputField_3.setBounds(607, 138, 0, 0);
										inputField_3.setEditable(false);
										
										inputField_4 = new Text(composite_2, SWT.BORDER);
										inputField_4.setBounds(607, 89, 0, 0);
										inputField_4.setEditable(false);
										
												Label inputLabel3 = new Label(composite_2, SWT.NONE);
												inputLabel3.setSize(0, 0);
												inputField_2 = new Text(composite_2, SWT.BORDER);
												inputField_2.setSize(0, 0);
												
												table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
												table.setLocation(94, 279);
												table.setSize(426, 273);
												table.addSelectionListener(new SelectionAdapter() {
													@Override
													public void widgetSelected(SelectionEvent e) {
														selectedItem = table.getSelection();
														
														if (selectedCategory.equals("Toy")) {
															String toyName = selectedItem[0].getText();
															inputField_1.setText(toyName);
															
															
															for (Toy toy : toys) {
																if (toy.toy_name.equalsIgnoreCase(toyName)) {
																	inputField.setText(Double.toString(toy.list_price));
																	break;
																}
															}
														} else if (selectedCategory.equals("Customer")) {
															String egn = selectedItem[0].getText();
															inputField_2.setText(egn);
	
															
															for (Customer customer : customers) {
																if (customer.egn.equals(egn)) {
																	inputField_1.setText(customer.first_name);
																	inputField.setText(customer.last_name);
																	break;
																}
															}
														} else if (selectedCategory.equals("Order")) {
															int item_id = Integer.parseInt(selectedItem[0].getText());
															inputField_3.setText(Integer.toString(item_id));
															
															
															
															for (Order order : orders) {
																
																for (OrderItem orderItem : order.orderItems) {
																	if (orderItem.item_id == item_id) {
																		inputField_4.setText(Double.toString(orderItem.list_price));
																		
																		inputField_2.setText(Integer.toString(orderItem.quantity));
																		
																		for (Customer customer : customers) {
																			if (order.customer_id == customer.customer_id) {
																				inputField_1.setText(customer.egn);
																				break;
																			}
																		}
																		for (Toy toy : toys) {
																			if (toy.toy_id == orderItem.toy_id) {
																				inputField.setText(toy.toy_name);
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
												
												TableColumn tblInfoColumn = new TableColumn(table, SWT.NONE);
												TableColumn tblInfoColumnTwo = new TableColumn(table, SWT.NONE);
												TableColumn tblInfoColumnThree = new TableColumn(table, SWT.NONE);
												TableColumn tblInfoColumnFour = new TableColumn(table, SWT.NONE);
												TableColumn tblInfoColumnFive = new TableColumn(table, SWT.NONE);
												
												Combo egnCombo = new Combo(composite_2, SWT.NONE);
												
												egnCombo.addMouseListener(new MouseAdapter() {
													@Override
													public void mouseDown(MouseEvent e) {
														ArrayList<String> items = new ArrayList<String>();
														
														egnCombo.removeAll();
														
														for (Customer customer : customers) {
															String customerIdentification = String.format("%s | %s %s", customer.egn, customer.first_name, customer.last_name);
															items.add(customerIdentification);
														}
														for (int i = 0; i < items.size(); i++) {
															egnCombo.add(items.get(i));
														}
													}
												});
												
												
												egnCombo.setBounds(0, 0, 0, 0);
												
												Combo toyCombo = new Combo(composite_2, SWT.NONE);
												toyCombo.addMouseListener(new MouseAdapter() {
													@Override
													public void mouseDown(MouseEvent e) {
														ArrayList<String> items = new ArrayList<String>();
														
														toyCombo.removeAll();
														
														for (Toy toy : toys) {
															items.add(toy.toy_name);
														}
														for (int i = 0; i < items.size(); i++) {
															toyCombo.add(items.get(i));
														}
														
													}
													
												});
										
												
												toyCombo.setBounds(0, 0, 0, 0);
												
												
												
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
																	table.removeAll();
																}
																
																egnCombo.setBounds(0, 0, 0, 0);
																toyCombo.setBounds(0, 0, 0, 0);
																
																inputLabel1.setText("Name:");
																inputLabel2.setText("Price:");
																inputField.setText("");
																inputField_1.setText("");
																inputField_2.setText("");
																inputField_3.setText("");
																inputField_4.setText("");
																
																inputField.setBounds(199, 138, 197, 21);
																inputField_1.setBounds(201, 84, 197, 21);
																dynamicOrderLbl.setSize(0, 0);
																inputField_3.setSize(0, 0);
																dynamicOrderPriceLbl.setSize(0, 0);
																inputField_4.setSize(0, 0);

																if (hideThirdField) {
																	inputLabel3.setBounds(0, 0, 0, 0);
																	inputLabel3.setText("");
																	inputField_2.setBounds(0, 0, 0, 0);
																	hideThirdField = false;
																}	
																
																selectedCategory = "Toy";
																category = "Toy";
																
																tblInfoColumn.setWidth(126);
																tblInfoColumn.setText("Toy");
																tblInfoColumnTwo.setText("Price");
																tblInfoColumnTwo.setWidth(126);										
																tblInfoColumnThree.setWidth(0);
																tblInfoColumnFour.setWidth(0);
																tblInfoColumnFive.setWidth(0);
																
																 
																
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
																btnCustomer.setLocation(247, 43);
																btnCustomer.setSize(75, 25);
																btnCustomer.addSelectionListener(new SelectionAdapter() {
																	@Override
																	public void widgetSelected(SelectionEvent e) {
																		
																		if (!selectedCategory.equals("Customer")) {
																			table.removeAll();
																		}
																		if (category.equals("Customer")) {
																			table.removeAll();
																		}
																		
																		egnCombo.setBounds(0, 0, 0, 0);
																		toyCombo.setBounds(0, 0, 0, 0);
																		
																		inputLabel1.setText("First Name:");
																		inputLabel2.setText("Last Name:");
																		inputLabel3.setBounds(10, 170, 75, 15);
																		inputLabel3.setText("EGN:");
																		hideThirdField = true;
																		inputField.setText("");
																		inputField_1.setText("");
																		inputField_2.setText("");
																		inputField_3.setText("");
																		inputField_4.setText("");

																		inputField.setBounds(199, 138, 197, 21);
																		inputField_1.setBounds(201, 84, 197, 21);
																		inputField_2.setBounds(201, 170, 197, 21);
																		
																		dynamicOrderLbl.setSize(0, 0);
																		inputField_3.setSize(0, 0);
																		dynamicOrderPriceLbl.setSize(0, 0);
																		inputField_4.setSize(0, 0);
																		
																		selectedCategory = "Customer";
																		category = "Customer";
																		
																		tblInfoColumn.setWidth(83);
																		tblInfoColumn.setText("EGN");
																		tblInfoColumnTwo.setWidth(83);
																		tblInfoColumnTwo.setText("First Name");
																		tblInfoColumnThree.setWidth(82);
																		tblInfoColumnThree.setText("Last Name");
																		tblInfoColumnFour.setWidth(0);
																		tblInfoColumnFive.setWidth(0);
																		
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
																		btnOrder.setLocation(430, 43);
																		btnOrder.setSize(75, 25);
																		btnOrder.addSelectionListener(new SelectionAdapter() {	
																			@Override
																			public void widgetSelected(SelectionEvent e) {
																				
																				if (!selectedCategory.equals("Order")) {
																					table.removeAll();
																				}
																				if (category.equals("Order")) {
																					table.removeAll();
																				}
																				
																				toyCombo.setBounds(147, 138, 249, 21);
																				egnCombo.setBounds(147, 86, 249, 21);
																				
																				inputLabel1.setText("Customer:");
																				inputLabel2.setText("Toy:");
																				inputLabel3.setBounds(10, 170, 75, 15);
																				inputLabel3.setText("Quantity:");
																				hideThirdField = true;
																				inputField.setText("");
																				inputField_1.setText("");
																				inputField_2.setText("");
																				inputField_3.setText("");
																				inputField_4.setText("");
																				
																				inputField.setBounds(0, 0, 0, 0);
																				inputField_1.setBounds(0, 0, 0, 0);
																				inputField_2.setBounds(201, 170, 197, 21);
																				
																				
																				dynamicOrderLbl.setSize(55, 15);
																				inputField_3.setSize(177, 21);
																				dynamicOrderPriceLbl.setSize(55, 15);
																				inputField_4.setSize(177, 21);
																				
																				selectedCategory = "Order";
																				category = "Order";
																				
																				tblInfoColumn.setWidth(50);
																				tblInfoColumn.setText("Order");
																				tblInfoColumnTwo.setWidth(100);
																				tblInfoColumnTwo.setText("Customer EGN");
																				tblInfoColumnThree.setWidth(50);
																				tblInfoColumnThree.setText("Toy");
																				tblInfoColumnFour.setWidth(75);
																				tblInfoColumnFour.setText("Quantity");
																				tblInfoColumnFive.setWidth(60);
																				tblInfoColumnFive.setText("Price");							
																				
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
																		
																		tblInfoColumnTwo.addSelectionListener(new SelectionAdapter() {
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
																							String name = inputField_1.getText();
																							
																							
	
																							if (!inputField.getText().matches("[0-9]+") && !inputField.getText().matches("[0-9]+.[0-9]+"))  {
																								JOptionPane.showMessageDialog(null, "Error: Wrong Price!");
																								return;
																							}
																							Double price = Double.parseDouble(inputField.getText());
																							
																							if (!(name.trim().length() > 0)) {
																								JOptionPane.showMessageDialog(null, "Error: Empty Toy Name!");
																								return;
																							}
																							
																							
																							Toy.addToy(name, price);
																							toys = Toy.getAllToys();
																							inputField.setText("");
																							inputField_1.setText("");
																							
																							table.removeAll();
																							
																							for (Toy toy : toys) {
																								TableItem tableItem = new TableItem(table, SWT.NONE);
																								tableItem.setText(toy.getToy_name());
																								tableItem.setText(1,Double.toString(toy.list_price));
																							}
																							
																						} else if (selectedCategory.equals("Customer")) {
																							String firstName = inputField_1.getText();
																							String lastName = inputField.getText();
																							String egn = inputField_2.getText();
																							
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
																							
																							inputField.setText("");
																							inputField_1.setText("");
																							inputField_2.setText("");
																							
																							table.removeAll();
																							
																							for (Customer customer : customers) {
																								TableItem tableItem = new TableItem(table, SWT.None);
																								tableItem.setText(customer.egn);
																								tableItem.setText(1, customer.getFirst_name());
																								tableItem.setText(2, customer.getLast_name());
																							}
																						} else if(selectedCategory.equals("Order")) {
																							String egn = egnCombo.getText().substring(0, 10);
																							
																							String toy_name = toyCombo.getText();
																							int quantity = Integer.parseInt(inputField_2.getText());
																							int customer_id = -1;
																							int toy_id = -1;
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
																							
																							if(customer_id == -1 || toy_id == -1) {
																								JOptionPane.showMessageDialog(null, "Error: Invalid Customer/Toy!");
																								return;
																							}
																							
																							
																							order_id = Order.addOrder(customer_id, toy_id, quantity, totalPrice);
																							orders = Order.getAllOrders();
																							
																							inputField.setText("");
																							inputField_1.setText("");
																							inputField_2.setText("");
																							
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
																									orders = Order.getAllOrders();
																									selectedItem[0].dispose();
																								} else if (selectedCategory.equals("Customer")) {
																									String egn = selectedItem[0].getText();
																									
																									if (!egn.matches("[0-9]+") || egn.length() != 10) {
																										JOptionPane.showMessageDialog(null, "Error: Wrong EGN!");
																										return;
																									}
																									
																									Customer.deleteCustomer(egn);
																									customers = Customer.getAllCustomers();
																									orders = Order.getAllOrders();
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
																											String name = inputField_1.getText();
																												
																											
																											if (!inputField.getText().matches("[0-9]+") && !inputField.getText().matches("[0-9]+.[0-9]+"))  {
																												JOptionPane.showMessageDialog(null, "Error: Wrong Price!");
																												return;
																											}
																											Double price = Double.parseDouble(inputField.getText());
																											
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
																											String firstName = inputField.getText();
																											String lastName = inputField_1.getText();
																											String egn = inputField_2.getText();
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
																											
																											Customer.editCustomer(lastName, firstName, egn);
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
																											String egn = inputField_1.getText();
																											String toy_name = inputField.getText();
																											int item_id = Integer.parseInt(inputField_3.getText());
																											int quantity = Integer.parseInt(inputField_2.getText());
																											double price = Double.parseDouble(inputField_4.getText());
																											int toy_id = -1;
																											int customer_id = -1;
																											double totalPrice = 0;
																											
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
																													totalPrice = toy.list_price * quantity;
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
																											OrderItem.editOrderItem(item_id, egn, toy_id, quantity, totalPrice);
																											
																											
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
																								
																								Button btnSearch = new Button(composite_2, SWT.NONE);
																								btnSearch.addSelectionListener(new SelectionAdapter() {
																									@Override
																									public void widgetSelected(SelectionEvent e) {
																										
																										if (selectedCategory.equals("Toy")) {
																											ArrayList<Toy> searchToyItems = new ArrayList<Toy>();
																											
																											for (Toy toy : toys) {
																												if (toy.toy_name.toLowerCase().startsWith(inputField_1.getText().toLowerCase())) {
																													searchToyItems.add(toy);
																												}
																											}
																											table.removeAll();
																											
																											
																											tblInfoColumn.setWidth(126);
																											tblInfoColumn.setText("Toy");
																											tblInfoColumnTwo.setText("Price");
																											tblInfoColumnTwo.setWidth(126);										
																											tblInfoColumnThree.setWidth(0);
																											tblInfoColumnFour.setWidth(0);
																											tblInfoColumnFive.setWidth(0);
																											
																											for (Toy toy : searchToyItems) {
																												TableItem tableItem = new TableItem(table, SWT.NONE);
																												tableItem.setText(toy.getToy_name());
																												tableItem.setText(1,Double.toString(toy.list_price));
																											}
																										} else if (selectedCategory.equals("Customer")) {
																											ArrayList<Customer> searchCustomerItems = new ArrayList<Customer>();
																											
																											
																											for (Customer customer : customers) {
																												if (customer.first_name.toLowerCase().startsWith(inputField_1.getText().toLowerCase())) {
																													searchCustomerItems.add(customer);
																												}
																											}
																											table.removeAll();
																											
																											for (Customer customer : searchCustomerItems) {
																												TableItem tableItem = new TableItem(table, SWT.None);
																												tableItem.setText(customer.egn);
																												tableItem.setText(1, customer.getFirst_name());
																												tableItem.setText(2, customer.getLast_name());
																											}
																											
																										} else if (selectedCategory.equals("Order")) {
																											ArrayList<OrderItem> searchOrderItems = new ArrayList<OrderItem>();
																											
																											for (Order order : orders) {
																												for (OrderItem orderItem : order.orderItems) {
																													for (Customer customer : customers) {
																														if (order.customer_id == customer.customer_id) {
																															searchOrderItems.add(orderItem);
																														}
																													}
																													
																												}
																												
																											}
																											table.removeAll();
																											
																											for (Order order : orders) {
																												for (OrderItem orderItem : searchOrderItems) {
																													if (order.order_id != orderItem.order_id) {
																														break;
																													}
																													
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
																								btnSearch.setBounds(430, 84, 75, 25);
																								btnSearch.setText("Search");
																								
																								Button btnClear = new Button(composite_2, SWT.NONE);
																								btnClear.addSelectionListener(new SelectionAdapter() {
																									@Override
																									public void widgetSelected(SelectionEvent e) {
																										if (selectedCategory.equals("Toy")) {
																											table.removeAll();
																											
																											inputField_1.setText("");
																											inputField_2.setText("");
																											
																											for (Toy toy : toys) {
																												TableItem tableItem = new TableItem(table, SWT.NONE);
																												tableItem.setText(toy.getToy_name());
																												tableItem.setText(1,Double.toString(toy.list_price));
																											}
																										} else if (selectedCategory.equals("Customer")) {
																											table.removeAll();
																											
																											inputField.setText("");
																											inputField_1.setText("");
																											inputField_2.setText("");
																											inputField_3.setText("");
																											inputField_4.setText("");
																											
																											for (Customer customer : customers) {
																												TableItem tableItem = new TableItem(table, SWT.None);
																												tableItem.setText(customer.egn);
																												tableItem.setText(1, customer.getFirst_name());
																												tableItem.setText(2, customer.getLast_name());
																											}
																											
																										} else if (selectedCategory.equals("Order")) {
																											table.removeAll();
																											
																											inputField.setText("");
																											inputField_1.setText("");
																											inputField_2.setText("");
																											inputField_3.setText("");
																											inputField_4.setText("");
																											
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
																								btnClear.setBounds(430, 238, 75, 25);
																								btnClear.setText("Clear");
																								
																								
																								
	}
}
