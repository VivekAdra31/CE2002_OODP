package manager;

import java.util.ArrayList;
import java.util.List;

import entities.Movie;
import entities.Review;
import entities.Reviews;

import java.util.Collections;

import movielistingdao.ReviewDAO;
import utility.CSVRow;

/*
 * Control class for accessing the movie reviews
 *
 * @author Lakshyajeet Dwivedee
 */
public class ReviewManager {
	
	/*
	 * Get reviews and ratings for a particular movie by name
	 * @param movieName Name of the movie for which to get reviews
	 * @return Reviews object containing all reviews of this movie
	 */
	public static Reviews getReviews(String movieName) {
		List<CSVRow> table = ReviewDAO.getReviews();
		List<Review> reviewList = convertToReview(table);
		
		Reviews reviews = new Reviews();
		
		for(Review review : reviewList) {
			if(review.getMovieName().compareTo(movieName) == 0) {
				reviews.addReview(review.getReview(), review.getRating(), review.getMovieName());
			}
		}
		
		return reviews;
	}
	
	/*
	 * Add a review for a movie to the reviews database
	 * @param review The review to be added
	 */
	public static void addReview(Review review) {
		CSVRow data = new CSVRow();
    	
		data.addVariable(review.getReview());
		data.addVariable(String.valueOf(review.getRating()));
		data.addVariable(review.getMovieName());
		
		ReviewDAO.insertReview(data);
	}
	
	/*
	 * Return the top 5 movies based on average rating
	 * @param movies The list of all movies currently showing
	 * @return List of top 5 movies by average rating
	 */
	public static List<Reviews> getTop5Movies(List<Movie> movies) {		
		List<Reviews> allMovieReviews = new ArrayList<>();
		
		for(Movie movie : movies) {
			allMovieReviews.add(ReviewManager.getReviews(movie.getTitle()));
		}
		
		Collections.sort(allMovieReviews);
		
		List<Reviews> top5Reviews = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			
			if(allMovieReviews.get(i).getReviewList().size() == 0 || allMovieReviews.get(i).getReviewList().size() == 0) {
				continue;
			}
			top5Reviews.add(allMovieReviews.get(i));
			System.out.println("ADDED: " + allMovieReviews.get(i).getReviewList().size());
		}
		
		return top5Reviews;
	}

	/*
	 * Convert a list of CSVRow objects to a list of Review objects
	 * @param table The list of CSVRow objects to convert
	 * @return The list of Review objects
	 */
    private static List<Review> convertToReview(List<CSVRow> table) {
    	List<Review> reviews = new ArrayList<>();
    	for(CSVRow csvRow : table) {
    		List<String> row = csvRow.getRow();
    		Review tempReview = new Review(row.get(0), Integer.parseInt(row.get(1)), row.get(2));
    		reviews.add(tempReview);
    	}
    	return reviews;
    }
	
}
