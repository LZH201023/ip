package duck.task;

/**
 * Represents a to-do task without a specific date or time.
 * A {@code TodoTask} contains only a description and a completion status.
 */
public class TodoTask extends Task {

    /**
     * Constructs a {@code TodoTask} with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Convert <code>TodoTask</code> object into compact string,
     * which is to be stored through a <code>Storage</code> object.
     *
     * @return Compact string representation.
     */
    @Override
    public String toCompactString() {
        return "T" + (this.isDone ? "1" : "0") + this.description;
    }

    /**
     * Convert <code>TodoTask</code> object into string.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T][" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}
