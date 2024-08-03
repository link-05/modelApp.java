import java.io.BufferedReader;
import java.io.FileReader;

public class DataMatch {
    private String[] currentLine;

    //Constructor to set the current line being processed.
    public DataMatch() {
        this.currentLine = null;
    }
    //First Method to call in order to set the current line being processed. Current line is the line read by the file reader.
    public void setCurrentLine(String currentLine) {
        this.currentLine = currentLine.split(",");
        /* index 1 is member id, index 2 is first name, index 3 is last name
         * index 4 is username, index 5 is password, index 6 is date of birth
        */
    }

    //Method to check if the information matches the current line at x index.
    public boolean isDataInFile(String input, int index) { 
        setCurrentLine(input);
        return this.currentLine[index].equalsIgnoreCase(input.substring(0, 1));//The event id)
    }

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

    //Overload Method to read each line from the txt file for comparison of data of memberID, returns a member.
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

    //Method to check if the current username matches the one in the file.
    private boolean isMatchingUsernameAndPassword(String thatUser, String thatPassword) {
        return this.currentLine[3].equalsIgnoreCase(thatUser) && this.currentLine[4].equalsIgnoreCase(thatPassword);
    }

    //Method to check if the current member ID matches the one in the file.
    private boolean isMatchingMemberID(Integer thatID) {
        //The id is a String type in the file, so it needs to be converted to integer before comparing.
        return Integer.parseInt(this.currentLine[0]) == thatID;
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

    //Method for checkIn to get a member id passed while returning a name for the id number. return first and last name.
    private String idToMemberName() {
        return this.currentLine[1] + " " + this.currentLine[2];
    }
}
