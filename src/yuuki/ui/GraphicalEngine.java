package yuuki.ui;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import yuuki.gui.UI.*;
import yuuki.gui.UI.BattleScreen;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import yuuki.action.Action;
import yuuki.buff.Buff;
import yuuki.entity.Character;
import yuuki.entity.Stat;
import yuuki.entity.VariableStat;

/**
 *
 * @author Caleb Smith
 * @version 10/16/12
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
    public void switchToSpecifiedScreen(JFrame specifiedForm)
    {
        if(currentForm == null)
        {
        currentForm = specifiedForm;
        currentForm.setVisible(true);
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = specifiedForm;
            currentForm.setVisible(true);
        }
    }
    public void switchToPlayerNameScreen()
    {
        if(currentForm == null)
        {
            currentForm = playerNameGui;
            currentForm.setVisible(true);
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = playerNameGui;
            currentForm.setVisible(true);
        }
    }
    public void switchToPlayerNameScreen(boolean soundEffects, boolean soundMusic)
    {
        if(currentForm == null)
        {
            currentForm = playerNameGui;
            currentForm.setVisible(true);
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = playerNameGui;
            currentForm.setVisible(true);
        }        
    }
    public void switchToIntroScreen()
    {
        if(currentForm == null)
        {
            currentForm = mainTitleGui;
            currentForm.setVisible(true);
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = mainTitleGui;
            currentForm.setVisible(true);
        }
    }
    public void switchToIntroScreen(boolean soundEffects, boolean soundMusic)
    {
       if(currentForm == null)
        {
            currentForm = mainTitleGui;
            currentForm.setVisible(true);
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = mainTitleGui;
            currentForm.setVisible(true);
        }
    }
    public void switchToOptionsScreen()
    {
        if(currentForm == null)
        {
            currentForm = optionsMenuGui;
            currentForm.setVisible(true);
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = optionsMenuGui;
            currentForm.setVisible(true);
        }
    }
    public void switchToOptionsScreen(boolean soundEffects, boolean soundMusic)
    {
        if(currentForm == null)
        {
            currentForm = optionsMenuGui;
            currentForm.setVisible(true);
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = optionsMenuGui;
            currentForm.setVisible(true);
        }
    }
    public void switchToBattleScreen(yuuki.entity.Character[][] fighters)
    {
        if(currentForm == null)
        {
            currentForm = battleScreen;
            currentForm.setVisible(true);
            battleScreen.resetMenu();
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = battleScreen;
            currentForm.setVisible(true);
            battleScreen.resetMenu();
        }
    }
    public void showStatUpdate(yuuki.entity.Character fighter)
    {
                showStats(fighter);
    }
    private void showStats(Character f) {
		print(f.getName() + " (");
		print("Team " + f.getTeamId());
		println(", Fighter " + f.getFighterId() + ")");
		println("---------------");
		VariableStat hp = f.getHPStat();
		VariableStat mp = f.getMPStat();
		Stat str = f.getStrengthStat();
		Stat def = f.getDefenseStat();
		Stat agt = f.getAgilityStat();
		Stat acc = f.getAccuracyStat();
		Stat mag = f.getMagicStat();
		Stat luk = f.getLuckStat();
		println("HP: " + hp.getCurrent() + "/" + hp.getMax(f.getLevel()));
		println("MP: " + mp.getCurrent() + "/" + mp.getMax(f.getLevel()));
                updateGuiHP(hp.getCurrent(), hp.getMax(f.getLevel()), f.getTeamId());
		println("---------------");
		println("STR: " + str.getEffective(f.getLevel()));
		println("DEF: " + def.getEffective(f.getLevel()));
		println("AGT: " + agt.getEffective(f.getLevel()));
		println("ACC: " + acc.getEffective(f.getLevel()));
		println("MAG: " + mag.getEffective(f.getLevel()));
		println("LUK: " + luk.getEffective(f.getLevel()));
		println("");
		if (f.getBuffs().size() > 0) {
			showBuffs(f);
		}
		pause();
	}
    public void updateGuiHP(int hp, int hpMax, int teamID)
    {
        if(teamID == 0)
        {
            battleScreen.setPlayerHP(hp, hpMax);
        }
        else
        {
            battleScreen.setMonsterHP(hp, hpMax);
        }
    }
    public void print(String arg)
    {
        battleScreen.setText(arg);
    }
    public void println(String arg)
    {
        battleScreen.setTextln(arg);
    }
    private void showBuffs(Character f) {
		ArrayList<Buff> buffs = f.getBuffs();
		print("Buffs: ");
		for (int i = 0; i < buffs.size(); i++) {
			print(buffs.get(i).getName());
			if (i + 1 < buffs.size()) {
				print(", ");
			}
		}
		println("");
	}
    private void pause() {
		println("pause");
		
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
        String[] moveNames = new String[moves.length];
		for (int i = 0; i < moves.length; i++) {
			moveNames[i] = moves[i].getName();
		}
        String move = (String) JOptionPane.showInputDialog(null, "Choose a move:", "test", JOptionPane.INFORMATION_MESSAGE, null, moveNames, moveNames[0]);
        int index = 0;
        for (int i = 0; i < moveNames.length; i++) {
            if (moveNames[i].equals(move)) {
                index = i;
                break;
            }
        }
        return index;
    }
    public yuuki.entity.Character selectTarget(ArrayList<ArrayList<yuuki.entity.Character>> fighters)
    {
		ArrayList<Character> chars = new ArrayList<Character>();
		for (int i = 0; i < fighters.size(); i++) {
			ArrayList<Character> team = fighters.get(i);
			for (int j = 0; j < team.size(); j++) {
				Character c = team.get(j);
				chars.add(c);
			}
		}
		Character[] charsArr = chars.toArray(new Character[0]);
                
		return (Character) JOptionPane.showInputDialog(null, "Select target", "test", JOptionPane.INFORMATION_MESSAGE, null, charsArr, charsArr[0]);
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