package ui;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import entities.Booking;
import entities.CinemaHall;
import entities.Movie;
import entities.MovieListing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import manager.BookingManager;
import manager.CustomerMovieListingManager;
import manager.CustomerMovieManager;
import manager.PriceManager;
import movielistingdao.HolidaysDAO;
import utility.CSVFileIO;
import utility.CSVRow;
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
        
        CustomerMovieManager manager = new CustomerMovieManager();
        Movie movie;

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
                	/**
                	 * Function to Book Tickets
                	 */
                	bookingCall(); 
                	break;
                case 5:
                	System.out.println("Enter Email ID Used:");
                	String emailID = scanner.nextLine();
                	BookingUI.printReviews(emailID.toUpperCase());break;
                case 6:
                	System.out.println("Enter Name of Movie to Review and Rate:");
                	//scanner.nextLine();
                	String tempMovie = scanner.nextLine();
            		
            		movie = manager.searchMovie(tempMovie);
                	ReviewUI.addReview(movie.getTitle());
                	break;
                case 7:
                	System.out.println("Enter Name of Movie to view Reviews and Rating");
                	String tempMovie1 = scanner.nextLine();
                	movie = manager.searchMovie(tempMovie1);
                	ReviewUI.printReviews(movie.getTitle());
                	ReviewUI.printAverageRating(movie.getTitle());
                	
                	
                	
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
       
       if(relevantListings.size()==0)
       {System.out.println("No such movie");
       return;
       }
        for(int i=0 ;i<relevantListings.size();i++)
        {
        	System.out.println("Enter "+ (i+1)+ " To choose this listing");
        	db_module.listMovieListing(relevantListings.get(i));
        }
        
        int a = scanner.nextInt();
        MovieListing to_book = relevantListings.get(a-1);
        to_book.showSeatAvailability();
        int row =0;
        char row_char=0;
        while(true) {
        System.out.println("Enter Row(Enter $ to stop booking)");
        row_char = scanner.next().charAt(0);
        row= (int)(row_char-64);
        if(row_char=='$')break;
        row--;
        System.out.println("Enter Collumn");
        int col = scanner.nextInt();
       col--;
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
        ArrayList<CSVRow> holidays = HolidaysDAO.returnHolidays();
        int price4=0;
    	for(int i =0; i< holidays.size();i++)
    	{ 
    		String sDate1=holidays.get(i).getRow().get(0);  
    	    Date date1 = null;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
    		if(to_book.getShowTime().compareTo(date1)==0)
    		{
    			price4=5;
    		}
    	}
    	
        System.out.println("The Price for your ticket is: "+(price1+price2+price3+price4)+" .Pay on collection of Tickets");
        
        Booking book_new = new Booking(customerName,emailID,mobileNumber, movieName,String.valueOf(to_book.getCinemaHall().getHallNumber()),to_book.getCineplexName());
    	BookingManager.insertBooking(book_new);
        }
    	
    
    	}
    }

