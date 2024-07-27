import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.Scanner;
import validation.pack.*;
//To be extracting member information from txt file to convert into members.
//How to extract member information to login?
//How to prompt member to login vs create user.
//How to make history log work.

public class TestCase {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        //The condition for the while loop to create user
        boolean needToStillCreateNewUser = true;
        //The scanner to grab decisions form user
        System.out.println("Do you wish to login or sign up (type login or sign up)");
        String responseToLogin = input.nextLine();
        if(responseToLogin.equalsIgnoreCase("sign up")){
            //The default member object to call updateMemberCount with which will get member count up to where it is supposed to be 
            Member setting = new Member();
            setting.updateMemberCount();
            //Enters the while loop with try catch for making a member and reading from the file for a member.
            while(needToStillCreateNewUser) {
                try {
                    //Creates a member object through signUpNewMember which is passed into saveMemberInFile which is the file writer. The append true is to avoid overwriting existing file info
                    writeMemberInFile(signUpNewMember(), true);
                    //Read file will read the entire file to ensure accuracy.
                    readFile();
                    /*askToRegisterMoreMember method will prompt user to reply y or n that will result in a boolean variable being returned.
                    *condition controlling while loop to sign up more members
                    */
                    needToStillCreateNewUser = askToRegisterMoreMember();
                } catch (Exception e) {
                    e.printStackTrace();
                }//end of catch
            }//end of while loop
        }//End of sign up
        else {
            System.out.println("This is the login.");
            loginUser();
        }
    }
    

    // Read file
    public static void readFile() {
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

    // Check file for dataMatch class
    public static Member loginUser() {
        boolean choiceToLogin = true;
        while ((choiceToLogin)) {
            try(BufferedReader reader = new BufferedReader(new FileReader("MemberList.txt"))) { //Try with resources.
                String line;
                System.out.println("Enter your username: ");
                String usernameInput = input.nextLine();
                System.out.println("Enter your password: ");
                String passwordInput = input.nextLine();
                //A dataMatch object to compare the input information with the file.
                dataMatch forLogin = new dataMatch();
                
                System.out.println("Now comparing the logged information");
                //The file will be extracted line by line and broken into tokens to be compared with the username and password. 
                //The member will be returned that can probably be used for a logged in user.
                while((line = reader.readLine()) != null) {
                    forLogin.setCurrentLine(line);
                    if(forLogin.isMatchingUsernameAndPassword(usernameInput, passwordInput)) {
                        System.out.println("Login Successful!");
                        forLogin.printCurrentLine();
                        Member loggedInUser = forLogin.makeTheMember();
                        return loggedInUser;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } 
            System.out.println("Username or Password is not found, please choose to continue trying or exit (Type \"Continue\" or \"exit\")");
            String choiceAfterUserNotFound = input.nextLine();
            if(choiceAfterUserNotFound.equalsIgnoreCase("continue")){
                System.out.println("Try again");
            }
            else {
                return new Member(); //The main method will read a member object so it has to be null
            }
        }//loop to continue
        return new Member();
    }//end loginUser method

    //This method ask user to decide if they want to register more members then return the result as a boolean.
    public static Boolean askToRegisterMoreMember() {
        System.out.println("Do you wish to register another user? (Y/N)");
        String responseToMakeMoreUser = "";
        responseToMakeMoreUser = input.nextLine();
        return responseToMakeMoreUser.equalsIgnoreCase("y");
    }//end of askToRegisterMoreMember method

    //Write file by passing in a member object to extract information. Append file to not overwrite existing file information.
    public static void writeMemberInFile(Member newMember, boolean append) throws IOException{
        //Try-catch block to let it run
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("MemberList.txt", append));
            /* Write the text to the file - it can run with a while loop
             * text will be formatted and separated with a comma to allow better use of delimiters in the future.
             * a combination of getter class will be used
             * all line in the file should look like that
             */
            writer.write(newMember.getId() + "," + newMember.getFirstName() + "," + newMember.getLastName() + "," + 
                newMember.getUsername() + "," + newMember.getPassword() + 
                 "," + newMember.getDateOfBirth() + "\n");
            writer.close(); // Writing will not be made if there is not a close statement.
        } catch(IOException e) {
            e.printStackTrace();
        }
    }//end of writeFile class

    //Method to gather all information needed to make a member object
    public static Member signUpNewMember() throws Exception {
        //get all necessary information for creating a member account.
                //Used to loop the username 
            boolean needToRedoUserName = true;  
            //Get the first and last name of the user to be used for password check later.

            System.out.println(("What is your first name?"));
            String first = input.nextLine();

            System.out.println(("What is your last name?"));
            String last = input.nextLine();
            //Create a Valid object for checking the username and password.
            Valid userCheck = new Valid(8, 20, true, first, last);
            
            String thatUser = "";
            while(needToRedoUserName) { 
            //is always true but flips to false when entered username does not already exist in file and passes validation.
                System.out.println("Please enter in your desired username from 9 letter up to 20 letters (Must contain a special character of ascii 33 to 42): ");
                thatUser = input.nextLine();
                if(checkIfExistingInFile(thatUser)) {
                    System.out.println("Username already exist please use a different one");
                }else if(userCheck.isValidUsername(thatUser)) {
                    needToRedoUserName = false;
                }
            }//end of username check 

            //Password check
            System.out.println("Please enter in your desired password: ");
            String pass = input.nextLine();
            //need to be opposite because if  password is valid then it will return true which should not ask for password again
            while(!userCheck.isValidPassword(pass)) {
                System.out.println("Please enter in your desired password: ");
                pass = input.nextLine();
            }//end of password check

            System.out.println("Please enter in your date of birth (mm/dd/yyyy): ");
            String dob = input.nextLine();
            //end of information gathering
            Member x  = new Member(first, last, thatUser, pass, dob);
            //Member object is created and printed out to confirm.
            System.out.print("Your membership is made successfully.\nYour username is " + x.getUsername() + 
                ".\nYour password is " + x.getPassword() + ".\nYour member id is " + x.getId() + ".\n");
            return x;
    }//end of sign up member class

    //This class will verify that the passed username is not matching any in the file.
    public static boolean checkIfExistingInFile(String passedText) throws IOException{
        try(BufferedReader reader = new BufferedReader(new FileReader("MemberList.txt"))) {
            String line;
            //Will check through reader for the passed text which may be username until it does not have any line left. 
            while((line = reader.readLine()) != null) {
                //Allow the saved file line to compare against the lowercase 
                if(line.toLowerCase().contains(passedText.toLowerCase())) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

