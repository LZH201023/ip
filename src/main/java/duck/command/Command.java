package duck.command;

import duck.task.TaskList;
import duck.Ui;
import duck.Storage;
import duck.DuckException;

/**
 * Represents an executable command in the application.
 * Each concrete subclass defines specific behavior for user instructions.
 */
public abstract class Command {

    /**
     * Executes this command using the provided task list, user interface, and storage.
     * Subclasses must implement this method to define their specific behavior.
     *
     * @param tasks The task list to be modified or accessed.
     * @param ui The user interface used to display messages.
     * @param storage The storage used to read or write task data.
     * @throws DuckException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException;

    /**
     * Indicates whether this command signals the application to exit.
     * Subclasses may override this method to return {@code true} for exit commands.
     *
     * @return {@code true} if this command requests application termination. Otherwise, {@code false}.
     */
    public boolean isExit() {
        return false;
    }
}
