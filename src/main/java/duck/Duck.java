package duck;

import duck.command.Command;
import duck.task.TaskList;

/**
 * Represents the main application class for Duck.
 * This class coordinates user input, command execution, and data storage.
 */
public class Duck {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final boolean hasError;

    /**
     * Constructs a {@code Duck} instance with the specified file path for data storage.
     * Initializes the user interface and loads existing task data if available.
     *
     * @param filePath The file path used to store and load task data.
     */
    public Duck(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        if (!storage.getStatus()) {
            this.hasError = true;
            return;
        } else {
            this.hasError = false;
        }

        try {
            tasks = storage.load();
        } catch (DuckException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Starts and runs the main application loop.
     * This method handles user input, command parsing, execution, and termination.
     */
    public void run() {
        boolean isExit = false;
        if (this.hasError) {
            ui.showUnsuccessfulStarting();
            return;
        }
        this.ui.greet();
        while (!isExit) {
            try {
                String nextInput = ui.readInput();
                ui.showLine();
                Command c = Parser.parse(nextInput);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DuckException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Launches the Duck application.
     *
     * @param args Command-line arguments provided at startup.
     */
    public static void main(String[] args) {
        new Duck("data/duck.txt").run();
    }

}
