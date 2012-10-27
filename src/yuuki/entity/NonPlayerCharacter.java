/**
 * The NPC class provides methods for battle AI and leveling up for non-player
 * characters, such as team mates and monsters.
 */

package yuuki.entity;

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
	 * @param hp The health stat of the new Character.
	 * @param mp The mana stat of the new Character.
	 * @param strength The physical strength of the Character.
	 * @param defense The Character's resistance to damage.
	 * @param agility The Character's avoidance to hits.
	 * @param accuracy The Character's ability to hit.
	 * @param magic The magical ability of the Character.
	 * @param luck The ability of the Character to get a critical hit.
	 * @param xpBase Used for calculating given XP on death.
	 */
	public NonPlayerCharacter(int level, VariableStat hp, VariableStat mp,
							Stat strength, Stat defense, Stat agility,
							Stat accuracy, Stat magic, Stat luck, int xpBase) {
		super(level, hp, mp, strength, defense, agility, accuracy, magic,
				luck);
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