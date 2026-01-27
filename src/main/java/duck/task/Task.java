package duck.task;

/**
 * Describes a general task with a description of type <code>String</code>,
 * and has a status of whether it has been done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Construct a new <code>Task</code> object, by default it is not done yet.
     *
     * @param description Name of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Convert <code>Task</code> object into compact string,
     * which is to be stored through a <code>Storage</code> object.
     *
     * @return Compact string representation.
     */
    public abstract String toCompactString();

    /**
     * Mark the task as been done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Set the task to be not done yet.
     */
    public void markAsUndone() {
        this.isDone = false;
    }
}
