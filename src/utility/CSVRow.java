package utility;
import java.util.ArrayList;
import java.util.List;
/*
* Class holds data regarding a row in a CSV
* @author Lakshyajeet Dwivedee 
*/
public class CSVRow {

	/*
	 * Store all the attributes present in a row of the CSV
	 */
    private List<String> row = new ArrayList<>();

    /*
     * Inserts a new element into the row ArrayList
	 * obj must be given and must be of String type
	 * @param obj Variable to add to the row 
     */
    public void addVariable(String obj){
        row.add(obj);
    }
    
    /*
     * Returns the data stored in this row of the CSV
	 * @return The row containing all the variables in this row of the CSV 
     */
    public List<String> getRow(){
        return row;
    }
    
    /*
     * Modify the row data at the given position
     * @param position Position where change has to be made
     * @param newValue New value to be insrted into the row
     */
    public void Modify(int position,String newValue)
    {
    	row.set(position,newValue);
    }
}
