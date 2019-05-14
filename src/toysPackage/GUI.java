package toysPackage;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GUI {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private  boolean hideCustomer=false;

	/**
	 * Launch the application.
	 * @param args
	 */
	

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
	
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		CTabFolder tabFolder = new CTabFolder(shell, SWT.BORDER);
		tabFolder.setBounds(0, 0, 434, 261);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmAdd = new CTabItem(tabFolder, SWT.NONE);
		tbtmAdd.setText("Add");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmAdd.setControl(composite);
		
		
		
		Label lblDynamic1 = new Label(composite, SWT.NONE);
		lblDynamic1.setBounds(10, 42, 75, 15);
		lblDynamic1.setText("Name:");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(109, 39, 197, 21);
		
		Label lblDynamic2 = new Label(composite, SWT.NONE);
		lblDynamic2.setBounds(10, 84, 99, 15);
		lblDynamic2.setText("Price:");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(159, 81, 92, 21);
		
		Label lblEGN = new Label(composite, SWT.NONE);
		text_2 = new Text(composite, SWT.BORDER);
		
		Button btnToy = new Button(composite, SWT.NONE);
		btnToy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblDynamic1.setText("Name:");
				lblDynamic2.setText("Price:");
				
				if (hideCustomer) {
					lblEGN.setBounds(0, 0, 0, 0);
					lblEGN.setText("");
					text_2.setBounds(0,0,0,0);
					hideCustomer=false;
				}
			}
		});
		btnToy.setBounds(10, 10, 75, 25);
		btnToy.setText("Toy");
		
		Button btnCustomer = new Button(composite, SWT.NONE);
		btnCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblDynamic1.setText("First Name:");
				lblDynamic2.setText("Last Name:");
				lblEGN.setBounds(10, 137, 55, 15);
				lblEGN.setText("EGN:");
				hideCustomer=true;
				
				text_2.setBounds(139, 134, 126, 21);
			}
		});
		
		Button btnOrder = new Button(composite, SWT.NONE);
		btnOrder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblDynamic1.setText("Name:");
				lblDynamic2.setText("Price:");
				if (hideCustomer) {
					lblEGN.setBounds(0, 0, 0, 0);
					lblEGN.setText("");
					text_2.setBounds(0,0,0,0);
					hideCustomer=false;
				}
				
			}
		});
		btnOrder.setBounds(343, 10, 75, 25);
		btnOrder.setText("Order");
		btnCustomer.setBounds(176, 10, 75, 25);
		btnCustomer.setText("Customer");
		
	
		
		
		CTabItem tbtmView = new CTabItem(tabFolder, SWT.NONE);
		tbtmView.setText("View");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmView.setControl(composite_1);
		composite_1.setLayout(null);

	}
}
