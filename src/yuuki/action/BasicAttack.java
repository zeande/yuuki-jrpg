/**
 * The basic attack. This is a zero-cost skill action that does some damage to
 * a single target. This Action is only applied to the first target.
 */

package yuuki.action;

import yuuki.entity.Character;

public class BasicAttack extends Skill {

	/**
	 * Creates a new BasicAttack.
	 *
	 * @param damage The base damage of the attack.
	 */
	public BasicAttack(double damage) {
		super("attack", damage, 0.0, null, null);
	}
	
	/**
	 * Applies damage to the first target.
	 */
	protected void applyEffect() {
		Character target = targets.get(0);
		int oStr = origin.getStrength().getEffective(origin.getLevel());
		int tDef = target.getDefense().getEffective(target.getLevel());
		double mod = (oStr / tDef);
		int totalDamage = (int) Math.round(effect + mod);
		target.getHP().lose(totalDamage);
	}
	
	protected void applyBuffs() {}

}