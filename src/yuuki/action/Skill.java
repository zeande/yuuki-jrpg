/**
 * An Action that costs mana to apply some effect to the targets.
 */

package yuuki.action;

public abstract class Skill extends Action {

	/**
	 * Creates a new Skill that costs the specified amount of mana.
	 *
	 * @inheritDoc
	 */
	public Skill(String name, double effect, double manaCost) {
		super(name, effect, manaCost);
	}
	
	/**
	 * Attempts to apply the action by subtracting mana from the origin and
	 * then applying the result.
	 */
	protected boolean applyCost() {
		int manaCost = (int) cost;
		if (origin.getMP() < manaCost) {
			return false;
		} else {
			origin.setMP(origin.getMP() - manaCost);
			return true;
		}
	}
	
}