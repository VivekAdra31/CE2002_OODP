package ui;
import java.util.Scanner;

import entities.Booking;
import entities.CinemaHall;
import entities.Movie;
import entities.MovieListing;

import java.util.Date;
import java.util.List;

import manager.BookingManager;
import manager.CustomerMovieListingManager;
import manager.PriceManager;
import utility.CSVFileIO;
/**
 * The main UI module for Customers.
 *
 * @author Gan Shyan
 */
public class CustomerUI {
    private Scanner scanner;
    private int sel = -1;
    private static final String sales_path="";
    public CustomerUI() {
        scanner = new Scanner(System.in);
    }

    public void startUp() {
        System.out.println("****** Welcome Customer ******");

        while (sel != 0) {
            System.out.println("Customer Module Main Menu:");
            System.out.println("(0): Exit Customer Module");
            System.out.println("(1): Access Movie Database");
            System.out.println("(2): Access Movie Listings Database");
            System.out.println("(3): Access Cineplex Locations Database");
            System.out.println("(4): Book Tickets!");
            System.out.println("(5): Check History of Bookings");
            System.out.println("(6): Add Rating and Review");
            System.out.println("(7): View Rating and Review");
            sel = scanner.nextInt();
            scanner.nextLine();

            switch (sel) {
                case 1:
                    new CustomerMovieUI().startUp();
                    break;
                case 2:
                    new CustomerMovieListingUI().startUp();
                    break;
                case 3:
                    new CustomerCineplexUI().startUp();
                    break;
                case 4:
                	bookingCall();
                	break;
                case 5:
                	System.out.println("Enter Email ID Used:");
                	String emailID = scanner.nextLine();
                	BookingUI.printReviews(emailID);
                case 6:
                	System.out.println("Enter Name of Movie to Review and Rate:");
                	//scanner.nextLine();
                	String tempMovie = scanner.nextLine();
                	ReviewUI.addReview(tempMovie);
                	break;
                case 7:
                	System.out.println("Enter Name of Movie to view Reviews and Rating");
                	String tempMovie1 = scanner.nextLine();
                	ReviewUI.printReviews(tempMovie1);
                	ReviewUI.printAverageRating(tempMovie1);
                	
                	
                	
            }
            
        }
    }
    /**
     * Function that simulates booking System
     * @author Vivek Adrakatti
     */
    public void bookingCall()
    {
    	BookingManager book = new BookingManager();
    	System.out.println("Enter Customer Name:");
    	String customerName = scanner.nextLine().trim().toUpperCase();
        System.out.println("Enter emailID:");
        String emailID=scanner.nextLine().trim().toUpperCase();
        System.out.println("Enter Mobile Number");
        int mobileNumber = scanner.nextInt();
        System.out.println("Enter Movie Name");
        scanner.nextLine();
        String movieName = scanner.nextLine();
        movieName.trim();
        CustomerMovieListingManager manager_listing = new CustomerMovieListingManager();
        
        List<MovieListing> relevantListings= manager_listing.searchMovieListingByFilmName(movieName);
       CustomerMovieListingUI db_module = new CustomerMovieListingUI();
       
       System.out.println(relevantListings.size());
        for(int i=0 ;i<relevantListings.size();i++)
        {
        	System.out.println("Enter "+ (i+1)+ " To choose this listing");
        	db_module.listMovieListing(relevantListings.get(i));
        }
        
        int a = scanner.nextInt();
        MovieListing to_book = relevantListings.get(a-1);
        to_book.showSeatAvailability();
        int row =0;
        while(true) {
        System.out.println("Enter Row(Enter -1 to stop booking)");
        row = scanner.nextInt();
        if(row==-1)break;
        System.out.println("Enter Collumn");
        int col = scanner.nextInt();
       
        if(!to_book.checkIfSeatFree(row,col))
        {
        	 System.out.println("Seat Not Free, Aborting!");break;
        }
        to_book.bookSeat(row, col);
        System.out.println("What Category do you fall Under?");
        System.out.println("Press 1 for Adult");
        System.out.println("Press 2 for Child");
        System.out.println("Press 3 for Senior");
        int b = scanner.nextInt();
        int price1=0;
        if(b==1)
        {
        	price1=PriceManager.fetch("Adult");
        }
        if(b==2)
        {
        	price1=PriceManager.fetch("Child");
        }
        if(b==3)
        {
        	price1=PriceManager.fetch("Senior");
        }
        Movie movie = to_book.getMovie();
        int type = movie.getTypeOfMovie();
        int price2=0;
        price2= PriceManager.fetch(type);
        CinemaHall cinemahall = to_book.getCinemaHall();
        int type2= cinemahall.getCinemaType();
        type2+=10;
        int price3=0;
        price3= PriceManager.fetch(type2);
        System.out.println("The Price for your ticket is: "+(price1+price2+price3)+" .Pay on collection of Tickets");
        
        Booking book_new = new Booking(customerName,emailID,mobileNumber, movieName,String.valueOf(to_book.getCinemaHall().getHallNumber()),to_book.getCineplexName());
    	BookingManager.insertBooking(book_new);
        }
    	
    
    	}
    }

