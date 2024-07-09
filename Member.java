public class Member {
    //A member will have a username, password, and id attached to them
    private String username;
    private String password;
    private static double members = 0.0;
    private String dateOfBirth;
    private String id;
    
    public Member(String user, String pass, int day, int month, int year) {
        username = user;
        password = pass;
        members++;
        id = "" + members;
        dateOfBirth = "" + month + day + year;
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

    public String getId() {
        return id;
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

}