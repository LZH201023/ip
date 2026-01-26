package duck.command;

import duck.task.TaskList;
import duck.Ui;
import duck.Storage;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
        ui.closeScanner();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
