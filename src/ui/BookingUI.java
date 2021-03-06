package ui;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import entities.Booking;
import manager.BookingManager;

/*
 * UI for printing the booking history of the user
 * @author Lakshyajeet Dwivedee
 */
public class BookingUI {	
	/*
	 * Show the booking history of the customer
	 * @param emailID The email ID of the customer
	 */
    public static void printReviews(String emailID) {
    	List<Booking> bookings = BookingManager.getBookings(emailID);
    	
    	if(bookings.size() == 0) {
    		System.out.println("N/A");
    		return;
    	}

    	for(Booking booking : bookings) {
    		booking.printBooking();
    		System.out.println("==============================================");
    	}
    }
}
