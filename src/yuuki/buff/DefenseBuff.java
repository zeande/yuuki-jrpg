/**
 * A Buff on the Character's defense. A simple multiplying buff.
 */

package yuuki.buff;

import yuuki.entity.Character;

public class DefenseBuff extends PassiveBuff {

	/**
	 * Creates a new Buff for a Character.
	 *
	 * @param name The display name of this Buff.
	 * @param target The Character that the Buff is on.
	 * @param effect The factor that defense is buffed by.
	 * @param turns The number of turns that this Buff lasts for.
	 */
	public DefenseBuff(String name, double effect, int turns) {
		super(name, effect, turns);
	}
	
	/**
	 * @inheritDoc
	 */
	protected void applyActivationEffect() {
		target.getDefense().addModifier(effect);
	}
	
	/**
	 * @inheritDoc
	 */
	protected void applyDeactivationEffect() {
		target.getDefense().removeModifier(effect));
	}

}