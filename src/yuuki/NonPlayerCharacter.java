/**
 * The NPC class provides methods for battle AI and leveling up for non-player
 * characters, such as team mates and monsters.
 */

package yuuki;

public class NonPlayerCharacter extends Character {

	/**
	 * Used in calculating experience given on death.
	 */
	protected static final int DEATH_XP_BASE = 2;

	/**
	 * Used to calculate expereince given on death.
	 */
	private int xpBase;

	/**
	 * Allocates a new NonPlayerCharacter.
	 *
	 * @inheritDoc
	 *
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
	 * @param xpBase Used for calculating given XP on death.
	 */
	public Character(int level, int hp, int hpGain, int mp, int mpGain,
					 int strength, int strengthGain, int defense,
					 int defenseGain, int agility, int agilityGain,
					 int accuracy, int accuracyGain, int magic, int magicGain,
					 int luck, int luckGain, int xpBase) {
		super(level, hp, hpGain, mp, mpGain, strength, strengthGain, defense,
				defenseGain, agility, agilityGain, accuracy, accuracyGain,
				magic, magicGain, luck, luckGain);
		this.xpBase = xpBase;
	}
	
	/**
	 * Gets the total experience given up by this NPC on death.
	 *
	 * @return The given experience.
	 */
	public int getDeathXP() {
		double power = Math.pow(DEATH_XP_BASE, level);
		return (int) Math.floor(xpBase * level * power);
	}
	
	/**
	 * Levels up this NPC. The base stat increases are automatically calculated.
	 *
	 * @param points The number of stat points that can be allocated.
	 */
	@Override
	public void levelUp(int points) {
		int hp, mp, str, def, agt, acc, mag, luck;
		hp = mp = str = def = agt = acc = mag = luck = 0;
		// randomly add stats for now
		for (int i = 0; i < points; i++) {
			int stat = (int) Math.floor(Math.random() * 8);
			switch (stat) {
			case 0:
				hp++;
				break;
				
			case 1:
				mp++;
				break;
				
			case 2:
				str++;
				break;
				
			case 3:
				def++;
				break;
				
			case 4:
				agt++;
				break;
				
			case 5:
				acc++;
				break;
				
			case 6:
				mag++;
				break;
				
			case 7:
				luck++;
				break;
			}
			levelUp(hp, mp, str, def, agt, acc, mag, luck);
		}
	}
}