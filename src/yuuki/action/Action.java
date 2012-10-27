/**
 * A character's action during the fight. This gives information on what type
 * of effect it has and who its target is.
 */

package yuuki.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import yuuki.entity.Character;
import yuuki.buff.Buff;

public abstract class Action {

	/**
	 * The Buff that is applied to the target.
	 */
	protected Buff targetBuff;
	
	/**
	 * The Buff that is applied to the origin.
	 */
	protected Buff originBuff;
	
	/**
	 * Where this Action came from; who is doing the Action.
	 */
	protected Character performer;
	
	/**
	 * The targets to whom the Action is to be applied.
	 */
	protected ArrayList<Character> targets;
	
	/**
	 * The amount of effect of this Action.
	 */
	protected double effect;
	
	/**
	 * The amount of cost of this Action.
	 */
	protected double cost;
	
	/**
	 * Whether the last application of this Action was successful.
	 */
	protected boolean successful;
	
	/**
	 * The name of this Action; used for display purposes.
	 */
	private String name;
	
	/**
	 * Creates a new Action.
	 *
	 * @param name The display name of this Action.
	 * @param effect The amount of effect that the new Action is to have.
	 * @param cost The amount of cost that the new Action will take.
	 * @param targetBuff The buff that is applied on application.
	 * @param originBuff The buff that is applied to the origin.
	 */
	public Action(String name, double effect, double cost, Buff targetBuff,
					Buff originBuff) {
		this.name = name;
		this.effect = effect;
		this.cost = cost;
		this.targetBuff = targetBuff;
		this.originBuff = originBuff;
		targets = new ArrayList<Target>();
		origin = null;
	}
	
	/**
	 * Applies this Action to targets. The cost is taken from the origin and
	 * the effects are attempted to be put on the targets. If the origin cannot
	 * supply the cost, the application will fail. No effects are put on the
	 * targets and this method returns false.
	 *
	 * Note that there are multiple situations that could cause this method to
	 * return false; the origin could lack the required stat to perform the
	 * Action, or it could have failed some other check. Returning false simply
	 * indicates that no effects were attempted to be put on the targets.
	 *
	 * Also note that returning true does not necessarily mean that the targets
	 * were affected; they could have blocked the effects. The return value
	 * only indicates whether effects were attempted to be put onto the
	 * targets, not whether it was successful.
	 *
	 * @return True if the effects were attempted to be put on the targets;
	 * otherwise, false.
	 */
	public boolean apply() {
		successful = applyCost();
		if (successful) {
			applyEffects();
			applyBuffs();
		}
		return successful;
	}
	
	/**
	 * Applies the Buffs.
	 */
	private abstract void applyBuffs();
	
	/**
	 * Applies the cost to the origin.
	 *
	 * @return True if the cost was successfully applied; otherwise, false.
	 */
	protected abstract boolean applyCost();
	
	/**
	 * Applies the effects to the targets.
	 */
	protected abstract void applyEffects();
	
	/**
	 * Gets the name of this Action.
	 *
	 * @return This Action's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the effect of this Action.
	 *
	 * @return The effect value of this Action.
	 */
	public double getEffect() {
		return effect;
	}
	
	/**
	 * Gets the cost of this Action.
	 *
	 * @return The amount that this Action costs.
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * Sets the performer of this Action.
	 *
	 * @param performer The Character performing the action.
	 */
	public void setOrigin(Character performer) {
		origin = performer;
	}
	
	/**
	 * Gets the performer of this Action.
	 *
	 * @return The Character performing this Action if origin was set;
	 * otherwise, null.
	 */
	public Character getOrigin() {
		return origin;
	}
	
	/**
	 * Sets the target and the target team.
	 *
	 * @param fighter The Character controlling the fighter.
	 * @param fighterId The ID of the fighter to target.
	 * @param teamId The ID of the fighter's team.
	 */
	public void addTarget(Character character, int fighterId, int teamId) {
		Target t = new Target();
		t.id = fighterId;
		t.team = teamId;
		targets.add(t);
	}
	
	/**
	 * Gets the targeted fighters.
	 *
	 * @return An array containing the targets, which will be empty if no
	 * targets have been set.
	 */
	public Character[] getTargets() {
		Character[] targets = this.targets.toArray(new Character[0]);
		return targets;
	}
	
	/**
	 * Gets the targeted fighters' team' IDs.
	 *
	 * @return An array containing the teams with Characters who are targets,
	 * which will be empty if no targets have been set.
	 */
	public int[] getAffectedTeams() {
		Set<Integer> affected = new HashSet<Integer>();
		for (Character c: targets) {
			affected.add(c.getTeam());
		}
		int[] teams = new int[affected.size()];
		int k = 0;
		for (Integer i: affected) {
			teams[k++] = i.intValue();
		}
		return teams;
	}
	
	/**
	 * Checks whether the last application of this Action was successful.
	 *
	 * @return True if it was successful; false if it was not.
	 */
	public boolean wasSuccessful() {
		return success;
	}

}