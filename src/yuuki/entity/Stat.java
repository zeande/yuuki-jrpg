/**
 * A stat used by a Character.
 */

package yuuki.entity;

public class Stat {

	/**
	 * The Character that this Stat is attached to. This is necessary due to
	 * Stat's dependance on level.
	 */
	private Character character;
	
	/**
	 * The base value for calculating this Stat's effective value.
	 */
	private int base;
	
	/**
	 * The amount of this stat that is gained with every level.
	 */
	private int gain;
	
	/**
	 * How much to modify the effective value by.
	 */
	private int modifier;
	
	/**
	 * Allocates a new Stat.
	 *
	 * @param Character The Character that this Stat is coupled with.
	 * @param base The base value of the Stat.
	 * @param gain The amount this Stat gains every level.
	 */
	public Stat(Character character, int base, int gain) {
		this.character = character;
		this.base = base;
		this.gain = gain;
		this.modifier = 0;
	}
	
	/**
	 * Checks whether or not this Stat has a current value that varies. Derived
	 * classes may override.
	 *
	 * @return false.
	 */
	public boolean hasCurrentValue() {
		return false;
	}
	
	/**
	 * Adds a modifier to this Stat. Modifiers change the final effective
	 * value.
	 *
	 * @param mod The amount of the modifier to add.
	 */
	public void addModifier(double mod) {
		modifier += mod;
	}
	
	/**
	 * Removes a modifier from this Stat. This method has the exact same effect
	 * as addModifier(-mod).
	 *
	 * @param mod The amount of the modifier to remove.
	 */
	public void removeModifier(double mod) {
		addModifier(-mod);
	}
	
	
	/**
	 * Gets this Stat's base value.
	 *
	 * @return The base value.
	 */
	public int getBaseValue() {
		return base;
	}
	
	/**
	 * Increases this Stat's base value.
	 *
	 * @param amount The amount to increase the base value by.
	 */
	public void increaseBase(int amount) {
		base += amount;
	}
	
	/**
	 * Gets the total calculated value of this Stat given a level.
	 *
	 * @param level The level of the Character that the Stat is being
	 * calculated for.
	 *
	 * @return The effective value of this Stat for the given level.
	 */
	public int getEffective(int level) {
		int effective = base + (gain * level) * modifier;
		return effective;
	}
	
	/**
	 * Gets the total calcluated value of this Stat given the level of the
	 * Character it is attached to.
	 *
	 * @return The effective value of this Stat for the Character it is
	 * attached to.
	 */
	public int getEffective() {
		return getEffective(character.getLevel());
	}
	
	/**
	 * Gets the amount gained per level.
	 *
	 * @return Amount gained per level.
	 */
	public int getLevelGain() {
		return gain;
	}
}