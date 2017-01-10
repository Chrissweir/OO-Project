package ie.gmit.sw;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableColumn;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Insets;

public class AppWindow {
	private JFrame frame;
	private String name;
	private TableController tc;
	private JTable table = new JTable();
	private JScrollPane tableScroller = new JScrollPane();
	private MetricCalculator m;

	public AppWindow(){
		//Create a window for the application
		frame = new JFrame();
		frame.setTitle("B.Sc. in Software Development - GMIT");
		frame.setSize(550, 550);
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

		JLabel label = new JLabel("Selected JAR file path:");
		label.setPreferredSize(new Dimension(490, 20));
		label.setMinimumSize(new Dimension(100, 20));
		label.setMaximumSize(new Dimension(400, 20));
		label.setHorizontalTextPosition(SwingConstants.LEFT);

		JTextField txtFileName = new JTextField(44);
		txtFileName.setEditable(false);
		txtFileName.setPreferredSize(new Dimension(400, 30));
		txtFileName.setMinimumSize(new Dimension(400, 30));
		txtFileName.setMaximumSize(new Dimension(400, 30));
		txtFileName.setMargin(new Insets(4, 2, 2, 2));

		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
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

		JPanel middle = new JPanel();

		middle.setVisible(false);
		middle.setPreferredSize(new Dimension(550, 240));
		middle.setMaximumSize(new Dimension(550, 240));
		middle.setMinimumSize(new Dimension(550, 240));


		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottom.setPreferredSize(new java.awt.Dimension(500, 50));
		bottom.setMaximumSize(new java.awt.Dimension(500, 50));
		bottom.setMinimumSize(new java.awt.Dimension(500, 50));


		btnCalculate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				// check if their is something entered in the filepath
				if(txtFileName.getText().length() > 1){
					try {
						m = new MetricCalculator(name);
						tc = new TableController();
						// get handle on summary table model
						TypeSummaryTableModel tm = tc.getTableModel();

						// add metric data into table model
						tm.setTableData(m.getData());
						//table.setColumnModel(tc.getColumnModel());
						table.setModel(tm);
						table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						table.setSelectionBackground(Color.YELLOW);

						table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

						TableColumn column = null;
						for (int i = 0; i < table.getColumnCount(); i++){
							column = table.getColumnModel().getColumn(i);
							if (i == 0){
								column.setPreferredWidth(250);
								column.setMaxWidth(250);
								column.setMinWidth(250);
							}else{
								column.setPreferredWidth(90);
								column.setMaxWidth(90);
								column.setMinWidth(90);
							}
						}

						middle.setVisible(true);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else {

					System.out.println("No jar selected");
				} 
			}
		});

		JButton btnQuit = new JButton("Quit"); //Create Quit button
		btnQuit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		top.add(label);
		top.add(txtFileName);
		top.add(buttonPanel);

		middle.add(tableScroller = new JScrollPane(table));
		tableScroller.setPreferredSize(new java.awt.Dimension(530, 300));
		tableScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		bottom.add(btnCalculate);
		bottom.add(btnQuit);

		frame.getContentPane().add(top);
		frame.getContentPane().add(middle);
		frame.getContentPane().add(bottom);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new AppWindow();
	}
}
