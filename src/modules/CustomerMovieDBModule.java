package modules;

import java.util.List;
import java.util.Scanner;

import entities.Movie;
import managers.CustomerMovieDBManager;

/**
 * The UI class for the CUSTOMERS to access movie database module
 *
 * @author Gan Shyan
 */
public class CustomerMovieDBModule {
    private int sel = -1;
    private Scanner scanner;
    private CustomerMovieDBManager customerMovieDBManager;

    public CustomerMovieDBModule() {
        scanner = new Scanner(System.in);
        customerMovieDBManager = new CustomerMovieDBManager();
    }

    public void startUp() {
        System.out.println("****** Welcome To Movie Catalog ******\n");

        while (sel != 0) {
            System.out.println("Choose Operation: ");
            System.out.println("(0) - Exit Movie Catalog Module");
            System.out.println("(1) - List All Movies");
            System.out.println("(2) - Search For Movie");
            System.out.print("(3) - Search For Upcoming Movies");
            System.out.print("(4) - Search For Preview Movies");
            System.out.print("(5) - Search For Now Showing Movies");

            sel = scanner.nextInt();
            scanner.nextLine();

            switch (sel) {
                case 1:
                    listAllMovies();
                    break;
                case 2:
                    searchForMovie();
                    break;
                case 3:
                    searchForUpcomingMovies();
                    break;
                case 4:
                    searchForPreviewMovies();
                    break;
                case 5:
                    searchForNowShowingMovies();
                    break;
            }
        }
    }

    /**
     * Print all movies on the UI
     */
    private void listAllMovies() {
        for (Movie movie : customerMovieDBManager.getAllMovies()) {
            listMovie(movie);
            System.out.println("\n");
        }
    }

    /**
     * Search for a single movie using its name
     */
    private void searchForMovie() {
        System.out.println("Enter name of movie: ");
        String name = scanner.nextLine();

        Movie movie = customerMovieDBManager.searchMovie(name);
        if (movie == null) {
            System.out.println("No such movie found");
        } else {
            listMovie(movie);
            System.out.println("\n");
        }
    }

    /**
     * Search for upcoming movies
     */
    private void searchForUpcomingMovies() {

        List<Movie> movieList = customerMovieDBManager.getAllUpComingMovies();

        if (movieList.size() == 0) {
            System.out.println("No upcoming movies found");
        } else {
            for (Movie movie : movieList) {
                listMovie(movie);
                System.out.println("\n");
            }
        }
    }

    /**
     * Search for upcoming movies
     */
    private void searchForPreviewMovies() {
        List<Movie> movieList = customerMovieDBManager.getAllPreviewMovies();
        if (movieList.size() == 0) {
            System.out.println("No preview movies found");
        } else {
            for (Movie movie : movieList) {
                listMovie(movie);
                System.out.println("\n");
            }
        }
    }

    /**
     * Search for upcoming movies
     */
    private void searchForNowShowingMovies() {
        List<Movie> movieList = customerMovieDBManager.getAllNowShowingMovies();
        if (movieList.size() == 0) {
            System.out.println("No now showing movies found");
        } else {
            for (Movie movie : movieList) {
                listMovie(movie);
                System.out.println("\n");
            }
        }
    }


    /**
     * Display a single movie in the UI
     *
     * @param movie
     */
    private void listMovie(Movie movie) {
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Runtime: " + movie.getRunTime() + " minutes");
        System.out.print("Status: ");
        switch (movie.getStatus()) {
            case Movie.COMING_SOON:
                System.out.println("Coming soon");
                break;
            case Movie.PREVIEW:
                System.out.println("Preview");
                break;
            case Movie.NOW_SHOWING:
                System.out.println("Now Showing");
                break;
            case Movie.END_OF_SHOWING:
                System.out.println("End of showing");
                break;
        }
        System.out.println("Director: " + movie.getDirector());
        System.out.println("Synopsis: " + movie.getSynopsis());
        System.out.println("Casts: ");

        int i = 1;
        for (String cast : movie.getCasts()) {
            System.out.println(i + ": " + cast);
            i += 1;
        }

        int censor = movie.getCensorClassification();
        String censorStr;

        switch (censor) {
            case Movie.G:
                censorStr = "G";
                break;
            case Movie.PG:
                censorStr = "PG";
                break;
            case Movie.NC16:
                censorStr = "NC16";
                break;
            case Movie.M18:
                censorStr = "M18";
                break;
            case Movie.R21:
                censorStr = "R21";
                break;
            default:
                censorStr = "No Rating";
        }
        System.out.println("Censor Classification: " + censorStr);

        System.out.println("Average rating: " + movie.getReviews().getAverageRating());
    }
}
