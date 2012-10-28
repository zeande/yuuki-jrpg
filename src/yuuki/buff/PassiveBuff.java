/**
 * A Buff that only has an effect upon activation and deactivation.
 */

package yuuki.buff;

import yuuki.entity.Character;

public abstract class PassiveBuff extends Buff {

	/**
	 * Creates a new PassiveBuff for a Character.
	 *
	 * @param name The display name of this Buff.
	 * @param effect The amount of effect that this Buff has.
	 * @param turns The number of turns that this Buff lasts for.
	 */
	public PassiveBuff(String name, double effect, int turns) {
		super(name, effect, turns);
	}
	
	/**
	 * Has no effect. PassiveBuff and derived classes do not apply any effects
	 * on a per-turn basis.
	 */
	protected void applyEffect() {}

}