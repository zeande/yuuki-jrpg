/**
 * A Simple Gui test.
 * Provides Gui Navigation.
 * Does Not Provide full Gameplay.
 */
package yuuki.ui;

/**
 *
 * @author Caleb Smtih
 * @version 11/16/12
 */
public class GraphicalEngineTest {
    //Initiate GraphicalEngine.
    GraphicalEngine ge = new GraphicalEngine();
        static boolean blnSoundMusic = true;
        static boolean blnSoundEffects = true;
        static String playerName = "";
        static boolean mainTitleStatus = false;
        static boolean optionsMenuStatus = false;
        static boolean playerCreationStatus = false;
        static boolean battleScreenStatus = false;
        static boolean beginGameStatus = false;
        static boolean exitStatus = false;
        
    //Main Method.
    public static void main(String[] args) {
        GraphicalEngineTest get = new GraphicalEngineTest();
        get.introScreen();
        mainTitleStatus = true;
    }
    public void introScreen()
    {
        ge.switchToIntroScreen();
        do
        {
            playerCreationStatus = ge.mainTitleGui.getNewGame();
            optionsMenuStatus = ge.mainTitleGui.getOptionsMenu();
            exitStatus = ge.mainTitleGui.getExit();
            try
            {
                Thread.sleep(1);
            }
            catch(Exception e)
            {
                System.out.println("Couldn't Sleep.");
            }
        }while(playerCreationStatus == false && optionsMenuStatus == false && exitStatus == false);
        mainTitleStatus = false;
        if(playerCreationStatus == true)
        {
            ge.mainTitleGui.setNewGame(false);
            playerCreation();
        }
        else if(optionsMenuStatus == true)
        {
            ge.mainTitleGui.setOptionsMenu(false);
            optionsMenu();
        }
        else if(exitStatus == true)
        {
            exitStatus = false;
            exit();
        }
    }
    public void playerCreation()
    {
        ge.switchToPlayerNameScreen();
        do
        {
            mainTitleStatus = ge.playerNameGui.getMenuStatus();
            beginGameStatus = ge.playerNameGui.getPlayStatus();
            try
            {
                Thread.sleep(1);
            }
            catch(Exception e)
            {
                System.out.println("Couldn't Sleep.");
            }
        }while(mainTitleStatus == false && beginGameStatus == false);
        playerCreationStatus = false;
        if(mainTitleStatus == true)
        {
            ge.playerNameGui.setMenuStatus(false);
            introScreen();
        }
        else if(beginGameStatus == true)
        {
            ge.playerNameGui.setPlayStatus(false);
            playerName = ge.playerNameGui.getUsersName();
            System.out.println("The Player's Name is:" + playerName);
            System.out.println("<Hardcoded Responce> @ GraphicalEngineTest.playerCreation.");
            System.out.println("Reason: No Start of Game JFrame.");
        }
        
    }
    public void optionsMenu()
    {
        ge.switchToOptionsScreen();
        do
        {
            mainTitleStatus = ge.optionsMenuGui.getBtnApply();
            try
            {
                Thread.sleep(1);
            }
            catch(Exception e)
            {
                System.out.println("Couldn't Sleep.");
            }
        }while(mainTitleStatus == false);
        optionsMenuStatus = false;
        ge.optionsMenuGui.setBtnApply(false);
        ge.switchToIntroScreen();
        introScreen();
    }
    {
       
    } public void exit()
    {
       System.exit(0);
    }
    
}