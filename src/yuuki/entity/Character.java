/**
 * The Character class provides stats for all characters and monsters.
 */

package yuuki.entity;

import java.util.ArrayList;
import java.util.Iterator;

import yuuki.action.Action;
import yuuki.buff.Buff;

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
	 * The moves that this Character can perform.
	 */
	protected Action[] moves;
	
	/**
	 * The experience of this Character.
	 */
	protected int xp;

	/**
	 * The level of this Character.
	 */
	protected int level;
	
	/**
	 * The Name of the Character.
	 */
	private String name;	

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
	 * The Buffs that this Character has on it.
	 */
	private ArrayList<Buff> buffs;
	
	/**
	 * The ID of this Character within its team during a battle.
	 */
	private int fighterId;
	
	/**
	 * The ID of this Character's team during a battle.
	 */
	private int teamId;
	
	/**
	 * Calculates the experience required to be at a level.
	 *
	 * @param level The level to get the required experience for.
	 *
	 * @return The experience required to be the given level.
	 */
	public static final int getRequiredXP(int level) {
		if (level == 1) {
			return 0;
		} else {
			double power = Math.pow(XP_BASE, level - 1);
			return (int) Math.floor(XP_MULTIPLIER * power);
		}
	}

	/**
	 * Allocates a new Character. Most stats are set manually, but experience
	 * is automatically calculated from the starting level. All stats are the
	 * base stats; all actual stats are calculated by multiplying the stat gain
	 * by the level and adding the base stat.
	 *
	 * @param name The name of the Character.
	 * @param level The level of the new Character. XP is set to match this.
	 * @param moves The moves that this Character knows.
	 * @param hp The health stat of the new Character.
	 * @param mp The mana stat of the new Character.
	 * @param strength The physical strength of the Character.
	 * @param defense The Character's resistance to damage.
	 * @param agility The Character's avoidance of hits.
	 * @param accuracy The Character's ability to hit.
	 * @param magic The magical ability of the Character.
	 * @param luck The ability of the Character to get a critical hit.
	 */
	public Character(String name, int level, Action[] moves, VariableStat hp,
					VariableStat mp, Stat strength, Stat defense, Stat agility,
					Stat accuracy, Stat magic, Stat luck) {
		if (level < 1) {
			throw new IllegalArgumentException("Character level too low.");
		}
		this.name = name;
		this.level = level;
		this.moves = moves;
		this.hp = hp;
		this.mp = mp;
		this.strength = strength;
		this.defense = defense;
		this.accuracy = accuracy;
		this.agility = agility;
		this.magic = magic;
		this.luck = luck;
		this.xp = Character.getRequiredXP(level);
		this.fighterId = -1;
		this.teamId = -1;
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
		int required = Character.getRequiredXP(level + 1);
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
		this.strength.increaseBase(str);
		this.defense.increaseBase(def);
		this.agility.increaseBase(agt);
		this.accuracy.increaseBase(acc);
		this.magic.increaseBase(mag);
		this.luck.increaseBase(luck);
		this.hp.restore(level);
		this.mp.restore(level);
	}
	
	/**
	 * Checks whether this Character is alive.
	 *
	 * @return True if this Character is alive; otherwise, false.
	 */
	public boolean isAlive() {
		return (hp.getCurrent() >= 1);
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
	 * Gets the HP stat of this Character.
	 *
	 * @return The HP stat.
	 */
	public VariableStat getHP() {
		return hp;
	}

	/**
	 * Gets the MP stat of this Character.
	 *
	 * @return The MP stat.
	 */
	public VariableStat getMP() {
		return mp;
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
	 * Gets the strength Stat of this Character.
	 *
	 * @return The strength Stat.
	 */
	public Stat getStrength() {
		return strength;
	}

	/**
	 * Gets the defense Stat of this Character.
	 *
	 * @return The defense Stat.
	 */
	public Stat getDefense() {
		return defense;
	}

	/**
	 * Gets the magic Stat of this Character.
	 *
	 * @return The magic Stat.
	 */
	public Stat getMagic() {
		return magic;
	}

	/**
	 * Gets the agility Stat of this Character.
	 *
	 * @return The agility Stat.
	 */
	public Stat getAgility() {
		return agility;
	}

	/**
	 * Gets the accuracy Stat of this Character.
	 *
	 * @return The accuracy Stat.
	 */
	public Stat getAccuracy() {
		return accuracy;
	}

	/**
	 * Gets the luck Stat of this Character.
	 *
	 * @return The luck Stat.
	 */
	public Stat getLuck() {
		return luck;
	}
	
	/**
	 * Gets the Buffs currently on this Character.
	 *
	 * @return The Buffs.
	 */
	public ArrayList<Buff> getBuffs() {
		return buffs;
	}
	
	/**
	 * Gets the fighter ID of this Character.
	 *
	 * @return The ID if this Character is in a Battle, otherwise -1.
	 */
	public int getFighterId() {
		return fighterId;
	}
	
	/**
	 * Gets the team ID of this Character.
	 *
	 * @return The ID if this Character is in a Battle, otherwise -1.
	 */
	public int getTeamId() {
		return teamId;
	}
	
	/**
	 * Puts a buff on this Character.
	 *
	 * @param b The buff to add.
	 */
	public void addBuff(Buff b) {
		b.setTarget(this);
		buffs.add(b);
	}
	
	/**
	 * Removes buffs that are no longer active.
	 */
	public void removeExpiredBuffs() {
		Iterator<Buff> it = buffs.iterator();
		while (it.hasNext()) {
			Buff b = it.next();
			if (b.isExpired()) {
				it.remove();
			}
		}
	}
	
	/**
	 * Applies all current buffs to this Character.
	 */
	public void applyBuffs() {
		for (Buff b: buffs) {
			b.apply();
		}
	}
	
	/**
	 * Decides what move to do next based on the states of other players in the
	 * battle.
	 *
	 * @param fighters The states of the other players.
	 *
	 * @return The move that this Character wishes to perform.
	 */
	public Action getNextAction(ArrayList<ArrayList<Character>> fighters) {
		// TODO: Make intelligent choices based on the battle state
		int choice = (int) Math.floor(Math.random() * moves.length);
		return moves[choice];
	}
	
	/**
	 * Gets the moves that this Character knows.
	 *
	 * @return The moves.
	 */
	public Action[] getMoves() {
		return moves;
	}
	
	/**
	 * Sets up the properties needed for fighting.
	 *
	 * @param id The fighter ID of this Character in the battle.
	 * @param team The team ID of this Character in the battle.
	 */
	public void startFighting(int id, int team) {
		fighterId = id;
		teamId = team;
		this.buffs = new ArrayList<Buff>();
	}
	
	/**
	 * Resets the propterties needed for fighting to their default values.
	 */
	public void stopFighting() {
		fighterId = -1;
		teamId = -1;
		this.buffs = null;
	}
	
	/**
	 * Sets team Id.
	 *
	 * @param Id The new team ID.
	 */
	public void setTeamId(int id) {
		teamId = id;
	}
	
	/**
	 * Sets fighter Id.
	 *
	 * @param Id The new fighter ID.
	 */
	public void setFighterId(int id) {
		fighterId = id;
	}
}
