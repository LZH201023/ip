package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents an executable command in the application.
 * Each concrete subclass defines specific behavior for user instructions.
 */
public abstract class Command {

    protected String message;

    /**
     * Executes this command using the provided task list, user interface, and storage.
     * Subclasses must implement this method to define their specific behavior.
     *
     * @param tasks The task list to be modified or accessed.
     * @param ui The user interface used to get messages.
     * @param storage The storage used to read or write task data.
     * @throws DuckException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException;

    /**
     * Returns the message to be displayed by executing this command.
     *
     * @return Message to be displayed.
     */
    public String getString() {
        return this.message;
    }

    /**
     * Returns type of the command.
     *
     * @return Type of command.
     */
    public abstract CommandType getCommandType();
}
