import java.io.IOException;

class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        tasks.addTask(this.task);
        ui.showAdd(this.task, tasks.getLength());

        // Update memory
        try {
            tasks.writeToDefaultFile();
        } catch (IOException e) {
            throw new DuckException("Memory update failure:\n"  + e.getMessage() +
                    "\nYour data could be lost");
        }
    }
}
