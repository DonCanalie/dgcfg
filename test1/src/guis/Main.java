package guis;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class Main {

	protected Shell shlTestWindow;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Change window text
	 */
	public void setText(String text)
	{
		this.shlTestWindow.setText(text);
	}

	/**
	 * Open the window.
	 * @throws InterruptedException 
	 */
	public void open() throws InterruptedException {
		Display display = Display.getDefault();
		createContents();
		shlTestWindow.open();
		shlTestWindow.layout();
		while (!shlTestWindow.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlTestWindow = new Shell();
		shlTestWindow.setSize(450, 300);
		shlTestWindow.setText("Test Window");
		
		text_1 = new Text(shlTestWindow, SWT.BORDER);
		text_1.setBounds(163, 82, 75, 31);
		
		Button btnNewButton = new Button(shlTestWindow, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				text_1.setText(text_1.getText() + "1");
			}
		});
		btnNewButton.setBounds(163, 141, 92, 33);
		btnNewButton.setText("New Button");
		
		Combo combo = new Combo(shlTestWindow, SWT.NONE);
		combo.setBounds(32, 30, 193, 33);

	}
}
