import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.Scanner;
//To be extracting member information from txt file to convert into members.

public class TestCase {
    public static void main(String[] args) {
        boolean isStillCreatingUser = true;
        Member placeholder = new Member();
        Scanner in = new Scanner(System.in);
        placeholder.updateMemberCount();
        while(isStillCreatingUser) {
            try {
                saveMember(signUpNewMember(), true);
                readFile();
                System.out.println("Do you wish to register another user? (Y/N)");
                String decision = in.nextLine();
                if(in.hasNextLine() && decision.equalsIgnoreCase("n")) {
                    isStillCreatingUser = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            in.close();

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
        
    }//end of read file method


    //Write file
    public static void saveMember(Member newMember, boolean append) {
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
        Scanner input = new Scanner(System.in); 
        boolean isRep = true;
        String thatUser = "";
        while(isRep) { //is repetitive is always true but flips to false when entered username does not already exist in file.
            System.out.println("Please enter in your desired username: ");
            thatUser = input.nextLine();
            if(!checkIfExistingInFile(thatUser)) {
                isRep = false;
            }else {
                System.out.println("Username already exist please use a different one");
            }
        }//end of username check 
        System.out.println("Please enter in your desired password: ");
        String pass = input.nextLine();
        System.out.println("Please enter in your date of birth (mm/dd/yyyy): ");
        String dob = input.nextLine();
        //end of information gathering
        Member x  = new Member(thatUser, pass, dob);
        //Member object is created and printed out to confirm.
        System.out.print("Your membership is made successfully. Your username is " + x.getUsername() + 
            ".\nYour password is " + x.getPassword() + ". \n Your member id is " + x.getId() + ".\n");
        input.close();
        return x;
    }//end of sign up member class

    //This class will verify that the passed username is not matching any in the file.
    public static boolean checkIfExistingInFile(String passedText) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("MemberList.txt"));
            String line;
            while((line = reader.readLine()) != null) {
                if(line.contains(passedText)) return true;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}