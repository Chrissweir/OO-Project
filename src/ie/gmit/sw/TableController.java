package ie.gmit.sw;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableController extends JTable{
	private static final long serialVersionUID = 777L;	
	private TypeSummaryTableModel tm = null;
	private JTable table = null;
	private JScrollPane tableScroller;
	
	public TableController(){
		createTable();
	}
	
	/**
     * Method to get the table model from the table 
     *
     * @return
     * The TypeSummaryTableModel, which is the model for the table
     */
	public TypeSummaryTableModel getTableModel(){
	    return tm;
    } 
	
	
	/**
	 *  Method for creating the table.
	 */
	private void createTable(){
		tm = new TypeSummaryTableModel();
		table = new JTable(tm);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionBackground(Color.YELLOW);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TableColumn column = null;
		for (int i = 0; i < table.getColumnCount(); i++){
			column = table.getColumnModel().getColumn(i);
			if (i == 0){
				column.setPreferredWidth(60);
				column.setMaxWidth(60);
				column.setMinWidth(60);
			}else{
				column.setPreferredWidth(100);
				column.setMaxWidth(100);
				column.setMinWidth(100);
			}
		}
		tableScroller = new JScrollPane(table);
		tableScroller.setPreferredSize(new java.awt.Dimension(485, 235));
		tableScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
}
