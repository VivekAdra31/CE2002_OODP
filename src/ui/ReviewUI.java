package ui;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import entities.Movie;
import entities.Review;
import entities.Reviews;
import manager.ReviewManager;

/*
 * UI for getting and setting reviews
 * @author Lakshyajeet Dwivedee
 */
public class ReviewUI {
	
	/*
	 * Add a review for a movie
	 * @param movie The movie for which the review is going to be added
	 */
	public static void addReview(String movie) {
		
		Scanner sc = new Scanner(System.in); //Initialise Scanner for IO

        //Ask for input thrice and then fail auth
        while (true) {
            try {

                System.out.println("Rate this movie from 1 to 5:");
                int rating = sc.nextInt();
                sc.nextLine();
                
                System.out.println("What is your review of the movie?:");
                String review = sc.nextLine();

                //If input is neither 0 nor 1, throw exception
                if (rating < 1 || rating > 5) {
                    throw new IOException();
                }
                
                Review movieReview = new Review(review, rating, movie);

                //If input is correct, print acknowledgement
                System.out.println("Thank you for your feedback!");
                ReviewManager.addReview(movieReview);
                return;

            } catch (IOException e) {

                //Catch exception if input is neither 1 nor 0
                System.out.println("Please enter a valid rating");

            } finally {}
        }
	}
	
	/*
	 * Show the reviews and ratings for a particular movie
	 * @param movie The movie for which the reviews and ratings have to be shown
	 */
    public static void printReviews(String movie) {
    	Reviews reviews = ReviewManager.getReviews(movie);
    	
    	if(reviews.getReviewList().size() <= 1) {
    		System.out.println("N/A");
    		return;
    	}
    	
    	System.out.println("Movie: " + movie);
    	
    	for(Review review : reviews.getReviewList()) {
    		System.out.println("Review: " + review.getReview());
    		System.out.println("Rating: " + String.valueOf(review.getRating()));
    		System.out.println("==============================================");
    	}
    }
    
    /*
     * Show just the average rating for a particular movie
	 * @param movie The movie for which the average rating has to be shown
     */
    public static void printAverageRating(String movie) {
    	Reviews reviews = ReviewManager.getReviews(movie);
    	
    	if(reviews.getReviewList().size() <= 1) {
    		System.out.println("Average Rating N/A");
    		return;
    	}
    	
    	System.out.println("Movie: " + movie);
    	System.out.println("Average Rating: " + reviews.getAverageRating());
    	System.out.println("==============================================");
    }
    
    /*
     * Print the top 5 movies and their average rating
	 * @param movies List of all currently showing movies
     */
    public static void printTop5(List<Movie> movies) {
    	List<Reviews> reviewList = ReviewManager.getTop5Movies(movies);
    	
    	System.out.println("Total Movies with reviews: " + reviewList.size());
    	
    	for(Reviews reviews : reviewList) {
    		System.out.println("Movie: " + reviews.getReviewList().get(0).getMovieName());
        	System.out.println("Average Rating: " + reviews.getAverageRating());
        	System.out.println("==============================================");
    	}
    }
}
