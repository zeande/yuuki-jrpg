/**
 * The basic attack. This is a zero-cost skill action that does some damage to
 * a single target. This Action is only applied to the first target.
 */

package yuuki.action;

public class BasicAttack extends Skill {

	/**
	 * Creates a new BasicAttack.
	 *
	 * @param damage The base damage of the attack.
	 */
	public BasicAttack(double damage) {
		super("attack", damage, 0.0);
	}
	
	/**
	 * Applies damage to the first target.
	 */
	protected void applyEffects() {
		Character target = targets.get(0);
		double mod = (origin.getStrength() / target.getDefense());
		int totalDamage = (int) Math.round(damage + mod);
		target.setHP(target.getHP() - totalDamage);
	}

}