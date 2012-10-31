/**
 * The game engine for the Yuuki JRPG project. This class may be executed
 * directly to run Yuuki.
 */

package yuuki;

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
		YuukiEngine gameEngine = new YuukiEngine();
		Thread gameThread = new Thread(gameEngine);
		gameThread.start();
	}
	
	/**
	 * Creates a new YuukiEngine with a UI that uses only the console.
	 */
	public YuukiEngine() {
		ui = new StreamInterface(System.in, System.out, System.err);
		battleEngine = null;
	}
	
	/**
	 * Runs the engine.
	 */
	public void run() {
		ui.initialize();
		ui.switchToIntroScreen();
		ui.switchToOverworldScreen();
		battle();
	}
	
	private void battle() {
		Character[] t1 = {createChar1()};
		Character[] t2 = {createChar2()};
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
					ui.display(c, "It looks like I'm up next.");
					break;
					
				case GETTING_ACTION:
					ui.showActionPreperation(a);
					break;
					
				case APPLYING_ACTION:
					if (a.wasSuccessful()) {
						Character o = a.getOrigin();
						ui.showDamage(o, o.getMP(), (int)a.getCost());
						ui.showActionUse(a);
						// show the damage done to all targets
						// ui.showDamage() - for all targets
						if (a.getOriginBuff() != null) {
							ui.showBuffActivation(a.getOriginBuff());
						}
						if (a.getTargetBuff() != null) {
							ui.showBuffActivation(a.getTargetBuff());
						}
					} else {
						ui.showActionFailure(a);
					}
					break;
					
				case APPLYING_BUFFS:
					for (Buff b: activeBuffs) {
						ui.showBuffApplication(b);
					}
					break;
					
				case CHECKING_DEATH:
					ArrayList<Character> removed;
					removed = battleEngine.getRemovedFighters();
					for (Character gone: removed) {
						ui.showCharacterRemoval(gone);
					}
					break;
					
				case ENDING_TURN:
					break;
					
				case CHECKING_VICTORY:
					break;
					
				case LOOTING:
					break;
			}
			if (battleEngine.getState() == Battle.State.ENDING) {
			
			}
		}
	}
	
	private Character makeChar1() {
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
		Character c = new NonPlayerCharacter("Jack", lvl, moves, hp, mp, str,
											def, agi, acc, mag, luk, xpb);
		return c;
	}
	
	private Character makeChar2() {
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
		Character c = new NonPlayerCharacter("Jill", lvl, moves, hp, mp, str,
											def, agi, acc, mag, luk, xpb);
		return c;
	}
}
