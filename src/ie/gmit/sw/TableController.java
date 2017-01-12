package ie.gmit.sw;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author Christopher Weir - G00309429
 *
 */
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
	}
}
