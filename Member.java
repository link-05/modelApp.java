import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.String;

public class Member {
    //A member will have a username, password, and id attached to them
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private static double members = 0.0;
    private String dateOfBirth;
    private String id;
    
    public Member(String first, String last, String user, String pass, String dob) {
        username = user;
        password = pass;
        members++;
        id = "" + members;
        dateOfBirth = dob;
        this.firstName = first;
        this.lastName = last;
    }

    //For the data match to return data with  
    public Member(String id, String first, String last, String user, String pass, String dob) {
        username = user;
        password = pass;
        this.id=id;
        dateOfBirth = dob;
        this.firstName = first;
        this.lastName = last;
    }

    public Member() {
        username = "null";
        password = "";
        id = "";
        firstName = "";
        lastName = "";
    }

    //Getter methods for each instance variable

    //getUsername, getId, getFirstName, getLastName, and getPassword are used by TestCase.java to use the member object to write to the file in sign up user.
    public String getUsername (){
        return username;
    }

    public String getPassword (){
        return password;
    }

    public int getId() {
        return Integer.parseInt(id.substring(0, id.indexOf(".")));
        // return Integer.parseInt(id.substring(0, id.indexOf(".")));
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    // For use to maintain accurate count of member when reading file before writing a new member into file.
    public void updateMemberCount(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("MemberList.txt"));
            String test = reader.readLine();
            while(test != null) {
                members++;
                test = reader.readLine();
            }
            //remove the count from the first line with the guide
            members--;
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}