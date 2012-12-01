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
        static boolean bsOptionsStatus = false;
        static boolean bsNewGameStatus = false;
        static boolean bsLoadGameStatus = false;
        static boolean bsSaveGameStatus = false;
        static boolean bsExitStatus = false;
        
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
            optionsMenu("IntroScreen");
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
            battleScreen();
        }
        
    }
    public void optionsMenu(String jFrame)
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
        if(jFrame == "IntroScreen")
        {
        ge.switchToIntroScreen();
        }
        else if(jFrame == "BattleScreen")
        {
            battleScreen();
        }
        introScreen();
    }
    public void battleScreen()
    {
        ge.switchToBattleScreen(fighters);
        do
        {
            bsOptionsStatus = ge.battleScreen.getOptionClicked();
            bsNewGameStatus = ge.battleScreen.getNewGameClicked();
            bsLoadGameStatus = ge.battleScreen.getLoadGameClicked();
            bsSaveGameStatus = ge.battleScreen.getSaveGameClicked();
            bsExitStatus = ge.battleScreen.getExitClicked();
            try
            {
                Thread.sleep(1);
            }
            catch(Exception e)
            {
                System.out.println("Couldn't sleep @ GraphicalEngineTest.battleScreen");
            }
        }while(bsOptionsStatus == false && bsNewGameStatus == false && bsLoadGameStatus == false && bsSaveGameStatus == false && bsExitStatus == false);
        if(bsOptionsStatus == true)
        {
            bsOptionsStatus = false;
            ge.battleScreen.setOptionClicked(false);
            ge.battleScreen.resetMenu();
            optionsMenu("BattleScreen");
        }
        else if(bsNewGameStatus == true)
        {
            bsNewGameStatus = false;
            ge.battleScreen.setNewGameClicked(false);
            ge.battleScreen.resetMenu();
            introScreen();
            playerName = "";
        }
        else if(bsLoadGameStatus == true)
        {
            bsLoadGameStatus = false;
            ge.battleScreen.setLoadGameClicked(false);
            ge.battleScreen.resetMenu();
            System.out.println("<Hardcoded Responce @ GraphicalEngingTest.battleScreen> No Load Game Functionality");
        }
        else if(bsSaveGameStatus == true)
        {
            bsSaveGameStatus = false;
            ge.battleScreen.setSaveGameClicked(false);
            ge.battleScreen.resetMenu();
            System.out.println("<Hardcoded Responce @ GraphicalEngingTest.battleScreen> No Save Game Functionality");
        }
        else if(bsExitStatus == true)
        {
            bsExitStatus = false;
            ge.battleScreen.setExitClicked(false);
            ge.battleScreen.resetMenu();
            exit();
        }
    }
       
     public void exit()
    {
       System.exit(0);
    }
    
}