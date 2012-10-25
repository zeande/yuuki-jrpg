/**
 * A Buff that temporarily modifies a single stat.
 */

package yuuki.buff;

import yuuki.entity.Character;

public abstract class PassiveBuff extends Buff {

	/**
	 * Whether this buff has been activated yet.
	 */
	private boolean active;

	/**
	 * Creates a new Buff for a Character.
	 *
	 * @param name The display name of this Buff.
	 * @param effect The amount of effect that this Buff has.
	 * @param turns The number of turns that this Buff lasts for.
	 */
	public PassiveBuff(String name, double effect, int turns) {
		super(name, effect, turns);
		active = false;
	}
	
	/**
	 * Applies this Buff to the target if it hasn't already been activated.
	 */
	public boolean apply() {
		if (!isActive()) {
			activate();
		}
		turnsLeft--;
		return (turnsLeft > 0);
	}
	
	/**
	 * Activates this Buff on a stat.
	 */
	private void activate() {
		active = true;
		addModifier();
	}
	
	/**
	 * Deactivates this Buff from a stat.
	 */
	private void deactivate() {
		active = false;
		removeModifier();
	}
	
	/**
	 * Puts the effect modifier on a stat.
	 */
	protected abstract void addModifier();
	
	/**
	 * Takes the effect off of a stat.
	 */
	protected abstract void removeModifier();
	
	/**
	 * Checks whether this buff has been activated.
	 *
	 * @return True if this Buff has been activated; false otherwise.
	 */
	public boolean isActive() {
		return active;
	}

}