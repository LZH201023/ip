package duck;

import duck.command.Command;
import duck.task.TaskList;
import duck.ui.Ui;

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

}
