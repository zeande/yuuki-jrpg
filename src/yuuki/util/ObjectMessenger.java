/**
 * Allows interface to send message to another object without direct access to
 * it.
 */

public class ObjectMessenger<T> {

	/**
	 * The options for the interface to choose from.
	 */
	private T[] choices;
	
	/**
	 * The chosen option
	 */
	private T option;
	
	/**
	 * Creates a new ObjectMessenger.
	 */
	public ObjectMessenger() {
		choices = null;
		option = null;
	}
	
	/**
	 * Checks whether the option is ready to be retrieved.
	 *
	 * @return True if the option is ready; false otherwise.
	 */
	public boolean optionReady() {
		return (option != null);
	}
	
	/**
	 * Checks whether the choices are ready to be retrieved.
	 *
	 * @return True if the choices are ready; false otherwise.
	 */
	public boolean choicesReady() {
		return (choices != null);
	}
	
	/**
	 * Gets the chosen option.
	 *
	 * @return The option.
	 */
	public T getOption() {
		return option;
	}
	
	/**
	 * Gets the choices.
	 *
	 * @return The choices.
	 */
	public T[] getChoices() {
		return choices;
	}
	
	/**
	 * Resets the option to null.
	 */
	public void resetOption() {
		option = null;
	}
	
	/**
	 * Resets the choices to null.
	 */
	public void resetChoices() {
		choices = null;
	}
	
	/**
	 * Sets the option.
	 *
	 * @param option The index of the option to set.
	 */
	public void setOption(int option) {
		this.option = choices[option];
	}
	
	/**
	 * Sets the choices.
	 *
	 * @param choices The array of choices.
	 */
	public void setChoices(T[] choices) {
		this.choices = choices;
	}

}