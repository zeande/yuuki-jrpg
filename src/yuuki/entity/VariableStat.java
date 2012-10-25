/**
 * A Stat that has a variable current value. The total effective value is used
 * as the maximum value for the current value.
 */

package yuuki.entity;

public class VariableStat extends Stat {

	/**
	 * The current value of this Stat.
	 */
	private int currentValue;
	
	/**
	 * Allocates a new VariableStat. The current value is immediately set to
	 * the effective level.
	 *
	 * @param character The Character this VariableStat is attached to.
	 * @param base The base value of this VariableStat.
	 * @param gain The amount this VariableStat gains every level.
	 */
	public VariableStat(Character character, int base, int gain) {
		super(character, base, gain);
		restore();
	}
	
	/**
	 * @inheritDoc
	 *
	 * When a modifier is added to a VariableStat, the current value also
	 * changes so that it is at the same percentage of effective as it was
	 * before the modifier was applied. It is rounded to the nearest integer,
	 * but it will always be at least 1.
	 *
	 * @param mod The amount of the modifier to add.
	 */
	@Override
	public void addModifier(int mod) {
		double percent = currentValue / getEffective();
		super.addModifier(mod);
		currentValue = (int) Math.round(getEffective() * percent);
		currentValue = (currentValue >= 1) ? currentValue : 1;
	}
	
	/**
	 * @inheritDoc
	 *
	 * When a modifier is removed from a VariableStat, the current value also
	 * changes so that it is at the same percentage of effective as it was
	 * before the modifier was removed. It is rounded to the nearest integer,
	 * but it will always be at least 1.
	 */
	@Override
	public void removeModifier(int mod) {
		addModifier(-mod);
	}
	
	/**
	 * Checks whether or not this Stat has a current value that varies. Derived
	 * classes may override.
	 *
	 * @return true.
	 */
	@Override
	public boolean hasCurrentValue() {
		return true;
	}
	
	/**
	 * Gets the current value of this Stat.
	 *
	 * @return The current value.
	 */
	public int getCurrent() {
		return currentValue;
	}
	
	/**
	 * Increases the current value of this Stat. The current value will not be
	 * increased past the effective value. If the amount given is greater than
	 * the difference between the effective value and the current value, the
	 * current value is increased only to the effective value.
	 *
	 * @param amount The amount to increase the current value by.
	 *
	 * @return True if the current value increased by the full amount given;
	 * otherwise, false.
	 */
	public boolean gain(int amount) {
		int maxAmount = getEffective() - currentValue;
		boolean amountInBounds = (amount <= maxAmount);
		currentValue += (amountInBounds ? amount : maxAmount);
		return amountInBounds;
	}
	
	/**
	 * Decreases the current value of this Stat. The current value will not be
	 * decreased to a value lower than 0. If the amount given is greater than
	 * the current value, the current value is decreased only to 0.
	 *
	 * @param amount The amount to decrease the current value by.
	 *
	 * @return True if the current value decreased by the full amount given;
	 * otherwise, false.
	 */
	public boolean lose(int amount) {
		boolean amountInBounds = (amount <= currentValue);
		currentValue -= (amountInBounds ? amount : currentValue);
		return amountInBounds;
	}
	
	/**
	 * Sets the current value to the effective level.
	 */
	public void restore() {
		currentValue = getEffective();
	}
	
	/**
	 * Sets the current value to 0.
	 */
	public void drain() {
		currentValue = 0;
	}
	
	/**
	 * Gets the maximum value for this VariableStat.
	 *
	 * @return The effective value.
	 */
	public int getMax() {
		return getEffective();
	}
}