/**
 * The Character class provides stats for all characters and monsters.
 */

package yuuki;

public class Character {
	
	/**
	 * The experience of this Character.
	 */
	private int xp;

	/**
	 * The level of this Character.
	 */
	private int level;

	/**
	 * The maximum possible hit points.
	 */
	private int hpMax;

	/**
	 * The amount of HP gained per level.
	 */
	private int hpGain;

	/**
	 * The current number of hit points.
	 */
	private int hp;

	/**
	 * The maximum possible mana points.
	 */
	private int mpMax;

	/**
	 * The amount of MP gain per level.
	 */
	private int mpGain;

	/**
	 * The current number of mana points.
	 */
	private int mp;

	/**
	 * The physical damage dealt by the character. This is the user-set
	 * skill point value.
	 */
	private int strength;

	/**
	 * Amount of strength gained per level.
	 */
	private int strengthGain;

	/**
	 * Modifies damage taken. This is the user-set skill point value.
	 */
	private int defense;

	/**
	 * Amount of defense gained per level.
	 */
	private int defenseGain;

	/**
	 * The magical damage dealt by the character. This is the user-set
	 * skill point value.
	 */
	private int magic;

	/**
	 * The amount of magic gained per level.
	 */
	private int magicGain;

	/**
	 * Modifies percent chance to be hit. This is the user-set skill point
	 * value.
	 */
	private int agility;

	/**
	 * The amount of agility gained per level.
	 */
	private int agilityGain;

	/**
	 * Modifies percent chance to hit. This is the user-set skill point
	 * value.
	 */
	private int accuracy;

	/**
	 * The amount of accuracy gained per level.
	 */
	private int accuracyGain;

	/**
	 * Increases critical strike percent. This is the user-set skill point
	 * value.
	 */
	private int luck;

	/**
	 * The amount of luck gained per level.
	 */
	private int luckGain;

	/**
	 * Gets the level of this Character. This is calculated directly from
	 * experience points.
	 *
	 * @return The level of this Character.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the max HP of this Character.
	 *
	 * @return The maximum HP of this Character.
	 */
	public int getMaxHP() {
		return hpMax + (hpGain * level);
	}

	/**
	 * Gets the current HP of this Character.
	 *
	 * @return The current HP.
	 */
	public int getHP() {
		return hp;
	}

	/**
	 * Gets the max MP of this Character.
	 *
	 * @return The maximum MP of this Character.
	 */
	public int getMaxMP() {
		return mpMax + (mpGain * level);
	}
	
	/**
	 * Gets the current MP of this Character.
	 *
	 * @return The current MP of this Character.
	 */
	public int getMP() {
		return mp;
	}

	/**
	 * Gets the strength of this Character.
	 *
	 * @return The total strength of this Character.
	 */
	public int getStrength() {
		return strength + (strengthGain * level);
	}

	/**
	 * Gets the defense of this Character.
	 *
	 * @return The total defense of this Character.
	 */
	public int getDefense() {
		return defense + (defenseGain * level);
	}

	/**
	 * Gets the magic stat of this Character.
	 *
	 * @return The total magic stat of this Character.
	 */
	public int getMagic() {
		return magic + (magicGain * level);
	}

	/**
	 * Gets the agility of this Character.
	 *
	 * @return The total agility of this Character.
	 */
	public int getAgility() {
		return agility + (agilityGain * level);
	}

	/**
	 * Gets the accuracy of this Character.
	 *
	 * @return The total accuracy of this Character.
	 */
	public int getAccuracy() {
		return accuracy + (accuracyGain * level);
	}

	/**
	 * Gets the luck of this Character.
	 *
	 * @return The total luck of this Character.
	 */
	public int getLuck() {
		return luck + (luckGain * level);
	}
}
