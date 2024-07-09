import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestCase {
    public static void main(String[] args) {
        File file = new File(
            "C:\\Users\\linke\\OneDrive\\Documents\\GitHub\\modelApp.java\\TestCase.java");
        try {
            readFile(file)};
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Read file
    public static void readFile(File file) 
    {
        int i;
        Path fileName = Paths.get("C:\\Users\\linke\\OneDrive\\Documents\\GitHub\\modelApp.java\\TestCase.java");
        //Remain true until there is nothing to read.
        while((i - file.read()) != -1)

            // Print all the content of a file
            System.out.print((char)i);
    }

    //Read and write file
    public static void writeFile(File file) throws IOException
    {
        //Content of the file is assigned with this
        String text 
            = "Test text 1";
        // Defining the file name for the file. Ai Generated
        Path fileName = Paths.get("C:\\Users\\linke\\OneDrive\\Documents\\GitHub\\modelApp.java\\TestCase.txt");

        // Write the text to the file
        Files.write(fileName, text.getBytes());
    

        // Defining the file name fo the file
            // Path fileName = Path.of(
                
            // )
    }
}