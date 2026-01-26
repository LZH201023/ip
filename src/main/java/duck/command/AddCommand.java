package duck.command;

import java.io.IOException;
import duck.task.Task;
import duck.task.TaskList;
import duck.Ui;
import duck.Storage;
import duck.DuckException;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

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
