package yuuki.ui;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import yuuki.gui.UI.*;
import javax.swing.JFrame;
import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import yuuki.action.Action;
import yuuki.buff.Buff;
import yuuki.entity.Character;
import yuuki.entity.Stat;

/**
 *
 * @author Caleb Smith
 * @version 10/13/12
 */
public class GraphicalEngine implements Interactable {
    //Construct nessecary objects.
    private JFrame currentForm = null;
    public MainTitle mainTitleGui = new MainTitle();
    public OptionsMenu optionsMenuGui = new OptionsMenu();
    public PlayerName playerNameGui = new PlayerName();
    public BattleScreen battleScreen = new BattleScreen();
    
    public void initialize()
    {
        
    }
    public void destroy()
    {
        
    }
    
    public void switchToSpecifiedScreen(JFrame showingForm)
    {
        currentForm.setVisible(false);
        currentForm = showingForm;
        currentForm.setVisible(true);
    }
    
    public void switchToPlayerNameScreen()
    {
        currentForm.setVisible(false);
        currentForm = playerNameGui;
        playerNameGui.setVisible(true);
    }
    
    public void switchToIntroScreen()
    {
        currentForm.setVisible(false);
        currentForm = mainTitleGui;
        mainTitleGui.setVisible(true);
    }
    
    public void switchToOptionsScreen()
    {
        currentForm.setVisible(false);
        currentForm = optionsMenuGui;
        optionsMenuGui.setVisible(true);        
    }
    public void switchToBattleScreen(yuuki.entity.Character[][] fighters)
    {
        currentForm.setVisible(false);
        currentForm = battleScreen;
        battleScreen.setVisible(true);
    }
    public void showStatUpdate(yuuki.entity.Character fighter)
    {
        
    }
    public void showDamage(yuuki.entity.Character fighter, Stat stat, int damage)
    {
        
    }
    public void showDamage(yuuki.entity.Character fighter, Stat stat, double damage)
    {
        
    }
    public void showRecovery(yuuki.entity.Character fighter, Stat stat, double amount)
    {
        
    }
    public void showRecovery(yuuki.entity.Character fighter, Stat stat, int amount)
    {
        
    }
    public void showActionPreperation(Action action)
    {
        
    }
    public void showActionUse(Action action)
    {
        
    }
    public void showActionFailure(Action action)
    {
        
    }
    public void showBuffActivation(Buff buff)
    {
        
    }
    public void showBuffApplication(Buff buff)
    {
        
    }
    public void showBuffDeactivation(Buff buff)
    {
        
    }
    public void showCharacterRemoval(yuuki.entity.Character fighter)
    {
        
    }
    public void showCharacterVictory(yuuki.entity.Character[] fighters)
    {
        
    }
    public void switchToOverworldScreen()
    {
        
    }
    public void switchToPauseScreen()
    {
        
    }
    public void switchToEndingScreen()
    {
        
    }
    /**
     * Is used to return a string from the user.
     * - Can check to see if required string is the Player Name or Not.
     * - Returns the desired String.
     * 
     * @param prompt
     * @return userName
     */
    public String getString(String prompt)
    {
        String string = "";
        //"Enter player name" hardcoded. sounce @ YuukiEngine - createPlayer(int)
        if(prompt.equals("Enter player name"))
        {
            JFrame showingForm = null;
            showingForm = currentForm;
            String userName = "";
            switchToPlayerNameScreen();
            boolean playGame = playerNameGui.getPlayStatus();
            /**
             * While the Start Game button (lblBtnBeginGame) has not been clicked, wait.
             * Once Start Game button (lblBtnBeginGame) has been clicked:
             * - get the Player Name the user Entered
             * - return the Player Name
             */
            while(!playGame)
            {
                playGame = playerNameGui.getPlayStatus();
                try
                {
                    Thread.sleep(10);
                }
                catch(Exception e)
                {
                    System.out.println("GraphicalEngine.getString(String) couldn't sleep.");
                }
            }
            userName = playerNameGui.getUsersName();
            switchToSpecifiedScreen(showingForm);
            return userName;
            
        }
       
      return string;
    }
    public String getString()
    {
        String string = "";
        return string;
    }
    public int getInt(String prompt)
    {
        int numb = 0;
        return numb;
    }
    public int getInt()
    {
        int numb = 0;
        return numb;
    }
    public int getInt(String prompt, int min, int max)
    {
        int numb = 0;
        return numb;
    }
    public int getInt(int min, int max)
    {
        int numb = 0;
        return numb;
    }
    public double getDouble(String prompt)
    {
        double dubs = 0;
        return dubs;
    }
    public double getDouble()
    {
        double dubs = 0;
        return dubs;
    }
    public double getDouble(String prompt, double min, double max)
    {
        double dubs = 0;
        return dubs;
    }
    public double getDouble(double min, double max)
    {
        double dubs = 0;
        return dubs;
    }
    public int getChoice(String prompt, String[] options)
    {
        int choiceP = 0;
        return choiceP;
    }
    public int getChoice(String[] options)
    {
        int choice = 0;
        return choice;
    }
    public boolean confirm(String prompt, String yes, String no)
    {
        boolean confirm = false;
        return confirm;
    }
    public int selectAction(Action[] moves)
    {
        int action = 0;
        return action;
    }
    public yuuki.entity.Character selectTarget(ArrayList<ArrayList<yuuki.entity.Character>> fighters)
    {
        yuuki.entity.Character target = fighters.get(0).get(0);
        return target;
    }
    
    public void playSound(String path)
    {
        AudioPlayer BGMPlayer = AudioPlayer.player;
            AudioStream BGM;
            AudioData BGMData;
            try
            {
            BGM = new AudioStream(new FileInputStream(path));
            BGMData = BGM.getData();
            AudioDataStream loop = null;
            loop = new AudioDataStream(BGMData);
            BGMPlayer.start(loop);
            }
            catch(IOException error)
            {
                System.out.println("Audio play New Game Entered went wrong.");
            }
    }
    public void display(yuuki.entity.Character speaker, String message)
    {
        
    }
}