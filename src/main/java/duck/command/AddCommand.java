package duck.command;

import java.io.IOException;

import duck.task.Task;
import duck.task.TaskList;
import duck.Ui;
import duck.Storage;
import duck.DuckException;

/**
 * Represents a command that adds a task to the task list.
 * This command stores the task and updates persistent storage after execution.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Constructs an {@code AddCommand} with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes this command by adding the task to the task list.
     * Displays a confirmation message to the user and updates stored data.
     *
     * @param tasks The task list to which the task will be added.
     * @param ui The user interface used to display messages.
     * @param storage The storage used to persist task data.
     * @throws DuckException If an error occurs while updating persistent storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        tasks.addTask(this.task);
        ui.showAdd(this.task, tasks.getLength());

        // Update memory
        try {
            storage.write(tasks);
        } catch (IOException e) {
            throw new DuckException("Memory update failure:\n"  + e.getMessage() +
                    "\nYour data could be lost");
        }
    }
}
