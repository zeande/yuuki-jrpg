/**
 * The battle engine for all fights in the Yuuki system. It is not
 * independently-driven. It must be explicitly instructed to advance its
 * execution. This allows it to be decoupled from any interfaces..
 *
 * @version 10/20/12
 */

package yuuki;

import java.util.ArrayList;

public class Battle {

	/**
	 * Struct for keeping track of fighter teams and buffs.
	 */
	private static class Contestant {
		public Character character;
		public ArrayList<Buff> buffs = new ArrayList<Buff>();
		public int team;
	}

	/**
	 * The potential states of a Battle.
	 */
	private static enum State {
		STARTING_TURN,
		GETTING_ACTION,
		APPLYING_ACTION,
		APPLYING_BUFFS,
		CHECKING_DEATH,
		ENDING_TURN,
		CHECKING_VICTORY,
		LOOTING,
		ENDING;
	}

	/**
	 * The percent of total mana gained in a turn.
	 */
	private static final double MANA_GEN = 0.05;

	/**
	 * The current state of this Battle. This determines what action is
	 * taken the next time advance() is called.
	 */
	private State state;

	/**
	 * Whether the last action was successful.
	 */
	private boolean actionSuccess;

	/**
	 * The current Character's action.
	 */
	private Action currentAction;

	/**
	 * The teams currently active.
	 */
	private ArrayList<Integer> teams;

	/**
	 * The Characters involved with the fight.
	 */
	private ArrayList<Character> fighters;

	/**
	 * The player whose turn it currently is.
	 */
	private int currentFighter;

	/**
	 * Begins a new battle with the given participants.
	 *
	 * @param participants The Characters involved in the battle. The array
	 * is the teams of the Characters; each of these is an array containing
	 * the Characters on that team.
	 */
	public Battle(Character[][] participants) {
		this.fighters = new ArrayList<Character>();
		this.teams = new ArrayList<Integer>();
		assignToFighters(participants);
		orderFighters();
		currentFighter = 0;
		state = State.STARTING_TURN;
	}

	/**
	 * Advances battle through the current execution state. Each state
	 * consists of only a few operations that are carried out before the
	 * next state is reached. In between execution states the user is free
	 * to query this Battle for information about the fight.
	 *
	 * @return True if more calls to advance() are required before the battle
	 * is over; otherwise, false.
	 */
	public boolean advance() {
		boolean complete = false;
		switch (state) {
			case STARTING_TURN:
				checkBuffs();
				regenerateMana();
				state = State.GETTING_ACTION;
				break;

			case GETTING_ACTION:
				getCharacterAction();
				state = State.APPLYING_ACTION;
				break;

			case APPLYING_ACTION:
				actionSuccess = applyAction();
				state = State.APPLYING_BUFFS;
				break;

			case APPLYING_BUFFS:
				applyBuffs();
				state = State.CHECKING_DEATH;
				break;

			case CHECKING_DEATH:
				checkDeath();
				state = State.ENDING_TURN;
				break;

			case ENDING_TURN:
				checkTeamStatus();
				state = State.CHECKING_VICTORY;
				break;

			case CHECKING_VICTORY:
				if (battleIsOver()) {
					winner = teams.get(0);
					state = State.LOOTING;
				} else {
					setNextPlayer();
					state = State.STARTING_TURN;
				}
				break;

			case LOOTING:
				calculateLoot();
				state = State.ENDING;
				break;

			case ENDING:
				complete = true;
				break;
		}
		return complete;
	}

	/**
	 * Regenerates the mana of the current Character.
	 */
	private void regenerateMana() {
		Character c = getCurrentFighter();
		int amount = (int) Math.floor(c.getMaxMP() * MANA_GEN);
		c.gainMP((amount > 0) ? amount : 1);
	}

	/**
	 * Gets the move that the current Character wishes to use.
	 */
	private void getCharacterAction() {
		Character c = getCurrentFighter();
		this.currentAction = c.?(); // TODO: get action from character
	}

	/**
	 * Applies the effects of the Character action to the target(s).
	 */
	private void applyAction() {
		// TODO: fill-in the method
	}

	/**
	 * Applies over-time buffs to the current Character.
	 */
	private void applyBuffs() {
		Character c = getCurrentFighter();
		c.applyBattleBuffs();
	}

	/**
	 * Removes the targeted fighter if he is now dead.
	 */
	private void checkDeath() {
		Character[] targets = currentAction.getTargets();
		for (Character c: targets) {
			if (!c.isAlive()) {
				removeFighter(c.getFighterId());
			}
		}
	}

	/**
	 * Removes the targeted fighter's team if there is no longer anyone on
	 * it.
	 */
	private void checkTeamStatus() {
		int[] affectedTeams = currentAction.getAffectedTeams();
		for (int t: affectedTeams) {
			if (getFighterCount(t) == 0) {
				teams.remove(new Integer(t));
			}
		}
	}

	/**
	 * Gets the number of fighters on the specified team.
	 *
 	 * @param team The team to check the fighter count for.
	 *
	 * @return the number of fighters on the given team.
	 */
	private int getFighterCount(int team) {
		int count = 0;
		for (Character c: fighters) {
			if (c.getTeamId() == team) {
				count++:
			}
		}
		return count;
	}

	/**
	 * Checks whether the battle is over.
 	 *
	 * @return True if only one team remains in active play; otherwise false.
	 */
	private boolean battleIsOver() {
		return (teams.size() == 1);
	}

	/**
	 * Sets the current player to the player whose turn is next.
	 */
	private void setNextPlayer() {
		currentFighter++; // won't work if killed player lower on list
		// TODO: algo that takes killed chars into account
		if (currentFighter > fighters.size()) {
			currentFighter = 0;
		}
	}

	/**
	 * Calculates the loot for the battle. The result is kept in this
	 * Battle's state.
	 */
	private void calculateLoot() {
		// TODO: loot calculation
	}

	/**
	 * Removes buffs on the current Character that have expired.
	 */
	private void checkBuffs() {
		Character c = getCurrentFighter();
		c.removeExpiredBuffs();
	}

	/**
	 * Gets the fighter whose turn it currently is.
	 *
	 * @return The current fighter.
	 */
	private Character getCurrentFighter() {
		return fighters.get(currentFighter);
	}

	/**
	 * Assigns characters to the fighters list. Each character's fighter ID and
	 * team ID is set and they are added to the internal array. The team ID is
	 * arbitrary; as long as Characters on the same team have the same team ID,
	 * the actual number doesn't matter. The fighter ID is simply set in the
	 * order that the participants are given.
	 */
	private void assignToFighters(Character[][] participants) {
		for (Character[] team: participants) {
			for (Character c: team) {
				c.setTeamId(teams.size());
				c.setFighterId(fighters.size());
				c.initBattleBuffs();
				fighters.add(c);
			}
			teams.add(teams.size());
		}
	}
	
	/**
	 * Sets the play order for the battle.
	 */
	private void orderFighters() {
		// TODO: put the fighters in some play order
	}
	
	/**
	 * Removes a fighter from the field. All of the Character's battle params
	 * are reset and it is removed from the list of fighters.
	 *
	 * @param id The id of the Character to remove.
	 */
	private void removeFighter(int id) {
		Character f = fighters.get(id);
		f.setTeamId(-1);
		f.setFighterId(-1);
		f.clearBattleBuffs();
		fighters.remove(id);
	}
}
