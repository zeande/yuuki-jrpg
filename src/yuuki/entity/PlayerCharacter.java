/**
 * A Character that can be controlled by the interface.
 */

package yuuki.entity;

import java.util.ArrayList;

import yuuki.action.Action;
import yuuki.util.ObjectMessenger;

public class PlayerCharacter extends Character {

	/**
	 * A reference from which this Character can get its Action from.
	 */
	private ObjectMessenger<Action> actionLink;

	/**
	 * Allocates a new Character. Most stats are set manually, but experience
	 * is automatically calculated from the starting level. All stats are the
	 * base stats; all actual stats are calculated by multiplying the stat gain
	 * by the level and adding the base stat.
	 *
	 * @param name The name of the Character.
	 * @param level The level of the new Character. XP is set to match this.
	 * @param moves The moves that this Character knows.
	 * @param hp The health stat of the new Character.
	 * @param mp The mana stat of the new Character.
	 * @param strength The physical strength of the Character.
	 * @param defense The Character's resistance to damage.
	 * @param agility The Character's avoidance of hits.
	 * @param accuracy The Character's ability to hit.
	 * @param magic The magical ability of the Character.
	 * @param luck The ability of the Character to get a critical hit.
	 * @param actionLink An ActionMessenger that serves as move input.
	 */
	public PlayerCharacter(String name, int level, Action[] moves,
					VariableStat hp, VariableStat mp, Stat strength,
					Stat defense, Stat agility, Stat accuracy, Stat magic,
					Stat luck, ObjectMessenger<Action> actionLink) {
		super(name, level, moves, hp, mp, strength, defense, agility, accuracy,
				magic, luck);
		this.actionLink = actionLink;
	}
	
	/**
	 * Decides what move to do next based on input from the interface.
	 *
	 * @param fighters The states of the other players. This is ignored.
	 *
	 * @return The move that was selected by the interface, otherwise null if
	 * this PlayerCharacter is still waiting for one.
	 */
	public Action getNextAction(ArrayList<ArrayList<Character>> fighters) {
		Action a = null;
		if (actionLink.optionReady()) {
			a = actionLink.getOption().clone();
			actionLink.resetOption();
		} else {
			actionLink.setChoices(moves);
		}
		return a;
	}

}
