public class Duck {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final boolean hasError;

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

    public static void main(String[] args) {
        new Duck("data/duck.txt").run();
    }

}
