package duck.command;

import duck.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents a command that finds tasks by keyword.
 * This command displays all tasks that contain <code>keyword</code>.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a new <code>FindCommand</code>.
     *
     * @param keyword The keyword to be matched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes this command by finding matching tasks in the task list.
     *
     * @param tasks The task list used by the application.
     * @param ui The user interface used to get messages.
     * @param storage The storage used to manage task data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList sublist = tasks.findAllMatch(keyword);
        this.message = ui.getFindResultMessage(sublist);
    }

}
