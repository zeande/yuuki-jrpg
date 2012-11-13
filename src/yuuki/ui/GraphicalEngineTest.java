/**
 * A Simple Gui test.
 * Provides Gui Navigation.
 * Does Not Provide full Gameplay.
 */
package yuuki.ui;

/**
 *
 * @author Caleb Smtih
 * @version 11/13/12
 */
public class GraphicalEngineTest {
    //Initiate GraphicalEngine.
    GraphicalEngine ge = new GraphicalEngine();
    //Main Method.
    public static void main(String[] args) {
        GraphicalEngineTest get = new GraphicalEngineTest();
        get.run();
    }
    /**
     * Displays the Intro Screen
     * Waits until user navigates to another menu.
     */
    public void run()
    {
        ge.switchToIntroScreen();
        boolean blnNewGame = false;
        boolean blnOptionsMenu = false;
        blnNewGame = ge.mainTitleGui.getNewGame();
        blnOptionsMenu = ge.mainTitleGui.getOptionsMenu();
        
        while(!blnNewGame && !blnOptionsMenu)
        {
            blnNewGame = ge.mainTitleGui.getNewGame();
            blnOptionsMenu = ge.mainTitleGui.getOptionsMenu();
            try
                {
                    Thread.sleep(10);
                }
            catch(Exception e)
                {
                    System.out.println("GraphicalEngineTest.run couldn't sleep.");
                }
        }
        
        if(blnNewGame == true)
            {
                ge.switchToPlayerNameScreen();
                newGame();
            }
            else if(blnOptionsMenu == true)
            {
                ge.switchToOptionsScreen();
            }
    }
    
    public void newGame()
    {
        boolean blnStartGame = false;
        boolean blnToMenu = false;
        blnStartGame = ge.playerNameGui.getPlayStatus();
        blnToMenu = ge.playerNameGui.getMenuStatus();
        
        while(!blnStartGame && !blnToMenu)
        {
            blnStartGame = ge.playerNameGui.getPlayStatus();
            blnToMenu = ge.playerNameGui.getMenuStatus();
            try
                {
                    Thread.sleep(10);
                }
            catch(Exception e)
                {
                    System.out.println("GraphicalEngineTest.newGame couldn't sleep.");
                }
        }
        
        if(blnStartGame = true)
        {
            String userName = ge.getString("Enter player name");
            System.out.println("The User's Name is:");
            System.out.println(userName);
            System.out.println("<Hardcoded Responce @ GraphicalEngineTest.newGame() - No JFrame For Game Start.");
        }
        else if(blnToMenu = true)
        {
            run();
        }
    }
   
    
}
