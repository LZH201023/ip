package duck.command;

import java.io.IOException;

import duck.DuckException;
import duck.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents a command that marks a task in the task list as not completed.
 * This command updates the task status and saves the changes to storage.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructs a {@code UnmarkCommand} with the specified task index.
     *
     * @param index The one-based index of the task to be marked as not completed.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command by marking the specified task as not completed.
     * Receives a confirmation message and updates stored data.
     *
     * @param tasks The task list containing the task to be unmarked.
     * @param ui The user interface used to get messages.
     * @param storage The storage used to persist task data.
     * @throws DuckException If the task index is invalid or if an error occurs while updating storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {

        if (index <= 0 && index > tasks.getLength()) {
            throw new DuckException("Task index out of bound.");
        }

        tasks.unmarkTaskAt(index - 1);
        this.message = ui.getUnmarkMessage(tasks.getTask(index - 1));

        // Update memory
        try {
            storage.write(tasks);
        } catch (IOException e) {
            throw new DuckException("Memory update failure:\n" + e.getMessage()
                    + "\nYour data could be lost");
        }
    }

    /**
     * Returns type of the command.
     *
     * @return Type of command.
     */
    @Override
    public CommandType getCommandType() {
        return CommandType.UNMARK_COMMAND;
    }

}
