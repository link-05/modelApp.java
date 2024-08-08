import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DataMatch {
    private String[] currentLine;

    //Constructor to set the current line being processed.
    public DataMatch() {
        this.currentLine = null;
    }
    //First Method to call in order to set the current line being processed. Current line is the line read by the file reader.
    //Only the DataMatch class needs to have a setCurrentLine method because it will be the only one that needs to read it. 

    //Method to check if the information matches the current line at x index.
    public boolean isDataInFile(String input, int index, String fileName) { 
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            //Will check through reader for the passed text which may be username until it does not have any line left.     
            while((line = reader.readLine()) != null) {
                setCurrentLine(line);
                //Returns the equality of the two tokens at the index value.
                //For the purpose of this current method the index will be taken in otherwise it should be a string like username password etc.
                if(input.contains(",")) {
                    if(this.currentLine[index].equalsIgnoreCase((input.split(",")[index]))) return true;//The event id)
                }
                else if(index == 4) {                    
                    //Allow the saved file line to compare against the lowercase 
                    if(currentLine[index].toLowerCase().contains(input.toLowerCase())) return true;
                }
                else if(index == 0) {
                    if(currentLine[index].equalsIgnoreCase(input)) return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }//end of isDataInFile method

    //Method to read each line from the txt file for comparison of data of username and password, returns a member.
    public Member findDataInFile(String usernameInput, String passwordInput) {
        try(BufferedReader reader = new BufferedReader(new FileReader("MemberList.txt"))) {
            String line;                
            System.out.println("Now comparing the information");
            //The file will be extracted line by line and broken into tokens to be compared with the username and password. 
            //The member will be returned that can probably be used for a logged in user.
            while((line = reader.readLine()) != null) {
                setCurrentLine(line);
                if(isMatchingUsernameAndPassword(usernameInput, passwordInput)) {
                    System.out.println("Login Successful!");
                    printCurrentLine();
                    Member loggedInUser = makeTheMember();
                    return loggedInUser;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return new Member(); //return a new null member if no match is found in the file.
    }

    //Overload Method to read each line from the txt file for comparison of data of memberID, returns a full name.
    //This will be implemented by the checkIn class to make a list of everyone attending an event listed by name.
    public String findDataInFile(Integer memberID) {
        try(BufferedReader reader = new BufferedReader(new FileReader("MemberList.txt"))) {
            String line;                
            System.out.println("Now finding member information with ID: " + memberID);
            //The file will be extracted line by line and broken into tokens to be compared with the username and password. 
            //The member will be returned that can probably be used for a logged in user.
            while((line = reader.readLine()) != null) {
                setCurrentLine(line);
                if(isMatchingMemberID(memberID)) {
                    System.out.println("Member information found ID: " + memberID);
                    printCurrentLine();
                    String memberNameString = idToMemberName();
                    return memberNameString;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null; //return a new null member if no match is found in the file which is impossible so it is just for java not to freak out.
    }

    //Method to find data but for events and return the event.
    public Event findEventByID(ArrayList<Event> events, String userInput) {
        for(Event event : events) {
            if(event.getId() == (Integer.parseInt(userInput))) {
                return event;
            }
        }
        return null;
    }

    //Find check in value with event id.
    public String findUserToCheckIn(ArrayList<Event> events, String userInput) {
        //Will use method to check for the event then send the member to check in the txt file.
        return "";
    }
    //Read out each value for current line
    public void printCurrentLine() {
        System.out.println("Member id: " + currentLine[0] + 
                            "\nFirst Name: " + currentLine[1] + 
                            "\nLast: " + currentLine[2] + 
                            "\nUsername: " + currentLine[3] + 
                            "\nPassword:: " + currentLine[4] + 
                        "\nDOB: " + currentLine[5]);
    }
    //Method to make the member object if username and password is matching
    public Member makeTheMember() {
        return new Member(this.currentLine[0], this.currentLine[1], this.currentLine[2], this.currentLine[3], 
                this.currentLine[4], this.currentLine[5]);
    } 

    private void setCurrentLine(String currentLine) {
        this.currentLine = currentLine.split(",");
        /* index 1 is member id, index 2 is first name, index 3 is last name
         * index 4 is username, index 5 is password, index 6 is date of birth
        */
    }

    //Method to check if the current username matches the one in the file.
    private boolean isMatchingUsernameAndPassword(String thatUser, String thatPassword) {
        return this.currentLine[3].equalsIgnoreCase(thatUser) && this.currentLine[4].equalsIgnoreCase(thatPassword);
    }

    //Method to check if the current member ID matches the one in the file.
    private boolean isMatchingMemberID(Integer thatID) {
        //The id is a String type in the file, so it needs to be converted to integer before comparing.
        return Integer.parseInt(this.currentLine[0]) == thatID;
    }

    //Method for checkIn to get a member id passed while returning a name for the id number. return first and last name.
    private String idToMemberName() {
        return this.currentLine[1] + " " + this.currentLine[2];
    }
}
