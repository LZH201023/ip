public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T][" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}
