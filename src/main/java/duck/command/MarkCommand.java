package duck.command;

import java.io.IOException;

import duck.task.TaskList;
import duck.Ui;
import duck.Storage;
import duck.DuckException;

/**
 * Represents a command that marks a task in the task list as completed.
 * This command updates the task status and saves the changes to storage.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructs a {@code MarkCommand} with the specified task index.
     *
     * @param index The one-based index of the task to be marked as completed.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command by marking the specified task as completed.
     * Displays a confirmation message and updates stored data.
     *
     * @param tasks The task list containing the task to be marked.
     * @param ui The user interface used to display messages.
     * @param storage The storage used to persist task data.
     * @throws DuckException If the task index is invalid or if an error occurs while updating storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {

        if (index > 0 && index <= tasks.getLength()) {
            tasks.markTaskAt(index - 1);
            ui.showMark(tasks.getTask(index - 1));
        } else {
            throw new DuckException("Task index out of bound.");
        }

        // Update memory
        try {
            storage.write(tasks);
        } catch (IOException e) {
            throw new DuckException("Memory update failure:\n"  + e.getMessage() +
                    "\nYour data could be lost");
        }
    }

}
