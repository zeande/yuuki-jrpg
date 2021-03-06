package yuuki.ui;

import java.util.ArrayList;
import yuuki.gui.UI.*;
import yuuki.gui.UI.BattleScreen;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import yuuki.action.Action;
import yuuki.buff.Buff;
import yuuki.entity.Character;
import yuuki.entity.Stat;
import yuuki.entity.VariableStat;

/**
 * Instantiates all JFrames.
 * provides handling for GUI Navigation.
 * Implements Interactable.
 * @author Caleb Smith
 * @version 12/06/12
 */
public class GraphicalEngine implements Interactable {
    //Construct nessecary objects.
    MainTitle mainTitle = new MainTitle();
    OptionsMenu optionsMenu = new OptionsMenu();
    PlayerName playerName = new PlayerName();
    BattleScreen battleScreenGui = new BattleScreen();
    String NameOfPlayer = "";
    public String nextMove = "";
    private JFrame currentForm = null;
    
    public void setNextMove(String input)
    {
        nextMove = input;
    }
//    public String waitUser()
//    {
//        
//        
//        
//    }
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
    /**
     * Switches to the PlayerName JFrame.
     * 
     * @return nextForm
     */
    public String switchToPlayerNameScreen()
    {
        String nextForm = "";
        if(currentForm == null)
        {
            currentForm = playerName;
            currentForm.setVisible(true);
            nextForm = playerName.getNextForm("next form querry");
            NameOfPlayer = playerName.getName();
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = playerName;
            currentForm.setVisible(true);
            nextForm = playerName.getNextForm("next form querry");
            NameOfPlayer = playerName.getName();
        }
        return nextForm;
    }
    /**
     * Switches to the PlayerName JFrame.
     * Provides PlayerName with current audio options.
     * 
     * @param soundMusic
     * @param soundEffects
     * @return nextForm
     */
    public String switchToPlayerNameScreen(boolean soundMusic , boolean soundEffects)
    {
        String nextForm = "";
        if(currentForm == null)
        {
            currentForm = playerName;
            playerName.setMusic(soundMusic);
            playerName.setEffects(soundEffects);
            currentForm.setVisible(true);
            nextForm = playerName.getNextForm("next form querry");
            NameOfPlayer = playerName.getName();
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = playerName;
            playerName.setMusic(soundMusic);
            playerName.setEffects(soundEffects);
            currentForm.setVisible(true);
            nextForm = playerName.getNextForm("next form querry");
            NameOfPlayer = playerName.getName();
        }    
        return nextForm;
    }
    /**
     * Switches the the MainTitle JFrame.
     * 
     * @return nextForm
     */
    public String switchToIntroScreen()
    {
        String nextForm = "";
        if(currentForm == null)
        {
            currentForm = mainTitle;
            currentForm.setVisible(true);
            nextForm = mainTitle.getNextForm("next form querry");
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = mainTitle;
            currentForm.setVisible(true);
            nextForm = mainTitle.getNextForm("next form querry");
        }
        return nextForm;
    }
    /**
     * Switches to the MainTitle JFrame.
     * Provides it with current audio options.
     * 
     * @param soundMusic
     * @param soundEffects
     * @return nextForm
     */
    public String switchToIntroScreen(boolean soundMusic, boolean soundEffects)
    {
        String nextForm = "";
       if(currentForm == null)
        {
            currentForm = mainTitle;
            mainTitle.setMusic(soundMusic);
            mainTitle.setEffects(soundEffects);
            currentForm.setVisible(true);
            nextForm = mainTitle.getNextForm("next form querry");
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = mainTitle;
            mainTitle.setMusic(soundMusic);
            mainTitle.setEffects(soundEffects);
            currentForm.setVisible(true);
            nextForm = mainTitle.getNextForm("next form querry");
        }
       return nextForm;
    }
    /**
     * Switches to OptionsMenu JFrame.
     * 
     * @return nextForm
     */
    public String switchToOptionsScreen()
    {
        String nextForm = "";
        if(currentForm == null)
        {
            currentForm = optionsMenu;
            currentForm.setVisible(true);
            nextForm = optionsMenu.getNextForm("next form querry");
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = optionsMenu;
            currentForm.setVisible(true);
            nextForm = optionsMenu.getNextForm("next form querry");
        }
        return nextForm;
    }
    /**
     * Switch to OptionsScreen JFrame.
     * Provide it with current audio options.
     * 
     * @param soundMusic
     * @param soundEffects
     * @return nextForm
     */
    public String switchToOptionsScreen(boolean soundMusic, boolean soundEffects)
    {
        String nextForm = "";
        if(currentForm == null)
        {
            currentForm = optionsMenu;
            optionsMenu.setMusic();
            currentForm.setVisible(true);
            nextForm = optionsMenu.getNextForm("next form querry");
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = optionsMenu;
            currentForm.setVisible(true);
            nextForm = optionsMenu.getNextForm("next form querry");
        }
        return nextForm;
    }
    /**
     * Switch to BattleScreen JFrame.
     * Provide it with current fighters Array.
     * Provide it with current audio options.
     * 
     * @param fighters
     * @param music
     * @param effects 
     */
    public void switchToBattleScreen(yuuki.entity.Character[][] fighters, boolean music, boolean effects)
    {
        if(currentForm == null)
        {
            currentForm = battleScreenGui;
            currentForm.setVisible(true);
            battleScreenGui.setMusic(music);
            battleScreenGui.setEffects(effects);
            battleScreenGui.resetMenu();
        }
        else
        {
            currentForm.setVisible(false);
            currentForm = battleScreenGui;
            currentForm.setVisible(true);
            battleScreenGui.setMusic(music);
            battleScreenGui.setEffects(effects);
            battleScreenGui.resetMenu();
        }
    }
    /**
     * Calls showStats method with current fighter.
     * @param fighter 
     */
    public void showStatUpdate(yuuki.entity.Character fighter)
    {
                showStats(fighter);
    }
    /**
     * Print out character Stats.
     * @param f 
     */
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
    /**
     * Update GUI hp representation.
     * 
     * @param hp
     * @param hpMax
     * @param teamID 
     */
    public void updateGuiHP(int hp, int hpMax, int teamID)
    {
        if(teamID == 0)
        {
            battleScreenGui.setPlayerHP(hp, hpMax);
        }
        else
        {
            battleScreenGui.setMonsterHP(hp, hpMax);
        }
    }
    /**
     * Print a String.
     * @param arg 
     */
    public void print(String arg)
    {
        battleScreenGui.setText(arg);
    }
    /**
     * Print a String followed by a new line.
     * @param arg 
     */
    public void println(String arg)
    {
        battleScreenGui.setTextln(arg);
    }
    /**
     * Print out character buffs.
     * @param f 
     */
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
		//println("Press \"Enter\" to continue.");
		
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
//        if(prompt.equals("Enter player name"))
//        {
//            JFrame showingForm = null;
//            showingForm = currentForm;
//            String userName = "";
//            switchToPlayerNameScreen();
//            boolean playGame = playerName.getPlayStatus();
//            /**
//             * While the Start Game button (lblBtnBeginGame) has not been clicked, wait.
//             * Once Start Game button (lblBtnBeginGame) has been clicked:
//             * - get the Player Name the user Entered
//             * - return the Player Name
//             */
//            while(!playGame)
//            {
//                playGame = playerName.getPlayStatus();
//                try
//                {
//                    Thread.sleep(10);
//                }
//                catch(Exception e)
//                {
//                    System.out.println("GraphicalEngine.getString(String) couldn't sleep.");
//                }
//            }
//            userName = playerName.getUsersName();
//            switchToSpecifiedScreen(showingForm);
//            return userName;
//        }
       
      return NameOfPlayer;
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
    /**
     * Get user input about what move to do next.
     * 
     * @param moves
     * @return index
     */
    public int selectAction(Action[] moves)
    {
        String[] moveNames = new String[moves.length];
		for (int i = 0; i < moves.length; i++) {
			moveNames[i] = moves[i].getName();
		}
        String move = (String) JOptionPane.showInputDialog(null, "Choose a move:", "test", JOptionPane.INFORMATION_MESSAGE, null, moveNames, moveNames[0]);
          setNextMove("");
        int index = 0;
        for (int i = 0; i < moveNames.length; i++) {
            if (moveNames[i].equals(move)) {
                index = i;
                break;
            }
        }
        return index;
    }
    /**
     * Selects Monster Character.
     * Has functionality to prompt user to select Character.
     * 
     * @param fighters
     * @return yuuki.entity.Character
     */
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
                
		return charsArr[1];
                        //(Character) JOptionPane.showInputDialog(null, "Select target", "test", JOptionPane.INFORMATION_MESSAGE, null, charsArr, charsArr[0]);
    }
    
    public void display(yuuki.entity.Character speaker, String message)
    {
        
    }
}