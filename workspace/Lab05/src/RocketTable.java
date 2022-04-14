import javax.swing.table.*;

public class RocketTable extends AbstractTableModel { 
	protected Rocket[] rockets;
	protected String[] columnNames = new String[] { "Name", "Price", "Apogee" };
	public RocketTable(Rocket[] rockets) {
		this.rockets = rockets; 
	} 
	//return column length
	public int getColumnCount() {
		return columnNames.length;
	} 
	//return i th column name
	public String getColumnName(int i) { 
		return columnNames[i];
	} 
	//return number of rocket
	public int getRowCount() { 
		return rockets.length;
	} 
	//get row th rocket and return col th column value
	public Object getValueAt(int row, int col) { 
		Object o = new Object();
		if(col == 0) {
			o = rockets[row].getName();
		}
		else if (col == 1) {
			o = rockets[row].getPrice();
		}
		else if (col == 2) {
			o = rockets[row].getApogee();
		}
		return o;
	} 
}
