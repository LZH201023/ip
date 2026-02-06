package duck.command;

import duck.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents a command that terminates the application.
 * This command displays a farewell message and closes input resources.
 */
public class ExitCommand extends Command {

    /**
     * Executes this command by displaying the exit message and closing the scanner.
     *
     * @param tasks The task list used by the application.
     * @param ui The user interface used to get messages.
     * @param storage The storage used to manage task data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.message = ui.getExitMessage();
        ui.closeScanner();
    }

    /**
     * Indicates that this command signals the application to exit.
     *
     * @return {@code true} to indicate application termination.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
