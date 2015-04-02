package guis;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;

public class IOTest {

	private JFrame frame;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IOTest window = new IOTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public IOTest() throws IOException {
		initialize();
		
		textArea.setText("");
		
		PipedOutputStream pOut = new PipedOutputStream();
		System.setOut(new PrintStream(pOut));
		PipedInputStream pIn = new PipedInputStream(pOut);
		BufferedReader reader = new BufferedReader(new InputStreamReader(pIn));
		
		while(true) {
		    try {
		        String line = reader.readLine();
		        if(line != null) {
		            textArea.setText(textArea.getText() + "\n" + line);
		        }
		    } catch (IOException ex) {
		        // Handle ex
		    }
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

}
