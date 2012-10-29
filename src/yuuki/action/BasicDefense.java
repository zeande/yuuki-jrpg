/**
 * Buffs the Character's defense for a bit.
 */

package yuuki.action;

import yuuki.buff.DefenseBuff;

public class BasicDefense extends Action {

	/**
	 * Creates a new BasicDefense.
	 *
	 * @param turns Number of turns it lasts.
	 */
	public BasicDefense(int turns) {
		super("defend", 0.0, 0.0, null,
				new DefenseBuff("defending", 1.5, turns));
	}
	
	protected boolean applyCost() {
		return true;
	}
	
	protected void applyEffect() {}
	
	protected void applyBuffs() {}

}