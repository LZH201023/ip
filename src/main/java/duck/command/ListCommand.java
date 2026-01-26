package duck.command;

import duck.task.TaskList;
import duck.Ui;
import duck.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

}
