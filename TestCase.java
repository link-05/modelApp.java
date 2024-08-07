import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.Scanner;
import validation.pack.*;
import java.util.ArrayList;
//To be extracting member information from txt file to convert into members.
//How to extract member information to login?
//How to prompt member to login vs create user.
//How to make history log work.

public class TestCase {
    private static Scanner input = new Scanner(System.in);
    private static DataMatch compareData = new DataMatch();
    public static void main(String[] args) {
        //The condition for the while loop to create user
        boolean needToStillCreateNewUser = true;
        //The placeholder for the logged in user after user chooses to login or after sign up.
        Member loggedInUser = new Member();
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
            //prompt user to login after creating a new sign in.
            loginUser();
        }//End of sign up
        else { 
            System.out.println("This is the login.");
            loggedInUser = loginUser();
        }
        if(loggedInUser.getFirstName().equals("null")) return;
        //Start code for the event section or event feature of the code.
        if(doYouWishToSee("events")) {
            //The logged in user can now use the first feature of the system which is event.
            //List of all the events.
            ArrayList<Event> allEvents = new ArrayList<Event>();
            //Constructor here is a temporary solution until the actual permission system for member and admin are implemented.
            //Create all events for the list.
            Event firstTournament = new Event(1, "Academy Cup", "This is the tournament hosted by the Academy for Table Tennis, all member have ability to participate.",
                                                "Academy Of Table Tennis, 1st Building", "17:00", "07/31/2024");
            Event firstWideLesson = new Event(2, "Wide Lesson", "This is a unique wide lesson for all.", "Academy Of Table Tennis, 2nd Building", "18:00", "07/31/2024");
            //Add all events to the list.
            allEvents.add(firstTournament);
            allEvents.add(firstWideLesson);
            for(Event event : allEvents) {
                event.addEventToFile();
            }
            System.out.println("Here are all the events that we have: ");
            for(Event event : allEvents) {
                System.out.println(event);
            }
            //Ask user if they wish to attend an event.
            if(doYouWishToSee("and attend any events?")) {     
                System.out.println("Which event would you like to attend? (Please enter the event ID)");
                int eventId = Integer.parseInt(input.nextLine());
                DataMatch dataComparison = new DataMatch();
                if(dataComparison.isDataInFile("" + eventId, 0, "EventList.txt")) {
                    //Sign up the person for the follow up after signed area 
                    // addAttendee method will add the logged in user's ID to the event's attendees list.
                    dataComparison.findEventByID(allEvents, "" + eventId).addAttendee(loggedInUser.getId());
                    //code so the allEvents will end up being able to be organized back out as value of what. Quality of effectiveness is required.
                    //Check each mmeber in the code and move it so that the two are easier to add. for checkIn txt files.
                }
            }
        } //End of  event section

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

    // Check file for DataMatch class
    public static Member loginUser() {
        boolean choiceToLogin = true;
        System.out.println("This is the login screen.");
        while ((choiceToLogin)) {
            System.out.println("Enter your username: ");
            String usernameInput = input.nextLine();
            System.out.println("Enter your password: ");
            String passwordInput = input.nextLine();
            //A DataMatch object to compare the input information with the file.
            DataMatch forLogin = new DataMatch();
            //The method will find the data in the file and return the member that it corresponds to, if not found then a null first name member will be made.
            Member loggedInUser = forLogin.findDataInFile(usernameInput, passwordInput);
            if(!loggedInUser.getFirstName().equals("null")){
                return loggedInUser;
            }
            //These statements will ask for the next steps to login else it will repeat.
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
                if(compareData.isDataInFile(thatUser, 4, "MemberList.txt")) {
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

    public static boolean doYouWishToSee(String feature) {
        System.out.println("Do you wish to see " + feature + " (Yes or No)?");
        String response = input.nextLine();
        System.out.println(); //To keep the output clean by adding a new line.
        return response.equalsIgnoreCase("yes");
    }
}

