package duck.command;

import java.io.IOException;

import duck.DuckException;
import duck.Storage;
import duck.task.Task;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents a command that deletes a task from the task list.
 * This command removes the specified task and updates persistent storage.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param index The one-based index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command by deleting the specified task from the task list.
     * Displays a confirmation message to the user and updates stored data.
     *
     * @param tasks The task list from which the task will be deleted.
     * @param ui The user interface used to get messages.
     * @param storage The storage used to persist task data.
     * @throws DuckException If the task index is invalid or if an error occurs while updating storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {

        if (index <= 0 || index > tasks.getLength()) {
            throw new DuckException("Task index out of bound.");
        }

        Task task = tasks.deleteTaskAt(index - 1);
        this.message = ui.getDeleteMessage(task);

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
        return CommandType.DELETE_COMMAND;
    }

}
