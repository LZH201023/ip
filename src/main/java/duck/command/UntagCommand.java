package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents a command that removes a tag on a task.
 */
public class UntagCommand extends Command {
    private final int index;

    /**
     * Constructs an {@code UntagCommand} with the specified task index.
     *
     * @param index The one-based index of the task to be untagged.
     */
    public UntagCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command by untagging the specified task from the task list.
     * Displays a confirmation message to the user.
     *
     * @param tasks The task list from which the task will be untagged.
     * @param ui The user interface used to get messages.
     * @param storage The storage used to persist task data.
     * @throws DuckException If the task index is invalid or if an error occurs while updating storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        if (index <= 0 || index > tasks.getLength()) {
            throw new DuckException("Task index out of bound.");
        }

        tasks.untagTaskAt(index - 1);
        this.message = ui.getUntagMessage(tasks.getTask(index - 1));
    }

    /**
     * Returns type of the command.
     *
     * @return Type of command.
     */
    @Override
    public CommandType getCommandType() {
        return CommandType.UNTAG_COMMAND;
    }
}
