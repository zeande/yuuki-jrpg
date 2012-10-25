/**
 * The Character class provides stats for all characters and monsters.
 */

package yuuki.entity;

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
	private String name;
	
	/**
	 * The experience of this Character.
	 */
	protected int xp;

	/**
	 * The level of this Character.
	 */
	protected int level;

	/**
	 * The hit point stat.
	 */
	private VariableStat hp;

	/**
	 * The mana points stat.
	 */
	private VariableStat mp;

	/**
	 * The physical damage dealt by the character.
	 */
	private Stat strength;

	/**
	 * Modifies damage taken.
	 */
	private Stat defense;

	/**
	 * The magical damage dealt by the character.
	 */
	private Stat magic;

	/**
	 * Modifies percent chance to avoid damage.
	 */
	private Stat agility;

	/**
	 * Modifies percent chance to hit.
	 */
	private Stat accuracy;

	/**
	 * Modifies critical strike percent.
	 */
	private Stat luck;

	/**
	 * Allocates a new Character. Most stats are set manually, but experience
	 * is automatically calculated from the starting level. All stats are the
	 * base stats; all actual stats are calculated by multiplying the stat gain
	 * by the level and adding the base stat.
	 *
	 * @param name The name of the Character.
	 * @param level The level of the new Character. XP is set to match this.
	 * @param hp The health stat of the new Character.
	 * @param mp The mana stat of the new Character.
	 * @param strength The physical strength of the Character.
	 * @param defense The Character's resistance to damage.
	 * @param agility The Character's avoidance of hits.
	 * @param accuracy The Character's ability to hit.
	 * @param magic The magical ability of the Character.
	 * @param luck The ability of the Character to get a critical hit.
	 */
	public Character(String name, int level, VariableStat hp, VariableStat mp,
					Stat strength, Stat defense, Stat agility, Stat accuracy,
					Stat magic, Stat luck) {
		if (level < 1) {
			throw new IllegalArgumentException("Character level too low.");
		}
		this.level = level;
		this.hp = hp;
		this.mp = mp;
		this.strength = strength;
		this.defense = defense;
		this.accuracy = accuracy;
		this.agility = agility;
		this.magic = magic;
		this.luck = luck;
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
		this.hp.increaseBase(hp);
		this.mp.increaseBase(mp);
		strength.increaseBase(str);
		defense.increaseBase(def);
		agility.increaseBase(agt);
		accuracy.increaseBase(acc);
		magic.increaseBase(mag);
		this.luck.increaseBase(luck);
		hp.restore();
		mp.restore();
	}
	
	/**
	 * Checks whether this Character is alive.
	 *
	 * @return True if this Character is alive; otherwise, false.
	 */
	public boolean isAlive() {
		return hp.getCurrent() >= 1;
	}
	
	/**
	 * Restores this Character's health completely.
	 */
	public void restoreHP() {
		hp.restore();
	}
	
	/**
	 * Restores this Character's mana completely.
	 */
	public void restoreMP() {
		mp.restore();
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
		return hp.getMax();
	}

	/**
	 * Gets the current HP of this Character.
	 *
	 * @return The current HP.
	 */
	public int getHP() {
		return hp.getCurrent();
	}

	/**
	 * Gets the max MP of this Character.
	 *
	 * @return The maximum MP of this Character.
	 */
	public int getMaxMP() {
		return mp.getMax();
	}
	
	/**
	 * Gets the current MP of this Character.
	 *
	 * @return The current MP of this Character.
	 */
	public int getMP() {
		return mp.getCurrent();
	}
	
	/**
	 * Increases the current MP of this Character.
	 *
	 * @param mp The amount to increase by.
	 */
	public void gainMP(int mp) {
		this.mp.gain(mp);
	}
	
	/**
	 * Increases the current HP of this Character.
	 *
	 * @param hp The amount to increase by.
	 */
	public void gainHP(int hp) {
		this.hp.gain(hp);
	}
	
	/**
	 * Decreases the current MP of this Character.
	 *
	 * @param mp The amount to decrease by.
	 */
	public void loseMP(int mp) {
		this.mp.lose(mp);
	}
	
	/**
	 * Decreases the current HP of this Character.
	 *
	 * @param hp The amount to decrease by.
	 */
	public void loseHP(int hp) {
		this.hp.lose(hp);
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
		return strength.getEffective();
	}

	/**
	 * Gets the defense of this Character.
	 *
	 * @return The total defense of this Character.
	 */
	public int getDefense() {
		return defense.getEffective();
	}

	/**
	 * Gets the magic stat of this Character.
	 *
	 * @return The total magic stat of this Character.
	 */
	public int getMagic() {
		return magic.getEffective();
	}

	/**
	 * Gets the agility of this Character.
	 *
	 * @return The total agility of this Character.
	 */
	public int getAgility() {
		return agility.getEffective();
	}

	/**
	 * Gets the accuracy of this Character.
	 *
	 * @return The total accuracy of this Character.
	 */
	public int getAccuracy() {
		return accuracy.getEffective();
	}

	/**
	 * Gets the luck of this Character.
	 *
	 * @return The total luck of this Character.
	 */
	public int getLuck() {
		return luck.getEffective();
	}
}
