package ie.gmit.sw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableColumn;

public class Controller extends JPanel {
	private JTable table;
	private TypeSummaryTableModel t;


	/**
	 * Create the panel.
	 */
	public Controller(TypeSummaryTableModel tm) {
		
		this.t = tm;
		table = new JTable(t);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionBackground(Color.YELLOW);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TableColumn column = null;
		for (int i = 0; i < table.getColumnCount(); i++){
			column = table.getColumnModel().getColumn(i);
			if (i == 0){
				column.setPreferredWidth(250);
				column.setMaxWidth(500);
				column.setMinWidth(100);
			}else{
				column.setPreferredWidth(60);
				column.setMaxWidth(500);
				column.setMinWidth(50);
			} // if

		} // for
		add(table);

	}
	/**
     * Method to get the table model from the table in the dialog window
     *
     * @return
     * The TypeSummaryTableModel, which is the model for the table in the dialog window
     */
	public TypeSummaryTableModel getTableModel(){
	    return t;
    } 

}
