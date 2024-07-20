package validation.pack;

public class Valid {
    //The minimum and maximum length for username. The special char requirement will be checked.
    //First and last name will be used to check the password for security check.
    private int minLength;
    private int maxLength;
    private String firstName;
    private String lastName;
    private boolean allowSpecialChar;

    //Constructor to set the username length, the maximum length, the first and last name, and the special character requirement.
    public Valid(int minLength, int maxLength, boolean charChoice, String first, String last) {
        firstName = first;
        lastName = last;
        this.minLength = minLength;
        this.maxLength = maxLength;
        allowSpecialChar = charChoice;
    }

    //method to determine if a string meets the required length.
    private boolean checkLength(String input) {
        //The body will check the input of the text against the length that is preset.
        if(input.length() > minLength && input.length() <= maxLength) {
            return true;
        }
        errMessage("username", "The length needs to be from " 
                    + minLength + " to " + maxLength + ".");
        return false;
    }

    //Method to tell the user that the ___ is incorrect.
    private void errMessage(String problemItem, String requirement) {
        System.out.println("There is a problem with the " + problemItem + ".\n" + requirement);
    }

    //This method will determine if a input has a char
    private boolean hasChar(String input) {
        //The body will check if the text has a character for the code
        if(allowSpecialChar) {
            //!, ", #, $, %, &, ', (, ), *, +
            for(int i = 33; i < 44; i++){
                if(input.indexOf(((char) i)) > -1) {
                    return true;
                }
            }
        } else {
            return true;
        }
        errMessage("username", "There needs to be one character.");
        return false;
    }

    //This method will be used by the main method to test if a username is valid and following the protocol.
    public boolean isValidUser(String input) {
        //Call the other two methods to check.
        //if a string is valid then it will meet all the requirements of the constructor.
        if(hasChar(input) && checkLength(input)) {
            return true;
        }
        return false;
    }

    //This method will be used by the main method to test if a password is valid and following the protocol.
    public boolean isValidPass(String input) {
        if(nameCheck(input) && isValidUser(input) ) {
            return true;
        }
        System.out.println("Please enter a password that has a character, is between ascii 33 to 43 and has length of 9 to 20.");
        return false;
    }

    private boolean nameCheck(String input) {
        if(input.contains(firstName) || input.contains(lastName)) {
            System.out.println("Please do not include name in username");
            return false;
        }
        return true;
    }
}


