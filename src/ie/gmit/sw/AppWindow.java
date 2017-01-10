package ie.gmit.sw;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Insets;

public class AppWindow {
	private JFrame frame;
	private String name;
	private JTextField txtFileName;
	private Controller cc;
	private TableController tc;
	private JTable table = new JTable();



	public AppWindow(){
		//Create a window for the application
		frame = new JFrame();
		frame.setTitle("B.Sc. in Software Development - GMIT");
		frame.setSize(550, 500);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new FlowLayout());

		//The file panel will contain the file chooser
		JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
		top.setBorder(new javax.swing.border.TitledBorder("Select a JAR File"));
		top.setPreferredSize(new Dimension(560, 150));
		top.setMaximumSize(new Dimension(560, 150));
		top.setMinimumSize(new Dimension(560, 150));

		// Create panel to hold text box with file path
		JPanel fileNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		fileNamePanel.setPreferredSize(new Dimension(500, 60));
		fileNamePanel.setMaximumSize(new Dimension(500, 60));
		fileNamePanel.setMinimumSize(new Dimension(500, 60));
		frame.getContentPane().add(top); //Add the panel to the window

		JLabel label = new JLabel("Selected JAR file path:");
		top.add(label);
		label.setPreferredSize(new Dimension(490, 20));
		label.setMinimumSize(new Dimension(100, 20));
		label.setMaximumSize(new Dimension(400, 20));
		label.setHorizontalTextPosition(SwingConstants.LEFT);

		txtFileName = new JTextField(44);
		txtFileName.setEditable(false);
		top.add(txtFileName);
		txtFileName.setPreferredSize(new Dimension(400, 30));
		txtFileName.setMinimumSize(new Dimension(400, 30));
		txtFileName.setMaximumSize(new Dimension(400, 30));
		txtFileName.setMargin(new Insets(4, 2, 2, 2));

		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		top.add(buttonPanel);
		buttonPanel.setPreferredSize(new Dimension(500, 40));
		buttonPanel.setMaximumSize(new Dimension(500, 40));
		buttonPanel.setMinimumSize(new Dimension(500, 40));

		JButton btnCalculate = new JButton("Calculate Stability"); //Create Quit button
		btnCalculate.setEnabled(false);

		JButton btnChooseFile = new JButton("Browse...");
		buttonPanel.add(btnChooseFile);
		btnChooseFile.setToolTipText("Select Jar File");
		btnChooseFile.setPreferredSize(new java.awt.Dimension(90, 30));
		btnChooseFile.setMaximumSize(new java.awt.Dimension(90, 30));
		btnChooseFile.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnChooseFile.setMinimumSize(new java.awt.Dimension(90, 30));
		btnChooseFile.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				JFileChooser fc = new JFileChooser("./");
				int returnVal = fc.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile().getAbsoluteFile();
					name = file.getAbsolutePath(); 
					txtFileName.setText(name);
					System.out.println("You selected the following file: " + name);
					btnCalculate.setEnabled(true);
				}
			}
		});


		//A separate panel for the programme output
		JPanel mid = new JPanel(new FlowLayout(FlowLayout.LEADING));
		mid.setBorder(new BevelBorder(BevelBorder.RAISED));
		mid.setPreferredSize(new java.awt.Dimension(500, 300));
		mid.setMaximumSize(new java.awt.Dimension(500, 300));
		mid.setMinimumSize(new java.awt.Dimension(500, 300));

		JPanel middle = new JPanel();
		middle.add(table);
		table.setPreferredSize(new Dimension(500, 250));
		table.setMaximumSize(new Dimension(500, 250));
		table.setMinimumSize(new Dimension(500, 250));
		frame.getContentPane().add(middle);
		middle.setPreferredSize(new Dimension(500, 250));
		middle.setMaximumSize(new Dimension(500, 250));
		middle.setMinimumSize(new Dimension(500, 250));

		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottom.setPreferredSize(new java.awt.Dimension(500, 50));
		bottom.setMaximumSize(new java.awt.Dimension(500, 50));
		bottom.setMinimumSize(new java.awt.Dimension(500, 50));


		btnCalculate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnCalculate.addActionListener(new ActionListener() {


					public void actionPerformed(ActionEvent evt) {

						// check if their is something entered in the filepath
						if(txtFileName.getText().length() > 1){
							try {
								MetricCalculator m = new MetricCalculator(name);
								tc = new TableController();
								// get handle on summary table model
								TypeSummaryTableModel tm = tc.getTableModel();

								// add metric data into table model
								tm.setTableData(m.getData());
								table.setModel(tm);

							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						else {

							System.out.println("No jar selected");
						} 
					}
				});
			}
		});

		JButton btnQuit = new JButton("Quit"); //Create Quit button
		btnQuit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		
		
		
		
		bottom.add(btnCalculate);
		bottom.add(btnQuit);

		frame.getContentPane().add(bottom);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new AppWindow();
	}
}
