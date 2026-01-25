public class DeadlineTask extends Task {
    protected String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toCompactString() {
        return "D" + (this.isDone ? "1" : "0") + this.description +
                "/" + this.deadline;
    }

    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] " + this.description +
                " (by: " + this.deadline + ")";
    }
}
