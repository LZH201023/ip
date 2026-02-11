package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents a command that adds a tag on a task.
 */
public class TagCommand extends Command {

    private final int index;
    private final String tag;

    /**
     * Constructs a {@code TagCommand} with the specified task index.
     *
     * @param index The one-based index of the task to be tagged.
     */
    public TagCommand(int index, String tag) {
        this.index = index;
        this.tag = tag;
    }

    /**
     * Executes this command by tagging the specified task from the task list.
     * Displays a confirmation message to the user.
     *
     * @param tasks The task list from which the task will be tagged.
     * @param ui The user interface used to get messages.
     * @param storage The storage used to persist task data.
     * @throws DuckException If the task index is invalid or if an error occurs while updating storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {

        if (index <= 0 || index > tasks.getLength()) {
            throw new DuckException("Task index out of bound.");
        }

        tasks.tagTaskAt(index - 1, tag);
        this.message = ui.getTagMessage(tasks.getTask(index - 1));
    }

    /**
     * Returns type of the command.
     *
     * @return Type of command.
     */
    @Override
    public CommandType getCommandType() {
        return CommandType.TAG_COMMAND;
    }
}
