import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.Scanner;
//To be extracting member information from txt file to convert into members.

public class TestCase {
    public static void main(String[] args) {
        try {
            saveMember(signUpNewMember(), true);;
            readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
           
    }
    

    // Read file
    public static void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("MemberList.txt"));
            String line;
            System.out.println("Now printing the log info");
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    //Write file
    public static void saveMember(Member newMember, boolean append)
    {
        //Try-catch block to let it run
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("MemberList.txt", append));
            // Write the text to the file - can run with while loop
            writer.write(newMember.getUsername() + ", " + newMember.getPassword() + 
                 ", " + newMember.getDateOfBirth() + ", " + newMember.getId() + "\n");
            writer.close(); // Writing will not be made if there is not a close statement.
        } catch(IOException e) {
            e.printStackTrace();
        }

    }//end of writeFile class

    public static Member signUpNewMember() {
        //get all necessary information for creating a member account.
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter in your desired username: ");
        String user = in.nextLine();
        System.out.println("Please enter in your desired password: ");
        String pass = in.nextLine();
        System.out.println("Please enter in your date of birth (mm/dd/yyyy): ");
        String dob = in.nextLine();
        Member x  = new Member(user, pass, dob);
        System.out.print("Your membership is made successfully. Your username is " + x.getUsername() + 
            ".\nYour password is " + x.getPassword() + ". \n Your member id is " + x.getId() + ".\n");
        in.close();
        return x;
    }//end of sign up member class

    //This class will verify that the passed username is not matching any in the file.
    public static boolean checkIfExisting(String anyText) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("MemberList.txt"));
            String line;
            while((line = reader.readLine())!= null) {
                if(line.contains(anyText)) return true;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}