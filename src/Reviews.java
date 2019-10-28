import java.util.*;

/**
 * Represents all the reviews and the overall rating of a movie
 * @author Lakshyajeet Dwivedee
 *
 */
public class Reviews {
	/**
	 * A list of all reviews of the movie
	 */
	private ArrayList<Review> reviewList;
	/*
	 * The overall rating of the movie
	 * Must be between 1 and 5
	 * Must have precision of 1 decimal place
	 */
	private float averageRating;
	
	/**
	 * Creates a new empty Reviews 
	 */
	public Reviews() {
		reviewList = new ArrayList<Review>();
		averageRating = 0;
	}
	
	/**
	 * Adds a new review to the review list and updates the average rating
	 * Both review and rating must be specified
	 * @param review Review of the movie given by the user
	 * @param rating Rating of the movie given by the user
	 */
	public void addReview(String review, int rating) {
		reviewList.add(new Review(review, rating));
		averageRating = ((averageRating)*(reviewList.size()-1) + rating)/reviewList.size();
	}
}