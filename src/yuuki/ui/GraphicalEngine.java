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
import yuuki.entity.Stat;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Caleb Smith
 * @version 10/11/12
 */
public class GraphicalEngine implements Interactable {
    private JFrame currentForm = null;
    private MainTitle mainTitleGui = new MainTitle();
    private OptionsMenu optionsMenuGui = new OptionsMenu();
    private PlayerName playerNameGui = new PlayerName();
    
    public void initialize()
    {
        
    }
    public void destroy()
    {
        
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
    public String getString(String prompt)
    {
        String string = "";
      if(prompt.equals("Enter player name"));
      {
          string = "test";
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