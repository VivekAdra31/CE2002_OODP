import java.io.IOException;
import java.util.Scanner;

/*
 * Main class called on system startup
 * @author Lakshyajeet Dwivedee
 */
public class Startup {

    /**
     * Store if the user is a customer or staff (0 = Staff, 1 = Customer)
     */
    private static int userType;

    /**
     * Asks the user whether they are a customer or a staff member and store it into userType
     */

    private static void getType() {

        Scanner sc = new Scanner(System.in); //Initialise Scanner for IO

        //Keep asking for input in while loop until input is correct
        while (true) {
            try {
            	System.out.println("********************* Welcome To MOBLIMA ********************");
                System.out.println("Hi, are you a customer or a staff member? Enter 0 if staff, 1 if customer:");
                userType = sc.nextInt();

                //If input ie neither 0 nor 1, throw exception
                if (userType != 0 && userType != 1) {
                    throw new IOException();
                }

                //If input is correct, break out of while loop
                break;

            } catch (IOException e) {

                //Catch exception if input is neither 1 nor 0
                System.out.println("That is an invalid option.");

            }
        }

    }

    public static void main(String[] args) {

        //Ask user what type of user they are
       while (true) {
            getType();

            //Create a new StartupController with the input user type
            StartupController startupController = new StartupController(userType);
            startupController.showUserSystem();

        }
    }
}
