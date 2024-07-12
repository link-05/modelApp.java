import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.String;

public class Member {
    //A member will have a username, password, and id attached to them
    private String username;
    private String password;
    private static double members = 0.0;
    private String dateOfBirth;
    private String id;
    
    public Member(String user, String pass, String dob) {
        username = user;
        password = pass;
        members++;
        id = "" + members;
        dateOfBirth = dob;
    }

    public Member() {
        username = "";
        password = "";
        id = "";
    }

    //Method to check if Username match. Used for comparing to input
    public Boolean compareUsername(String thatUser) {
        return this.username.equals(thatUser);
    }

    //Method to check if Password match. Used for comparing to input
    public Boolean comparePassword(String thatPass) {
        return this.password.equals(thatPass);
    }

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setUsername(String newUsername) {
        username = newUsername;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    // For use to maintain accurate count of member when reading file then writing a new member into file.
    public void updateMemberCount(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("MemberList.txt"));
            String test = reader.readLine();
            while(test != null) {
                members++;
                test = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}