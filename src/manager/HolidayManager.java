package manager;


import utility.CSVFileIO;

import utility.CSVRow;
import movielistingdao.HolidaysDAO;
/**
 * Class to Manage Holidays database
 * @author Vivek Adrakatti
 *
 */
public class HolidayManager {

	
	public static final String Date_Path="holidays.csv"; //File Path
	/**
	 * Function to write a date to CSV
	 * @param date
	 */
	public static void addHolidayDates(String date) {
    	HolidaysDAO.addHolidays(date);
		}
}
