package ie.gmit.sw;

import javax.swing.table.*;
/**
 * @author Christopher Weir - G00309429
 *
 *	This Class is responsible for taking in the JAR class data 
 *  and structuring it for it to be used in a table.
 */
public class TypeSummaryTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 777L;
	private String[] cols = {"Class", "InDegree", "OutDegree", "Stability"};
	private Object[][] data = {
		{"Stuff 1", "Other Stuff 1", "Even More Stuff 1", "Extra Stuff 1"}
	};

	/**
	 * Sets the data that appears in the table.
	 *
	 * @param data
	 * The data, in the form of a 2 dimensional array.
	 */
	public void setTableData(Object[][] data){

		this.data = data;

	}
	
	public String[] getColumns(){
		return cols;
	}

	/**
	 * Gets the number of columns in the table.
	 *
	 * @return
	 * Returns the number as an int.
	 */
	public int getColumnCount() {

		return cols.length;
	}

	/**
	 * Gets the number of rows in the table.
	 *
	 * @return
	 * Returns the number as an int.
	 */
	public int getRowCount() {
		return data.length;
	}

	/**
	 * Gets the name of a particular column
	 * @param col
	 * The index the column appears at.
	 *
	 * @return
	 * The name of the column as a String.
	 */
	public String getColumnName(int col) {
		return cols[col];
	}

	/**
	 * Gets the value at a particular row and column in the table.
	 *
	 * @param row
	 * The index of the row in the table.
	 *
	 * @param col
	 * The index of the column in the table.
	 *
	 * @return
	 * Returns the value as an object
	 */
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	/**
	 * Gets the class that is in the first row of selected column.
	 *
	 * @param c
	 * The index of the column in the table.
	 *
	 * @return
	 * Returns the class of the object in the first row of selected column.
	 */
	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}