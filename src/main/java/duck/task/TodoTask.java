package duck.task;

public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toCompactString() {
        return "T" + (this.isDone ? "1" : "0") + this.description;
    }

    @Override
    public String toString() {
        return "[T][" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}
