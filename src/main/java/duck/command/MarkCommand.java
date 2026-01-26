package duck.command;

import java.io.IOException;
import duck.task.TaskList;
import duck.Ui;
import duck.Storage;
import duck.DuckException;

public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

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
