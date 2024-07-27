
public class dataMatch {
    private String[] currentLine;

    //Constructor to set the current line being processed.
    public dataMatch() {
        this.currentLine = null;
    }

    //Method to set the current line being processed. current line is the line read by the file reader.
    public void setCurrentLine(String currentLine) {
        this.currentLine = currentLine.split(",");
        /* index 1 is member id, index 2 is first name, index 3 is last name
         * index 4 is username, index 5 is password, index 6 is date of birth
        */
    }

    //Method to check if the current username matches the one in the file.
    public boolean isMatchingUsernameAndPassword(String thatUser, String thatPassword) {
        return this.currentLine[3].equalsIgnoreCase(thatUser) && this.currentLine[4].equalsIgnoreCase(thatPassword);
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
}
