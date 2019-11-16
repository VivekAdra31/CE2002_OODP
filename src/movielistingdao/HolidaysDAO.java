package movielistingdao;

import java.util.ArrayList;

import utility.CSVFileIO;
import utility.CSVRow;
/**
 *  Class to access holidays csv File
 * @author Vivek Adrakatti
 *
 */
public class HolidaysDAO {
	
		public static final String Date_Path="holidays.csv";//Path of holidays CSV file
		/**
		 * Function to write a date to CSV
		 * @param date
		 */
		public static void addHolidays(String date) {
			CSVRow newRow = new CSVRow();
			newRow.addVariable(date);
			CSVFileIO.writeToCSV(Date_Path,newRow);}
		
		/**
		 * Function to return ArrayList of CSVRow
		 * @return
		 */
		public static ArrayList<CSVRow> returnHolidays()
		{
		ArrayList<CSVRow> holidays =CSVFileIO.getParsedCSV("holidays.csv");
        holidays = CSVFileIO.getParsedCSV("holidays.csv");
        return holidays;
		}
	

}
