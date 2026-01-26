import java.io.IOException;

class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {

        if (index > 0 && index <= tasks.getLength()) {
            tasks.unmarkTaskAt(index - 1);
            ui.showUnmark(tasks.getTask(index - 1));
        } else {
            throw new DuckException("Task index out of bound.");
        }

        // Update memory
        try {
            tasks.writeToDefaultFile();
        } catch (IOException e) {
            throw new DuckException("Memory update failure:\n"  + e.getMessage() +
                    "\nYour data could be lost");
        }
    }

}
