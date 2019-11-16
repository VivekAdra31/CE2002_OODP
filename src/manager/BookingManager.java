package manager;

import java.util.ArrayList;
import java.util.List;

import bookingdao.BookingDAO;
import entities.Booking;
import utility.CSVRow;

/*
 * Control class for controling the bookings database
 *
 * @author Lakshyajeet Dwivedee
 */
public class BookingManager {
	
	/*
	 * Insert a new booking into BookingDB
     * @param booking Booking which has to be saved
	 */
	public static void insertBooking(Booking booking) {
		
		CSVRow data = new CSVRow();
    	
		data.addVariable(booking.getTransactionID());
		data.addVariable(booking.getCustomerName());
		data.addVariable(booking.getMovieName());
		data.addVariable(booking.getEmailID());
		data.addVariable(String.valueOf(booking.getMobileNumber()));
		data.addVariable(booking.getCinemaHallID());
		data.addVariable(booking.getCineplexName());
		
		BookingDAO.insertBooking(data);
	}

	/*
	 * Get bookings related to an email ID
	 * @param emailID The email ID of the user for whom to get the booking history
     * @return List of bookings related to an emailID
	 */
    public static List<Booking> getBookings(String emailID) {
    	
    	List<CSVRow> table = BookingDAO.getBookings();
    	List<Booking> bookingList = convertToBookings(table);
    	
        List<Booking> relevantBookings = new ArrayList<Booking>();
        for(Booking booking : bookingList) {
        	if(booking.getEmailID().compareTo(emailID) == 0) {
        		relevantBookings.add(booking);
        	}
        }
        return relevantBookings;
    }
    
    /*
     * Convert a list of CSVRow objects to a list of Booking objects
	 * @param table The list of CSVRow objects to convert
	 * @return The list of Booking objects
     */
    private static List<Booking> convertToBookings(List<CSVRow> table) {
    	List<Booking> bookings = new ArrayList<>();
    	for(CSVRow csvRow : table) {
    		List<String> row = csvRow.getRow();
    		if(row.size()==0) {continue;}
    		
    		//System.out.println(row.size());
    		Booking tempBooking = new Booking(row.get(1), row.get(3), Integer.parseInt(row.get(4)), row.get(2), row.get(5), row.get(6), row.get(0));
    		bookings.add(tempBooking);
    	}
    	return bookings;
    }
	
}
