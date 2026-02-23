package duck.task;

/**
 * Represents a stub for <code>TodoTask</code>. This task will always be named "ip".
 */
class TodoTaskStub extends Task {

    /**
     * Constructs a {@code TodoTaskStub} with the name "ip".
     */
    public TodoTaskStub() {
        super("ip");
    }

    /**
     * Converts <code>TodoTaskStub</code> object into compact string,
     * which is to be stored through a <code>Storage</code> object.
     *
     * @return Compact string representation.
     */
    @Override
    public String toCompactString() {
        return "T" + (this.isDone ? "1" : "0") + this.description;
    }

    /**
     * Converts <code>TodoTaskStub</code> object into string.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T][X] " + this.description;
    }
}
