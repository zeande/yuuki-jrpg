package yuuki.ui;
import java.util.ArrayList;
import yuuki.entity.*;
import yuuki.action.*;
import yuuki.battle.Battle;
import yuuki.buff.Buff;
import yuuki.entity.Character;
/**
 *
 * @author Caleb Smtih
 * @version 11/16/12
 */
public class GraphicalEngineTest {
    //Initiate GraphicalEngine.
    GraphicalEngine ge = new GraphicalEngine();
    private PlayerCharacter player = null;
    private MonsterFactory mf = new MonsterFactory();
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
        
    public static void main(String [] args)
    {
        GraphicalEngineTest get = new GraphicalEngineTest();
        get.introScreen();
    }
    public void introScreen()
    {
        mainTitleStatus = true;
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
        Character [][] fighters = makeTeams();
        ge.switchToBattleScreen(fighters);
        Battle b = new Battle(fighters);
        ge.battleScreen.setText("Before Battle Creation");
        runBattle(b);
        Character winner = b.getFighters(0).get(0);
    }
    
    public void battleScreenNav()
    {
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
    
     public Character[][] makeTeams()
     {
         Character[][] fighters = new Character[2][];
         fighters[0] = new Character[]{player};
         fighters[1] = mf.createRandomTeam(1, 1, player.getLevel(), "slime");
         return fighters;
     }
     
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
     
     private void outputTurnStart(Battle battle) {
		Character c = battle.getCurrentFighter();
		int recoveredMana = battle.getRegeneratedMana();
		ge.display(c, "It looks like I'm up next.");
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