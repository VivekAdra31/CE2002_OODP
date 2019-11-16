package utility;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
* Class finds specified CSV file and converts it into a 2D ArrayList of Strings
* @author Lakshyajeet Dwivedee 
*/
public class CSVFileIO {

	/*
	 * Store parsed CSV data as a list of CSV rows 
	 */
    private static ArrayList<CSVRow> CSVInfo = new ArrayList<>();
    
    /*
     * Parses CSV and returns it as a list of list of attributes in String form
	 * @param path Path to the CSV file which has to be read
	 * @return The ArrayList of CSVRow Arraylists
     */
    public static ArrayList<CSVRow> getParsedCSV(String path) {
    	
    	CSVInfo.clear();
    	
        // Declare a BufferedReader
        BufferedReader br = null;
        String line = ""; // Will store new line of CSV
        String splitter = ","; // Will store the delimiter to be used
        
        // Try catch block in case file is not found
        try {
            // Initialise BufferedReader with a new FileReader pointing to the input path
            br = new BufferedReader(new FileReader(path));
            
            //Iterate through each line of the CSV file till end reached
            while ((line = br.readLine()) != null) {

                // Use comma as separator
                String[] attributes = line.split(splitter);
                // Create a new CSVRow object
                CSVRow newRow = new CSVRow();
                // Fill up the newRow with the attributes present in this line of the CSV
                for(String var : attributes){
                	if(var.compareTo("")==0)
                	{
                		continue;
                	}
                    newRow.addVariable(var);
                }
                // Insert newRow into the CSVInfo table
                CSVInfo.add(newRow);
                
            }
        // If file is not found
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        // If there is some error in IO
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                // Try closing the BufferedReader
                try {
                    br.close();
                // If there is some error in IO
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return CSVInfo;
    }

    /*
     * Takes in a CSVRow and saves it to a CSV file
   	 * @return The ArrayList of CSVRow Arraylists 
     */
       public static void writeToCSV(String path, CSVRow csvRow) {
    	   FileWriter csvWriter = null;
    	   try {
    		   csvWriter = new FileWriter(path, true);
        	   
        	   List<String> row = csvRow.getRow();
        	   
        	   // Check if file is empty
    		   File file = new File(path);
    		   if(file.length() != 0) {
    			   csvWriter.append("\n");
    		   }
        	   
        	   csvWriter.append(String.join(",", row));
        	   
        	   csvWriter.flush();
        	   csvWriter.close();
        	   
    	   } catch (IOException e) {
               e.printStackTrace();
           } finally {}
    	   
       }
       
       /*
        * Rewrites a CSV file
        * @param path Path of the CSV file
        * @param table List containing CSVRow objects
        */
       public static void rewriteToCSV(String path, List<CSVRow> table) {
    	   
    	   FileWriter csvWriter = null;
    	   try {
    		   csvWriter = new FileWriter(path,false);
        	   for(CSVRow csvRow : table)
        	   {
        	  
        	   List<String> row = csvRow.getRow();
        	   
        	   csvWriter.append(String.join(",", row));
        	   csvWriter.append("\n");
        	   //csvWriter.append(String.join(",", row));
        	   }
        	   csvWriter.flush();
        	   csvWriter.close();
        	   
    	   } catch (IOException e) {
               e.printStackTrace();
           } finally {}
       }
       
       
}
