package duck.command;

import duck.task.TaskList;
import duck.Ui;
import duck.Storage;
import duck.DuckException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException;

    public boolean isExit() {
        return false;
    }
}
