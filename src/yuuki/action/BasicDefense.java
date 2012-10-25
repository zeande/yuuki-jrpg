/**
 * Buffs the Character's defense for a bit.
 */

package yuuki.action;

public class BasicDefense extends Action {

	/**
	 * Creates a new BasicDefense.
	 *
	 * @param turns Number of turns it lasts.
	 */
	public BasicDefense(int turns) {
		DefenseBuff db = new DefenseBuff("defending", 1.5, turns);
		super("defend", 0.0, 0.0, null, db);
	}
	
	protected boolean applyCost() {
		return true;
	}
	
	protected void applyEffects() {}

}