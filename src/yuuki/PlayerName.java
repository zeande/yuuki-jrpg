//Imports.
import java.util.Scanner;
/**
 *Class PlayerName contains a method that prompts the user to enter a name
 * for their (player style) character.
 * @author Caleb
 */
public class PlayerName {
    
    public String playerName()
    {
        //Declare Variables.
        String name = "Player";
        //Instantiates an instance of Scanner.
        Scanner scan = new Scanner(System.in);
        
        //Prompt user for a character name.
        System.out.println("Please enter a name for your charater:");
        name = scan.nextLine();
        return name;
    }
}
