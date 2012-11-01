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
	 * The battle engine of the currently running battle.
	 */
	private Battle battleEngine;
	
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
		battleEngine = null;
	}
	
	/**
	 * Runs the engine.
	 */
	public void run() {
		ui.initialize();
		ui.switchToIntroScreen();
		ui.switchToOverworldScreen();
		battle(createJack(), createJill());
	}
	
	/**
	 * Starts a battle between two characters.
	 *
	 * @param f1 The first fighter.
	 * @param f2 The second fighter.
	 *
	 * @return The winner of the battle.
	 */
	private void battle(Character f1, Character f2) {
		Character[] t1 = {f1};
		Character[] t2 = {f2};
		Character[][] fighters = new Character[2][1];
		fighters[0][0] = t1;
		fighters[1][0] = t2;
		ui.display(null, "Oh dear, is that a random monster?");
		battleEngine = new Battle(fighters);
		ui.switchToBattleScreen(fighters);
		while (battleEngine.advance()) {
			Action a = battleEngine.getLastAction();
			Character c = battleEngine.getCurrentFighter();
			ArrayList<Buff> activeBuffs = c.getBuffs();
			switch (battleEngine.getLastState()) {
				case STARTING_TURN:
					outputTurnStart();
					break;
					
				case GETTING_ACTION:
					outputActionGet();
					break;
					
				case APPLYING_ACTION:
					outputActionApplication();
					break;
					
				case APPLYING_BUFFS:
					outputBuffApplication();
					break;
					
				case CHECKING_DEATH:
					outputDeathCheck();
					break;
					
				case ENDING_TURN:
					outputTeamDeathCheck();
					break;
					
				case CHECKING_VICTORY:
					// not sure we care about this state
					break;
					
				case LOOTING:
					outputLoot();
					break;
			}
			if (battleEngine.getState() == Battle.State.ENDING) {
				outputVictory();
			}
		}
		Character winner = battleEngine.getFighters(0).get(0);
		return winner;
	}
	
	/**
	 * Creates the NPC Jack.
	 *
	 * @return Jack, the NPC.
	 */
	private NonPlayerCharacter createJack() {
		VariableStat hp, mp;
		Stat str, def, agi, acc, mag, luk;
		hp = new VariableStat(0, 1);
		mp = new VariableStat(10, 3);
		str = new Stat(5, 1);
		def = new Stat(5, 1);
		agi = new Stat(5, 1);
		acc = new Stat(5, 1);
		mag = new Stat(5, 1);
		luk = new Stat(5, 1);
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
		hp = new VariableStat(0, 1);
		mp = new VariableStat(10, 3);
		str = new Stat(5, 1);
		def = new Stat(5, 1);
		agi = new Stat(5, 1);
		acc = new Stat(5, 1);
		mag = new Stat(5, 1);
		luk = new Stat(5, 1);
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
	 * Outputs the results of the current battle's turn start phase to the user
	 * interface.
	 */
	private void outputTurnStart() {
		Character c = battleEngine.getCurrentFighter();
		int recoveredMana = battleEngine.getRecoveredMana();
		ui.display(c, "It looks like I'm up next.");
		ArrayList<Buff> expiredBuffs = c.getExpiredBuffs();
		for (Buff expired: expiredBuffs) {
			ui.showBuffDeactivation(expired);
		}
		ui.showRecovery(c, c.getMP(), recoveredMana);
	}
	
	/**
	 * Outputs the results of the current battle's action get phase to the user
	 * interface.
	 */
	private void outputActionGet() {
		Action a = battleEngine.getLastAction();
		ui.showActionPreperation(a);
	}
	
	/**
	 * Outputs the results of the current battle's action application phase to
	 * the user interface.
	 */
	private void outputActionApplication() {
		Action a = battleEngine.getLastAction();
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
	 * Outputs the results of the current battle's buff application phase to
	 * the interface.
	 */
	private void outputBuffApplication() {
		Character currentFighter = battleEngine.getCurrentFighter();
		ArrayList<Buff> buffs = currentFighter.getBuffs();
		for (Buff b: activeBuffs) {
			ui.showBuffApplication(b);
		}
	}
	
	/**
	 * Outputs the results of the current battle's death check phase to the
	 * user interface.
	 */
	private void outputDeathCheck() {
		ArrayList<Character> removed = battleEngine.getRemovedFighters();
		for (Character c: removed) {
			ui.showCharacterRemoval(c);
		}
	}
	
	/**
	 * Outputs the results of the current battle's team death check phase to
	 * the user interface.
	 */
	private void outputTeamDeathCheck() {}
	
	/**
	 * Outputs the results of the current battle's loot calculation phase to
	 * the user interface.
	 */
	private void outputLoot() {}
	
	/**
	 * Outputs the results of the current battle's victory check phase to the
	 * user interface.
	 */
	private void outputVictory() {}
}
