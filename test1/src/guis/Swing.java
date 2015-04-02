package guis;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.io.*;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Swing {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Swing window = new Swing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Swing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane panel_1 = new JScrollPane();
		splitPane.setLeftComponent(panel_1);
		
		JScrollPane panel_2 = new JScrollPane();
		splitPane.setRightComponent(panel_2);
		
		//splitPane.setDividerLocation(315);	
		
		File[] files = getFileList(System.getenv("HOME"));

		final JList list = new JList(files);
		panel_1.add(list);		
		panel_1.setViewportView(list);
		
		final JTextArea text = new JTextArea();
		panel_2.add(text);
		panel_2.setViewportView(text);
		
		list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	try {
                		BufferedReader in = new BufferedReader(new FileReader(list.getSelectedValue().toString()));
                		String string = "", line = "";
                	
                		while ((line = in.readLine()) != null)	{
                			string += line + "\n";
                		}
                		string = string.substring(0, string.length()-2);
                		in.close();
                		
                		text.setText(string);

            		} catch (Exception ex)	{
            			System.out.println(ex.getMessage());
            		}
                }
            }
        });
	}

	private File[] getFileList(String folderpath)	{
		File folder = new File(folderpath);
		FileFilter filter = new FileFilter() {
			public boolean accept(File file) {
				return file.isFile() && 
						!file.toString().endsWith(".disk") &&
						!file.toString().endsWith(".iso") &&
						!file.toString().endsWith(".img");
			}
		};
		File[] files =  folder.listFiles(filter);

		return files;
	}
}
