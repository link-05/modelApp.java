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