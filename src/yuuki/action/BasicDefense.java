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
	
	/**
	 * Creates a clone of this BasicDefense.
	 */
	
	/**
	 * Applies the cost to the origin. The origin does not need to spend
	 * anything to use this Action, so this method always returns true.
	 *
	 * @return True unconditionally.
	 */
	protected boolean applyCost() {
		return true;
	}
	
	/**
	 * Has no effect.
	 */
	protected void applyEffect() {}
	
	/**
	 * Applies the defense buff to the origin.
	 */
	protected void applyBuffs() {
		origin.addBuff(originBuff);
	}

}