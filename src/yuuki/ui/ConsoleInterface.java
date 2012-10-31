/**
 * A very simple interface that only uses stdin and stdout.
 */

package yuuki.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import yuuki.entity.Character;
import yuuki.buff.Buff;
import yuuki.action.Action;

public class ConsoleInterface implements Interactable {

	/**
	 * Reads from stdin.
	 */
	private BufferedReader stdin;
	
	/**
	 * Creates a new ConsoleInterface.
	 */
	public ConsoleInterface() {
		stdinReader = null;
	}
	
	/**
	 * Creates the Reader on stdin.
	 */
	@Override
	public void initialize() {
		stdin = new BufferedReader(new InputStreamReader(System.in));
	}
	
	/**
	 * Closes the stdin Reader.
	 */
	@Override
	public void destroy() {
		try {
			stdin.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Shows the intro message and displays the main menu.
	 */
	@Override
	public void switchToIntroScreen() {
		println("+-------------------------------------+");
		println("|                Yuuki                |");
		println("|                                     |");
		println("|  By python'); DROP TABLE teams; --  |");
		println("+-------------------------------------+");
		println();
		pause();
	}
	
	/**
	 * Shows the options screen. The user is prompted to change options
	 * until he quits out of this screen.
	 */
	@Override
	public void switchToOptionsScreen() {
		String input = null;
		boolean inOptions = true;
		while (inOptions) {
			String[] choices = {"Back to main menu"};
			int input = getChoice("Enter option", choices);
			if (input == 0) {
				inOptions = false;
			}
		}
	}
	
	/**
	 * Displays the battle scene.
	 *
	 * @param fighters The characters who are fighting.
	 */
	@Override
	public void switchToBattleScreen(Character[][] fighters) {
		showBattleIntro(fighters);
		showTeams(fighters);
	}
	
	/**
	 * Displays the stats of a character during a battle.
	 *
	 * @param fighter The Character to update.
	 */
	@Override
	public void showStatUpdate(Character fighter) {
		showStats(fighter);
	}
	
	/**
	 * Shows that damage occured to a character.
	 *
	 * @param fighter The affected character.
	 * @param stat The affected stat
	 * @param damage The amount of damage
	 */
	@Override
	public void showDamage(Character fighter, Stat stat,  int damage) {
		String msg = fighter.getName() + " took " + damage;
		msg += " damage to " + stat.getName();
		println(msg);
		pause();
	}
	
	/**
	 * Shows that damage occured to a character.
	 *
	 * @param fighter The affected character.
	 * @param stat The affected stat
	 * @param damage The amount of damage
	 */
	@Override
	public void showDamage(Character fighter, Stat stat, double damage) {
		String msg = fighter.getName() + " took " + damage;
		msg += " damage to " + stat.getName();
		println(msg);
		pause();
	}
	
	/**
	 * Shows that a character is preparing to use an attack.
	 *
	 * @param action The move used.
	 */
	@Override
	public void showActionPreperation(Action action) {
		String name = action.getOrigin().getName();
		Character[] targets = action.getTargets();
		print(name + " is getting ready to use " + action.getName());
		print(" on");
		for (int i = 0; i < targets.length; i++) {
			print(" " + targets[i]);
			if (i + 1 < targets.length) {
				if (targets.length > 2) {
					print(",");
				}
				if (i + 1 + 1 == targets.length) {
					print(" and");
				}
			}
		}
		pause();
	}
	
	/**
	 * Shows a character using an attack successfully.
	 *
	 * @param action The move used.
	 */
	public void showActionUse(Action action) {
		String name = action.getOrigin().getName();
		println(name + " did it!");
		pause();
	}
	
	/**
	 * Shows a character fail at an attack.
	 *
	 * @param action The move used.
	 */
	public void showActionFailure(Action action) {
		String name = action.getOrigin().getName();
		println(name + " couldn't pull it off.");
		pause();
	}
	
	/**
	 * Shows a buff activating.
	 *
	 * @param buff The buff to show.
	 */
	public void showBuffActivation(Buff buff) {
		Character t = buff.getTarget();
		println(t.getName() + " is now " + buff.getName());
		pause();
	}
	
	/**
	 * Shows a buff being applied.
	 *
	 * @param buff The buff to show.
	 */
	public void showBuffApplication(Buff buff) {
		Character t = buff.getTarget();
		bName = buff.getName();
		println(t.getName() + " is feeling the effects of the " + bName);
		pause();
	}
	
	/**
	 * Shows a buff being deactivated.
	 *
	 * @param buff The buff to show.
	 */
	public void showBuffDeactivation(Buff buff) {
		Character t = buff.getTarget();
		println(t.getName() + " is no longer " + buff.getName());
		pause();
	}
	
	/**
	 * Shows a character being removed from battle.
	 *
	 * @param fighter The character to show.
	 */
	public void showCharacterRemoval(Character fighter) {
		println(fighter.getName() + " is no longer in the battle.");
		pause();
	}
	
	/**
	 * Shows that the specified characters are victorious.
	 *
	 * @param fighters The characters to show as victorious.
	 */
	public void showCharacterVictory(Character[] fighters) {
		print("VICTORY!");
		for (int i = 0; i < fighters.length; i++) {
			print(" " + fighters[i]);
			if (i + 1 < targets.length) {
				if (targets.length > 2) {
					print(",");
				}
				if (i + 1 + 1 == targets.length) {
					print(" and");
				}
			}
		}
		println(" are victorious!");
		pause();
	}
	
	/**
	 * Displays the overworld screen.
	 */
	@Override
	public void switchToOverworldScreen() {
		println("Here, you would see the overworld.");
		println("Right now, there is nothing.");
		println("Skipping to battle...");
		pause();
	}
	
	/**
	 * Displays the pause screen.
	 */
	@Override
	public void switchToPauseScreen() {
		println("Paused the game.");
		pause();
		println("Unpaused the game.");
	}
	
	/**
	 * Displays the ending screen.
	 */
	@Override
	public void switchToEndingScreen() {
		println("The end.");
		println();
		println("Thanks for playing!");
		pause();
	}
	
	/**
	 * Has no effect.
	 */
	@Override
	public void playSound(String path) {}
	
	/**
	 * Displays the message that a Character says.
	 *
	 * @param speaker The speaker of the line. Null for no speaker.
	 * @param message The message to display.
	 */
	@Override
	public void display(Character speaker, String message) {
		String s = "";
		String q = "";
		if (speaker != null) {
			s = speaker.getName().toUpperCase() + ": ";
			q = "\"";
		}
		String line = s + q + message + q;
		println(line);
	}
	
	/**
	 * Gets a String from the user.
	 *
	 * @param prompt The prompt to show the user.
	 *
	 * @return The entered String.
	 */
	@Override
	public String getString(String prompt) {
		print(prompt + ": ");
		String input = null;
		while (input == null) {
			try {
				input = stdin.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return input;
	}
	
	/**
	 * Gets a String from the user.
	 *
	 * @return The entered String.
	 */
	@Override
	public String getString() {
		return getString("");
	}
	
	/**
	 * Gets an int from the user.
	 *
	 * @param prompt The prompt to show the user.
	 *
	 * @return The entered int.
	 */
	@Override
	public int getInt(String prompt) {
		String input = null;
		int intInput = 0;
		boolean inputIsGood = false;
		while (!inputIsGood) {
			input = getString(prompt);
			inputIsGood = true;
			try {
				intInput = Integer.parseInt(input);
			} catch(NumberFormatException e) {
				warn("You must enter an integer!");
				inputIsGood = false;
			}
		}
		return intInput;
	}
	
	/**
	 * Gets an int from the user.
	 *
	 * @return The entered int.
	 */
	@Override
	public int getInt() {
		return getInt("");
	}
	
	/**
	 * Gets an int in a range from the user.
	 *
	 * @param prompt The prompt to show the user.
	 * @param min The minimum that the input can be.
	 * @param max The maximum that the input can be.
	 *
	 * @return A number in the given range.
	 */
	@Override
	public int getInt(String prompt, int min, int max) {
		int input = 0;
		boolean inputIsGood = false;
		while (!inputIsGood) {
			input = getInt(prompt);
			inputIsGood = (input >= min && input <= max);
			if (!inputIsGood) {
				warn("Number must be between " + min + " and " + max + "!");
			}
		}
		return input;
	}
	
	/**
	 * Gets an int in a range from the user.
	 *
	 * @param min The minimum that the input can be.
	 * @param max The maximum that the input can be.
	 *
	 * @return A number in the given range.
	 */
	@Override
	public int getInt(int min, int max) {
		return getInt("", min, max);
	}
	
	/**
	 * Gets a double from the user.
	 *
	 * @param prompt The prompt to show the user.
	 *
	 * @return The entered double.
	 */
	@Override
	public double getDouble(String prompt) {
		String input = null;
		double doubleInput = 0.0;
		boolean inputIsGood = false;
		while (!inputIsGood) {
			input = getString(prompt);
			inputIsGood = true;
			try {
				doubleInput = Double.parseInt(input);
			} catch(NumberFormatException e) {
				warn("You must enter a real number!");
				inputIsGood = false;
			}
		}
		return doubleInput;
	}
	
	/**
	 * Gets a double from the user.
	 *
	 * @return The entered double.
	 */
	@Override
	public double getDouble() {
		return getDouble("");
	}
	
	/**
	 * Gets a double in a range from the user
	 *
	 * @param prompt The prompt to show the user.
	 * @param min The minimum that the input can be.
	 * @param max The maximum that the input can be.
	 *
	 * @return A number in the given range.
	 */
	@Override
	public int getDouble(String prompt, double min, double max) {
		double input = 0.0;
		boolean inputIsGood = false;
		while (!inputIsGood) {
			input = getDouble(prompt);
			inputIsGood = (input >= min && input <= max);
			if (!inputIsGood) {
				warn("Number must be between " + min + " and " + max + "!");
			}
		}
		return input;
	}
	
	/**
	 * Gets a double in a range from the user
	 *
	 * @param min The minimum that the input can be.
	 * @param max The maximum that the input can be.
	 *
	 * @return A number in the given range.
	 */
	@Override
	public double getDouble(double min, double max) {
		return getDouble("", min, max);
	}
	
	/**
	 * Gets a choice from the user. The choice may be one of the given Strings
	 * in the array.
	 *
	 * @param prompt The prompt to show the user.
	 * @param options The Strings from which the user must choose.
	 *
	 * @return The index of the user's choice.
	 */
	@Override
	public int getChoice(String prompt, String[] options) {
		int choice = 0;
		String builtPrompt = "";
		for (int i = 0; i < options.length; i++) {
			builtPrompt += i;
			builtPrompt += " - ";
			builtPrompt += options[i];
			builtPrompt += '\n';
		}
		builtPrompt += prompt;
		choice = getInt(builtPrompt, 0, options.length - 1);
	}
	
	/**
	 * Gets a choice from the user. The choice may be one of the given Strings
	 * in the array.
	 *
	 * @param options The Strings from which the user must choose.
	 *
	 * @return The index of the user's choice.
	 */
	@Override
	public int getChoice(String[] options) {
		return getChoice("", options);
	}
	
	/**
	 * Displays the stats of a Character.
	 *
	 * @param f The character to show.
	 */
	private void showStatus(Character f) {
		print(f.getName() + " (");
		print("Team " + f.getTeamId());
		println(", Fighter " + f.getFighterId() + ")");
		println("---------------");
		VariableStat hp = f.getHP();
		VariableStat mp = f.getMP();
		Stat str = f.getStrength();
		Stat def = f.getDefense();
		Stat agt = f.getAgility();
		Stat acc = f.getAccuracy();
		Stat mag = f.getMagic();
		Stat luk = f.getLuck();
		println("HP: " + hp.getCurrent() + "/" + hp.getMax(f.getLevel()));
		println("MP: " + mp.getCurrent() + "/" + mp.getMax(f.getLevel()));
		println("---------------");
		println("STR: " + str.getEffective(f.getLevel()));
		println("DEF: " + def.getEffective(f.getLevel()));
		println("AGT: " + agt.getEffective(f.getLevel()));
		println("ACC: " + acc.getEffective(f.getLevel()));
		println("MAG: " + mag.getEffective(f.getLevel()));
		println("LUK: " + luk.getEffective(f.getLevel()));
		println();
		pause();
	}
	
	/**
	 * Shows the battle introduction.
	 *
	 * @param fighters The fighting characters.
	 */
	private void showBattleScreen(Character[][] fighters) {
		String vsMsg = "";
		for (int i = 0; i < fighters.length; i++) {
			vsMsg += "Team " + fighters[i][0].getName();
			if (i + 1 < fighters.length) {
				vsMsg += " vs. "
			}
		}
		vsMsg += "!";
		println("A battle started!");
		println(vsMsg);
		println();
		pause();
	}
	
	/**
	 * Shows what each team is made up of.
	 *
	 * @param fighters The fighters.
	 */
	private void showTeams(Character[][] fighters) {
		for (int i = 0; i < fighters.length; i++) {
			print("Team " + i + ":");
			for (int j = 0; j < fighters[i].length; j++) {
				print(" " + fighters[i][j].getName());
				if (j + 1 < fighters[i].length) {
					print(",");
				}
			}
			println();
		}
		println();
		pause();
	}
	
	/**
	 * Prints a message to stdout.
	 *
	 * @param message The message to print.
	 */
	private void print(String message) {
		System.out.print(message);
	}
	
	/**
	 * Prints a message to stdout and ends the line.
	 *
	 * @param message The message to print.
	 */
	private void println(String message) {
		System.out.println(message);
	}
	
	/**
	 * Prints a line end to stdout.
	 */
	private void println() {
		println("\n");
	}
	
	/**
	 * Pauses the action until the user presses the enter key.
	 */
	private void pause() {
		try {
			stdin.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prints an error message.
	 *
	 * @param message The message to print.
	 */
	private void warn(String message) {
		System.err.println(message);
	}

}
