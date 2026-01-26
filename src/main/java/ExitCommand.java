class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
        ui.closeScanner();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
