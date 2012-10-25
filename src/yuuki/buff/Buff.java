/**
 * An effect that either boosts or reduces stats. Effects can either be passive
 * or active. Passive effects are applied during stat calculation, while active
 * effects are applied every time a player's turn comes up.
 */

package yuuki.buff;

import yuuki.entity.Character;

public abstract class Buff {

	/**
	 * The amount of effect that this buff applies. This could be a multiplier
	 * or a discrete number.
	 */
	protected double effect;
	
	/**
	 * The Character that this Buff applies its effect to.
	 */
	protected Character target;
	
	/**
	 * The number of turns remaining that this Buff can be applied for.
	 */
	protected int turnsLeft;
	
	/**
	 * The name of this Buff. Used for display purposes.
	 */
	private String name;
	
	/**
	 * Creates a new Buff for a Character.
	 *
	 * @param name The display name of this Buff.
	 * @param effect The amount of effect that this Buff has.
	 * @param turns The number of turns that this Buff lasts for.
	 */
	public Buff(String name, double effect, int turns) {
		this.name = name;
		this.effect = effect;
		this.turnsLeft = turns;
	}
	
	/**
	 * Sets the target.
	 */
	public void setTarget(Character target) {
		this.target = target;
	}
	
	/**
	 * Applies this buff's effect to its target Character. This must not be
	 * used for a passive buff; that must be externally obtained through
	 * getEffect().
	 *
	 * @return True if this Buff has more turns left to apply; otherwise false.
	 */
	public abstract boolean apply();
	
	/**
	 * Gets the amount of effect that this Buff has.
	 *
	 * @return The amount of effect.
	 */
	public double getEffect() {
		return effect;
	}
	
	/**
	 * Checks how many turns are left.
	 *
	 * @return The number of turns remaining for this Buff.
	 */
	public int getTurns() {
		return turnsLeft;
	}
	
	/**
	 * Checks whether there are any turns left.
	 *
	 * @return True if the number of turns left is greater than 0; otherwise,
	 * false.
	 */
	public boolean hasTurns() {
		return (getTurnsLeft() > 0);
	}
	
	/**
	 * Gets this Buff's name.
	 *
	 * @return This Buff's name.
	 */
	public String getName() {
		return name;
	}

}