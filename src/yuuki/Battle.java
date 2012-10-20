/**
 * The battle engine for all fights in the Yuuki system. It is not
 * independently-driven. It must be explicitly instructed to advance its
 * execution. This allows it to be decoupled from any interfaces..
 *
 * @version 10/20/12
 */

package yuuki;

import java.util.ArrayList;

class Battle {

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
	private ArrayList<Contestant> fighters;

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
		this.fighters = new ArrayList<Contestant>();
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
	 * @return True if more calls to advance() are required before the
	 * battle is over; otherwise, false.
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
		Character c = getCurrentFighter().character;
		int amount = (int) Math.floor(c.getMaxMP() * MANA_GEN);
		c.gainMP((amount > 0) ? amount : 1);
	}

	/**
	 * Gets the move that the current Character wishes to use.
	 */
	private void getCharacterAction() {
		Character c = getCurrentFighter().character;
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
		Contestant c = getCurrentFighter();
		for (int i = 0; i < c.buffs.size(); i++) {
			Buff buff = c.buffs.get(i);
			if (buff.isOverTime()) {
				buff.applyTo(c.character);
			}
		}
	}

	/**
	 * Removes the targeted fighter if he is now dead.
	 */
	private void checkDeath() {
		int targetId = currentAction.getTarget();
		Contestant target = fighters.get(targetId);
		if (!target.character.isAlive()) {
			fighters.remove(targetId);
		}
	}

	/**
	 * Removes the targeted fighter's team if there is no longer anyone on
	 * it.
	 */
	private void checkTeamStatus() {
		int team = currentAction.getTargetTeam();
		if (getFighterCount(team) == 0) {
			teams.remove(new Integer(team));
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
		for (Contestant c: fighters) {
			if (c.team == team) {
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
		Contestant c = getCurrentFighter();
		ArrayList<Buff> buffs = c.buffs;
		int removeCount = 0;
		for (int i = 0; i < buffs.size(); i++) {
			Buff b = buffs.get(i);
			if (b.getRemainingTurns() == 0) {
				c.buffs.remove(i - removeCount);
				removeCount++;
			}
		}
	}

	/**
	 * Gets the fighter whose turn it currently is.
	 *
	 * @return The current fighter.
	 */
	private Contestant getCurrentFighter() {
		return fighters.get(currentFighter);
	}

	/**
	 * Assigns characters to the contestents list. Team number is started
	 * at 0 and simply increments for each team.
	 */
	private void assignToFighters(Character[][] participants) {
		int team = 0;
		for (Character[] team: participants) {
			for (Character c: team) {
				Contestant fighter = new Contestant();
				fighter.character = c;
				fighter.team = team;
				this.fighters.add(fighter);
			}
			team++;
		}
		createTeams(team);
	}

	/**
	 * Creates the array of teams with the specified number of teams.
	 *
	 * @param count The highest team number to include.
	 */
	private void createTeams(int teams) {
		for (int i = 0; i < teams + 1; i++) { // teams is 0-indexed
			teams.add(i);
		}
	}
	
	/**
	 * Sets the play order for the battle.
	 */
	private void orderFighters() {
		// TODO: put the fighters in some play order
	}
}
