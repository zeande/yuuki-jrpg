package yuuki.ui;
import java.util.ArrayList;
import yuuki.entity.*;
import yuuki.action.*;
import yuuki.battle.Battle;
import yuuki.buff.Buff;
import yuuki.entity.Character;
import java.awt.*;
import yuuki.gui.UI.*;
/**
 *Highest Level Class.
 * Manages what to do next.
 * 
 * @author Caleb Smtih
 * @version 11/16/12
 */
public class GraphicalEngineTest {
    //Initiate GraphicalEngine.
    private PlayerCharacter player = null;
    private MonsterFactory mf = new MonsterFactory();
    GraphicalEngine ge = new GraphicalEngine();
    Character [][] fighters = null;
    Battle b = null;
    /**
     * Main Method.
     * @param args 
     */
    public static void main(String[] args) {
        GraphicalEngineTest get = new GraphicalEngineTest();
        get.decideNextScreen("introScreen");
    }
    /**
     * Decides what screen to switch too.
     * @param input 
     */
    public void decideNextScreen(String input)
    {
        if(input == "MainTitle.BtnNewGame")
        {
            input = "";
            playerCreation(ge.optionsMenu.getMusic(), ge.optionsMenu.getEffects());
        }
        if(input == "MainTitle.BtnLoadGame")
        {
            input = "";
            System.out.println("<Hardcoded Responce @ GraphicalEngineTest.decideNextScreen>");
            System.out.println("Load Game - No Functionality Yet.");
            decideNextScreen("introScreen");
        }
        else if(input == "MainTitle.BtnOptionsMenu")
        {
            input = "";
            optionsMenu();
        }
        else if(input == "Exit")
        {
            input = "";
            exit();
        }
        else if(input == "introScreen")
        {
            input = "";
            introScreen(ge.optionsMenu.getMusic(), ge.optionsMenu.getEffects());
        }
        else if(input == "PlayerName.btnBattleScreen")
        {
            input = "";
            ge.switchToBattleScreen(fighters, ge.optionsMenu.getMusic(), ge.optionsMenu.getEffects());
            battleScreenNav();
        }
        else if(input == "BattleScreen.btnMenu")
        {
            input = "";
            battleScreenNav();
        }
        else if(input == "BattleScreen.btnOptionsMenu")
        {
            input = "";
            String nextForm = ge.switchToOptionsScreen(ge.optionsMenu.getMusic(), ge.optionsMenu.getMusic());
            while(nextForm == "")
            {
                try
                {
                    Thread.sleep(1);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
            ge.switchToBattleScreen(fighters, ge.optionsMenu.getMusic(), ge.optionsMenu.getEffects());
            battleScreenNav();
        }
        else if(input == "BattleScreen.btnCreateNewGame")
        {
            input = "";
            System.out.println("<Hardcoded Responce @ GraphicalEngineTest.decideNextScreen>");
            System.out.println("New Game - No Functionality Yet.");
            battleScreenNav();
        }
        else if(input == "BattleScreen.btnSaveGame")
        {
            input = "";
            System.out.println("<Hardcoded Responce @ GraphicalEngineTest.decideNextScreen>");
            System.out.println("Save Game - No Functionality Yet.");
            battleScreenNav();
        }
        else if(input == "BattleScreen.btnLoadGame")
        {
            input = "";
            System.out.println("<Hardcoded Responce @ GraphicalEngineTest.decideNextScreen>");
            System.out.println("Load Game - No Functionality Yet.");
            battleScreenNav();
        }
        else if(input == "choice")
        {
            System.out.println("ahhhahaha!");
        }
        else
        {
            input = "";
            System.out.println("Unhandled formInput: \"" + input + "\" @ GraphicalEngineTest.decideNextScreen");
        }
    }
    /**
     * Switches to introScreen.
     * Provides it with current audio options.
     * 
     * @param music
     * @param effects 
     */
    public void introScreen(boolean music, boolean effects)
    {
        String nextForm = ge.switchToIntroScreen(music, effects);
        decideNextScreen(nextForm);
    }
    /**
     * Switches to playerCreation Screen.
     * Instantiates new playerCharacter.
     * Provides JFrame with current audio options.
     * 
     * @param music
     * @param effects 
     */
    public void playerCreation(boolean music, boolean effects)
    {
        String nextForm = ge.switchToPlayerNameScreen(music, effects);
            String playerName = ge.getString("Give me PlayerName!");
            Action[] playerMoves = new Action[3];
            playerMoves[0] = new BasicAttack(8);
            playerMoves[1] = new BasicDefense(4);
            playerMoves[2] = new Flee();
            VariableStat hp = new VariableStat("health", 100, 15);
            VariableStat mp = new VariableStat("mana", 100, 15);
            Stat str = new Stat("strength", 10, 1);
            Stat def = new Stat("defence", 10, 1);
            Stat agl = new Stat("agility", 10, 1);
            Stat acc = new Stat("accuracy", 10, 1);
            Stat mag = new Stat("magic", 10, 1);
            Stat luk = new Stat("luck", 10, 1);
            player = new PlayerCharacter(playerName, 1, playerMoves, hp, mp, str, def, agl, acc, mag, luk, ge);
            fighters = makeTeams();
        decideNextScreen(nextForm);
    }
    /**
     * Switches to optionsMenu.
     */
    public void optionsMenu()
    {
        String nextForm = ge.switchToOptionsScreen();
        decideNextScreen(nextForm);
    }
    /**
     * ?
     * @param choice 
     */
    public void battleScreen(String choice)
    {
       ge.setNextMove(choice);
       
    }
    /**
     * Provides Navigation on the BattleScreen.
     */
    public void battleScreenNav()
    {
        b = new Battle(fighters);
        runBattle(b);
        String choice = ge.battleScreenGui.Nav();
        if(choice == "BattleScreen.btnMenu" || choice == "BattleScreen.btnOptionsMenu" || choice == "BattleScreen.btnCreateNewGame" || choice == "BattleScreen.btnSaveGame" || choice == "BattleScreen.btnLoadGame" || choice == "Exit")
        {
            decideNextScreen(choice);
            choice = "";
        }
        else if(choice == "Attack" || choice == "Defend" || choice == "Flee")
        {
            battleScreen(choice);
            choice = "";
        }
    }
    /**
     * Makes 2 teams, one with player on it, and one with Monsters.
     * @return fighters 
     */
     public Character[][] makeTeams()
     {
         fighters = new Character[2][];
         fighters[0] = new Character[]{player};
         fighters[1] = mf.createRandomTeam(1, 1, player.getLevel(), "slime");
         return fighters;
     }
     /**
      * Runs Battle.
      * @param battle 
      */
     private void runBattle(Battle battle) {
		while (battle.advance()) {
			switch (battle.getLastState()) {
				case STARTING_TURN:
					outputTurnStart(battle);
					break;
					
				case GETTING_ACTION:
					outputActionGet(battle);
					break;
					
				case APPLYING_ACTION:
					outputActionApplication(battle);
					break;
					
				case APPLYING_BUFFS:
					outputBuffApplication(battle);
					break;
					
				case CHECKING_DEATH:
					outputDeathCheck(battle);
					break;
					
				case ENDING_TURN:
					outputTeamDeathCheck(battle);
					break;
					
				case CHECKING_VICTORY:
					// not sure we care about this state
					break;
					
				case LOOTING:
					outputLoot(battle);
					break;
					
				default:
					break;
			}
			if (battle.getState() == Battle.State.ENDING) {
				outputVictory(battle);
			}
                }
     }
     /**
      * Outputs the start of a battle's beginning phase to the user interface.
      * @param battle 
      */
     private void outputTurnStart(Battle battle) {
		Character c = battle.getCurrentFighter();
		int recoveredMana = battle.getRegeneratedMana();
		//ge.display(c, "It looks like I'm up next.");
		ArrayList<Buff> expiredBuffs = c.getExpiredBuffs();
		for (Buff expired: expiredBuffs) {
			ge.showBuffDeactivation(expired);
		}
		if (recoveredMana != 0) {
			ge.showRecovery(c, c.getMPStat(), recoveredMana);
		}
		ge.showStatUpdate(c);
	}
	
	/**
	 * Outputs the results of a battle's action get phase to the user
	 * interface.
	 *
	 * @param battle The battle to output the state of.
	 */
	private void outputActionGet(Battle battle) {
		Action a = battle.getLastAction();
		ge.showActionPreperation(a);
	}
	
	/**
	 * Outputs the results of a battle's action application phase to the user
	 * interface.
	 *
	 * @param battle The battle to output the state of.
	 */
	private void outputActionApplication(Battle battle) {
		Action a = battle.getLastAction();
		if (a.wasSuccessful()) {
			if (a.getCostStat() != null) {
				outputActionCost(a);
			}
			ge.showActionUse(a);
			if (a.getEffectStat() != null) {
				outputActionEffects(a);
			}
			if (a.getOriginBuff() != null) {
				ge.showBuffActivation(a.getOriginBuff());
			}
			if (a.getTargetBuff() != null) {
				ge.showBuffActivation(a.getTargetBuff());
			}
		} else {
			ge.showActionFailure(a);
		}
	}
	
	/**
	 * Outputs the results of an action cost to the user interface.
	 *
	 * @param action The Action to output.
	 */
	private void outputActionCost(Action a) {
		ge.showDamage(a.getOrigin(), a.getCostStat(), (int)a.getCost());
		ge.showStatUpdate(a.getOrigin());
	}
	
	/**
	 * Outputs the effects of an action to the user interface.
	 *
	 * @param a The Action to output.
	 */
	private void outputActionEffects(Action a) {
		int[] effects = a.getActualEffects();
		ArrayList<Character> targets = a.getTargets();
		for (int i = 0; i < effects.length; i++) {
			Character t = targets.get(i);
			int damage = effects[i];
			ge.showDamage(t, a.getEffectStat(), damage);
			ge.showStatUpdate(t);
		}
	}
	
	/**
	 * Outputs the results of a battle's buff application phase to the user
	 * interface.
	 *
	 * @param battle The battle to output the state of.
	 */
	private void outputBuffApplication(Battle battle) {
		Character currentFighter = battle.getCurrentFighter();
		ArrayList<Buff> buffs = currentFighter.getBuffs();
		for (Buff b: buffs) {
			ge.showBuffApplication(b);
			ge.showStatUpdate(currentFighter);
		}
	}
	
	/**
	 * Outputs the results of a battle's death check phase to the user
	 * interface.
	 *
	 * @param battle The battle to output the state of.
	 */
	private void outputDeathCheck(Battle battle) {
		ArrayList<Character> removed = battle.getRemovedFighters();
		for (Character c: removed) {
			ge.showCharacterRemoval(c);
		}
	}
	
	/**
	 * Outputs the results of a battle's team death check phase to the user
	 * interface.
	 *
	 * @param battle The battle to output the state of.
	 */
	private void outputTeamDeathCheck(Battle battle) {}
	
	/**
	 * Outputs the results of a battle's loot calculation phase to the user
	 * interface.
	 *
	 * @param battle The battle to output the state of.
	 */
	private void outputLoot(Battle battle) {}
	
	/**
	 * Outputs the results of a battle's victory check phase to the user
	 * interface.
	 *
	 * @param battle The battle to output the state of.
	 */
	private void outputVictory(Battle battle) {}

     
     public void exit()
    {
       System.exit(0);
    }
    
}