package yuuki.ui;
import yuuki.gui.UI.*;
import javax.swing.JFrame;
import yuuki.action.Action;
import yuuki.buff.Buff;
import yuuki.entity.Stat;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Caleb
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
        if(prompt.equals("Enter player name"))
        {
            currentForm.setVisible(false);
        currentForm = optionsMenuGui;
        playerNameGui.setVisible(true);
        }
        else
        {
            
        }
    }
    public String getString()
    {
        
    }
}