import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.Scanner;
import validation.pack.*;
//To be extracting member information from txt file to convert into members.

public class TestCase {
    public static void main(String[] args) {
        //The condition for the while loop to create user
        boolean isStillCreatingUser = true;
        //The default member object to call updateMemberCount with which will get member count up to where it is supposed to be 
        Member placeholder = new Member();
        placeholder.updateMemberCount();
        //Enters the while loop with try catch for making a member and reading from the file for a member.
        while(isStillCreatingUser) {
            try {
                //Creates a member object through signUpNewMember which is passed into saveMember which is the file writer. The append true is to avoid overwriting existing file info
                saveMember(signUpNewMember(), true);
                //Read file will read the entire file to ensure accuracy.
                readFile();
                //askToRegisterMoreMember method will prompt user to reply y or n that will result in a boolean variable being returned.
                isStillCreatingUser = askToRegisterMoreMember();
            } catch (Exception e) {
                e.printStackTrace();
            }//end of catch
        }//end of while loop
    }
    

    // Read file
    public static void readFile() { //throws IOException{
        try(BufferedReader reader = new BufferedReader(new FileReader("MemberList.txt"))) { //Try with resources.
            String line;
            System.out.println("Now printing the log info");
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            // reader.close(); // Writing will not be made if there is not a close statement.
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
    }//end of read file method

    //This method ask user to decide if they want to register more members then return the result as a boolean.
    public static Boolean askToRegisterMoreMember() {
        Scanner in = new Scanner(System.in);
        System.out.println("Do you wish to register another user? (Y/N)");
        String response = "";
        response = in.nextLine();
        return response.equalsIgnoreCase("n");
    }//end of askToRegisterMoreMember method

    //Write file by passing in a member object to extract information. Append file to not overwrite existing file information.
    public static void saveMember(Member newMember, boolean append) throws IOException{
        //Try-catch block to let it run
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("MemberList.txt", append));
            // Write the text to the file - can run with while loop
            writer.write(newMember.getUsername() + ", " + newMember.getPassword() + 
                 ", " + newMember.getDateOfBirth() + ", " + newMember.getId() + "\n");
            writer.close(); // Writing will not be made if there is not a close statement.
        } catch(IOException e) {
            e.printStackTrace();
        }
    }//end of writeFile class

    //Method to gather all information needed to make a member object
    public static Member signUpNewMember() throws Exception {
        //get all necessary information for creating a member account.
            Scanner input = new Scanner(System.in);
            boolean isRep = true;
            String thatUser = "";
            while(isRep) { //is repetitive is always true but flips to false when entered username does not already exist in file.
                System.out.println("Please enter in your desired username: ");
                thatUser = input.nextLine();
                // input.next();
                if(checkIfExistingInFile(thatUser)) {
                    System.out.println("Username already exist please use a different one");
                }else {
                    isRep = false;
                }
            }//end of username check 
            System.out.println("Please enter in your desired password: ");
            String pass = input.nextLine();
            // input.next(); //clearing the newline character left by the scanner.
            System.out.println("Please enter in your date of birth (mm/dd/yyyy): ");
            String dob = input.nextLine();
            // input.next();
            //end of information gathering
            Member x  = new Member(thatUser, pass, dob);
            //Member object is created and printed out to confirm.
            System.out.print("Your membership is made successfully.\nYour username is " + x.getUsername() + 
                ".\nYour password is " + x.getPassword() + ".\nYour member id is " + x.getId() + ".\n");
            // input.close();
            return x;
    }//end of sign up member class

    //This class will verify that the passed username is not matching any in the file.
    public static boolean checkIfExistingInFile(String passedText) throws IOException{
        try(BufferedReader reader = new BufferedReader(new FileReader("MemberList.txt"))) {
            String line;
            //Will check through reader for the passed text which may be username until it does not have any line left. 
            while((line = reader.readLine()) != null) {
                if(line.contains(passedText)) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

