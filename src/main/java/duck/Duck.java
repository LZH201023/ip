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
    private String commandType;

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
            System.out.println(ui.getLoadingErrorMessage());
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
            System.out.println(ui.getUnsuccessfulStartingMessage());
            return;
        }
        //this.ui.greet();
        while (!isExit) {
            try {
                String nextInput = ui.readInput();
                ui.showLine();
                Command c = Parser.parse(nextInput);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DuckException e) {
               // ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            return c.getString();
        } catch (DuckException e) {
            commandType = "UNKNOWN";
            return e.getMessage();
        }
    }
    public String getCommandType() {
        return commandType;
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
