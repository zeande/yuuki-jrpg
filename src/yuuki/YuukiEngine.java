/**
 * The game engine for the Yuuki JRPG project. This class may be executed
 * directly to run Yuuki.
 */

package yuuki;

import java.io.InputStream;
import java.io.OutputStream;

import yuuki.ui.Interactable;
import yuuki.battle.Battle;

public class YuukiEngine implements Runnable {

	/**
	 * The user interface.
	 */
	private Interactable ui;
	
	/**
	 * Program execution hook. Creates a new thread in which to execute the
	 * game engine in and then starts the thread.
	 *
	 * @param args Command line arguments. Not used.
	 */
	public static void main(String[] args) {
		InputStream in = System.in;
		OutputStream out = System.out;
		OutputStream error = System.err;
		YuukiEngine gameEngine = new YuukiEngine(in, out, error);
		Thread gameThread = new Thread(gameEngine);
		gameThread.start();
	}
	
	/**
	 * Creates a new YuukiEngine with a UI that uses only streams.
	 *
	 * @param in The input stream.
	 * @param out The output stream.
	 * @param error The error stream.
	 */
	public YuukiEngine(InputStream in, OutputStream out, OutputStreamm error) {
		ui = new StreamInterface(in, out, error);
	}
	
	/**
	 * Runs the engine.
	 */
	public void run() {
		boolean stillFighting = true;
		ui.initialize();
		ui.switchToIntroScreen();
		ui.switchToOverworldScreen();
		while (stillFighting) {
			battleOneOnOne(createJack(), createJill());
			stillFighting = ui.confirm("Battle again?", "Yes", "No");
		}
		ui.switchToEndingScreen();
		ui.destroy();
	}
	
	/**
	 * Starts a battle between two characters. Switches to the battle screen,
	 * runs the battle, then switches to the overwold screen.
	 *
	 * @param f1 The first fighter.
	 * @param f2 The second fighter.
	 *
	 * @return The winner of the battle.
	 */
	private Character battleOneOnOne(Character f1, Character f2) {
		Character[] t1 = {f1};
		Character[] t2 = {f2};
		Character[][] fighters = new Character[2][1];
		fighters[0][0] = t1;
		fighters[1][0] = t2;
		ui.display(null, "Oh no! Random monsters!");
		Battle b = new Battle(fighters);
		showBattle(fighters, b);
		return battle.getFighters(0).get(0);
	}
	
	/**
	 * Switches to the battle screen, runs through a battle, then switches to
	 * the overworld screen.
	 *
	 * @param fighters The characters in the battle.
	 * @param battle The battle to show.
	 */
	private void showBattle(Character[][] fighters, Battle b) {
		ui.switchToBattleScreen(fighters);
		runBattle(b);
		ui.switchToOverworldScreen();
	}
	
	/**
	 * Runs a battle to completion. If the UI is switched to the battle screen,
	 * it is displayed there; otherwise it is not displayed at all.
	 *
	 * @param battle The battle to run
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
			}
			if (battle.getState() == Battle.State.ENDING) {
				outputVictory(battle);
			}
		}
	}
	
	/**
	 * Creates the NPC Jack.
	 *
	 * @return Jack, the NPC.
	 */
	private NonPlayerCharacter createJack() {
		VariableStat hp, mp;
		Stat str, def, agi, acc, mag, luk;
		hp = new VariableStat("health", 0, 1);
		mp = new VariableStat("mana", 10, 3);
		str = new Stat("strength", 5, 1);
		def = new Stat("defense", 5, 1);
		agi = new Stat("agility", 5, 1);
		acc = new Stat("accuracy", 5, 1);
		mag = new Stat("magic", 5, 1);
		luk = new Stat("luck", 5, 1);
		Action[] moves = new Action[2];
		moves[0] = new BasicAttack(1.0);
		moves[1] = new BasicDefense(10);
		int lvl = 5;
		int xpb = 5;
		NonPlayerCharacter c = null;
		c = new NonPlayerCharacter("Jack", lvl, moves, hp, mp, str, def, agi,
									acc, mag, luk, xpb);
		return c;
	}
	
	/**
	 * Creates the NPC Jill.
	 *
	 * @return Jill, the NPC.
	 */
	private NonPlayerCharacter createJill() {
		VariableStat hp, mp;
		Stat str, def, agi, acc, mag, luk;
		hp = new VariableStat("health", 0, 1);
		mp = new VariableStat("mana", 10, 3);
		str = new Stat("strength", 5, 1);
		def = new Stat("defense", 5, 1);
		agi = new Stat("agility", 5, 1);
		acc = new Stat("accuracy", 5, 1);
		mag = new Stat("magic", 5, 1);
		luk = new Stat("luck", 5, 1);
		Action[] moves = new Action[2];
		moves[0] = new BasicAttack(1.0);
		moves[1] = new BasicDefense(3);
		int lvl = 5;
		int xpb = 5;
		NonPlayerCharacter c = null;
		c = new NonPlayerCharacter("Jill", lvl, moves, hp, mp, str, def, agi,
									acc, mag, luk, xpb);
		return c;
	}
	
	/**
	 * Outputs the results of a battle's turn start phase to the user
	 * interface.
	 *
	 * @param battle The battle to output the state of.
	 */
	private void outputTurnStart(Battle battle) {
		Character c = battle.getCurrentFighter();
		int recoveredMana = battle.getRecoveredMana();
		ui.display(c, "It looks like I'm up next.");
		ArrayList<Buff> expiredBuffs = c.getExpiredBuffs();
		for (Buff expired: expiredBuffs) {
			ui.showBuffDeactivation(expired);
		}
		ui.showRecovery(c, c.getMP(), recoveredMana);
	}
	
	/**
	 * Outputs the results of a battle's action get phase to the user
	 * interface.
	 *
	 * @param battle The battle to output the state of.
	 */
	private void outputActionGet(Battle battle) {
		Action a = battle.getLastAction();
		ui.showActionPreperation(a);
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
			Character o = a.getOrigin();
			ui.showDamage(o, o.getMP(), (int)a.getCost());
			ui.showActionUse(a);
			int[] effects = a.getActualEffects();
			Character[] targets = a.getTargets();
			for (i = 0; i < effects.length; i++) {
				Character t = targets[i];
				int damage = effects[i];
				// assume it's damage to HP
				ui.showDamage(t, t.getHP(), damage);
			}
			if (a.getOriginBuff() != null) {
				ui.showBuffActivation(a.getOriginBuff());
			}
			if (a.getTargetBuff() != null) {
				ui.showBuffActivation(a.getTargetBuff());
			}
		} else {
			ui.showActionFailure(a);
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
		for (Buff b: activeBuffs) {
			ui.showBuffApplication(b);
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
			ui.showCharacterRemoval(c);
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
}
