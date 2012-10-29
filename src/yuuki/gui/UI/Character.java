/**
 * The Character class provides stats for all characters and monsters.
 */

package yuuki;

public class Character {

	/**
	 * The multiplier used in calculating required experience.
	 */
	protected static final int XP_MULTIPLIER = 50;
	
	/**
	 * The base used in calculating required experience.
	 */
	protected static final double XP_BASE = 2.0;
        
        /**
         * The Name of the Character.
         */
        protected String name;
	
	/**
	 * The experience of this Character.
	 */
	protected int xp;

	/**
	 * The level of this Character.
	 */
	protected int level;

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
	 * 
	 */
	
	/**
	 * Allocates a new Character. Most stats are set manually, but experience
	 * is automatically calculated from the starting level. All stats are the
	 * base stats; all actual stats are calculated by multiplying the stat gain
	 * by the level and adding the base stat.
	 *
         * @param name The name of the Character.
	 * @param level The level of the new Character. XP is set to match this.
	 * @param hp The hit points of the new Character.
	 * @param hpGain The number of hit points gained per level.
	 * @param mp The mana points of the new Character.
	 * @param mpGain The number of mana points gained per level.
	 * @param strength The physical strength of the Character.
	 * @param strengthGain The amount of strength gained per level.
	 * @param defense The Character's resistance to damage.
	 * @param defenseGain The amount of defense gained per level.
	 * @param agility The Character's avoidance to hits.
	 * @param agilityGain The amount of agility gained per level.
	 * @param accuracy The Character's ability to hit.
	 * @param accuracyGain The amount of accuracy gained per level.
	 * @param magic The magical ability of the Character.
	 * @param magicGain The amount of magic gained per level.
	 * @param luck The ability of the Character to get a critical hit.
	 * @param luckGain The amount of luck gained per level.
	 */
	public Character(String name, int level, int hp, int hpGain, int mp, int mpGain,
					 int strength, int strengthGain, int defense,
					 int defenseGain, int agility, int agilityGain,
					 int accuracy, int accuracyGain, int magic, int magicGain,
					 int luck, int luckGain) {
		if (level < 1) {
			throw new IllegalArgumentException("Character level too low.");
		}
                this.name = name;
		this.level = level;
		this.hpMax = this.hp = hp;
		this.hpGain = hpGain;
		this.mpMax = this.mp = mp;
		this.mpGain = mpGain;
		this.strength = strength;
		this.strengthGain = strengthGain;
		this.defense = defense;
		this.defenseGain = defenseGain;
		this.accuracy = accuracy;
		this.accuracyGain = accuracyGain;
		this.agility = agility;
		this.agilityGain = agilityGain;
		this.magic = magic;
		this.magicGain = magicGain;
		this.luck = luck;
		this.luckGain = luckGain;
		this.xp = getRequiredXP(level);
	}
	
	/**
	 * Adds experience points to this Character. Adding enough XP to level up
	 * this Character will not make it level up, but it will make the
	 * canLevelUp method return true. That method should be checked after
	 * adding experience to determine whether levelUp should then be called.
	 *
	 * @param amount
	 * The amount of XP to add.
	 */
	public void addXP(int amount) {
		xp += amount;
	}
	
	/**
	 * Checks whether this Character has enough experience points to advance to
	 * the next level.
	 *
	 * @return True if the Character has enough XP to level up; otherwise
	 * false.
	 */
	public boolean canLevelUp() {
		int required = getRequiredXP(level + 1);
		return (xp >= required);
	}
	
	/**
	 * Levels up this Character. The level is increased by one and base stats
	 * are increased by the given amounts. The current HP and MP are reset to
	 * their maximum value.
	 *
	 * @param hp The amount to increase base HP by.
	 * @param mp The amount to increase base MP by.
	 * @param str The amount to increase base strength by.
	 * @param def The amount to increase base defense by.
	 * @param agt The amount to increase base agility by.
	 * @param acc The amount to increase base accuracy by.
	 * @param mag The amount to increase base magic by.
	 * @param luck The amount to increase base luck by.
	 */
	public void levelUp(int hp, int mp, int str, int def, int agt, int acc,
						 int mag, int luck) {
		level++;
		this.hpMax += hp;
		this.mpMax += mp;
		this.strength += str;
		this.defense += def;
		this.agility += agt;
		this.accuracy += acc;
		this.magic += mag;
		this.luck += luck;
		restoreHP();
		restoreMP();
	}
	
	/**
	 * Checks whether this Character is alive.
	 *
	 * @return True if this Character is alive; otherwise, false.
	 */
	public boolean isAlive() {
		return hp >= 1;
	}
	
	/**
	 * Restores this Character's health completely.
	 */
	public void restoreHP() {
		hp = hpMax;
	}
	
	/**
	 * Restores this Character's mana completely.
	 */
	public void restoreMP() {
		mp = mpMax;
	}
	
	/**
	 * Calculates the experience required to be at a level.
	 *
	 * @param level The level to get the required experience for.
	 *
	 * @return The experience required to be the given level.
	 */
	public final int getRequiredXP(int level) {
		if (level == 1) {
			return 0;
		} else {
			double power = Math.pow(XP_BASE, level - 2);
			return (int) Math.floor(XP_MULTIPLIER * power);
		}
	}

        /**
         * Gets the name of this Character.
         * 
         * @return The name of the Character.
         */
        public String getName() {
                return name;
        }
	/**
	 * Gets the level of this Character.
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
	 * Sets the current MP of this Character.
	 *
	 * @param mp The amount to set the MP to.
	 */
	public void setMP(int mp) {
		this.mp = mp;
	}
	
	/**
	 * Sets the current HP of this Character.
	 *
	 * @param hp The amount to set the HP to.
	 */
	public void setHP(int hp) {
		this.hp = hp;
	}
	
	/**
	 * Gets this Character's experience points.
	 *
	 * @return The number of experience points of this Character.
	 */
	public int getXP() {
		return xp;
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
