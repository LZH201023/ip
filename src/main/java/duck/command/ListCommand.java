package duck.command;

import duck.Storage;
import duck.Ui;
import duck.task.TaskList;

/**
 * Represents a command that displays all tasks in the task list.
 * This command outputs the current list of tasks to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes this command by showing the task list to the user.
     *
     * @param tasks The task list whose contents will be displayed.
     * @param ui The user interface used to get messages.
     * @param storage The storage used to manage task data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.message = ui.getListMessage(tasks);
    }

}
