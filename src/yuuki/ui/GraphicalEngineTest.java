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
        static boolean blnSoundMusic = true;
        static boolean blnSoundEffects = true;
        
    //Main Method.
    public static void main(String[] args) {
        GraphicalEngineTest get = new GraphicalEngineTest();

        get.run(blnSoundMusic, blnSoundEffects);
    }
    /**
     * Displays the Intro Screen
     * Waits until user navigates to another menu.
     */
    public void run(boolean soundMusic, boolean soundEffects)
    {
        ge.switchToIntroScreen();
        ge.mainTitleGui.soundEffects = soundEffects;
        ge.mainTitleGui.soundMusic = soundMusic;
        boolean blnNewGame = false;
        boolean blnOptionsMenu = false;
        boolean blnExit = false;
        blnNewGame = ge.mainTitleGui.getNewGame();
        blnOptionsMenu = ge.mainTitleGui.getOptionsMenu();

        do
        {
            blnExit = ge.mainTitleGui.getExit();
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
        }while(!blnNewGame && !blnOptionsMenu);
        
        if(blnNewGame == true)
            {
                ge.switchToPlayerNameScreen();
                newGame(blnSoundMusic, blnSoundEffects);
            }
            else if(blnOptionsMenu == true)
            {
                ge.switchToOptionsScreen();
                optionsMenu(blnSoundMusic, blnSoundEffects);
            }
            else if(blnExit = true)
            {
                exit();
            }
    }
    
    public void newGame(boolean soundMusic, boolean soundEffects)
    {
        boolean blnStartGame = false;
        boolean blnToMenu = false;
        blnStartGame = ge.playerNameGui.getPlayStatus();
        blnToMenu = ge.playerNameGui.getMenuStatus();
        
        do
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
        }while(!blnStartGame && !blnToMenu);
        
        if(blnStartGame = true)
        {
            String userName = ge.getString("Enter player name");
            System.out.println("The User's Name is:");
            System.out.println(userName);
            System.out.println("<Hardcoded Responce @ GraphicalEngineTest.newGame() - No JFrame For Game Start.");
        }
        else if(blnToMenu = true)
        {
            run(blnSoundMusic, blnSoundEffects);
        }
    }
   
    public void optionsMenu(boolean soundMusic, boolean soundEffects)
    {
        boolean blnApply = false;
        ge.optionsMenuGui.soundMusic = soundMusic;
        ge.optionsMenuGui.soundEffects = soundEffects;
        do
        {
           blnApply = ge.optionsMenuGui.getBtnApply();
           blnSoundMusic = ge.optionsMenuGui.getSoundMusic();
           blnSoundEffects = ge.optionsMenuGui.getSoundEffects();
           try
                {
                    Thread.sleep(10);
                }
            catch(Exception e)
                {
                    System.out.println("GraphicalEngineTest.optionsMenu couldn't sleep.");
                }
        }while(!blnApply);
        run(blnSoundMusic, blnSoundEffects);
    }
    
    public void exit()
    {
        System.exit(0);
    }
}
