package duck.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a <code>Task</code> with a deadline of type
 * <code>LocalDate</code>.
 */
public class DeadlineTask extends Task {
    protected LocalDate deadline;

    /**
     * Construct a new <code>DeadlineTask</code> object,
     * date is based on the given <code>LocalDate</code> object.
     *
     * @param description Name of task.
     * @param deadline Deadline of task.
     */
    public DeadlineTask(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Construct a new <code>DeadlineTask</code> object,
     * date is based on the given <code>String</code> object.
     *
     * @param description Name of task.
     * @param deadline Deadline of task.
     * @throws DateTimeParseException If <code>deadline</code> cannot be parsed into a valid date.
     */
    public DeadlineTask(String description, String deadline) throws DateTimeParseException {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Convert <code>DeadlineTask</code> object into compact string,
     * which is to be stored through a <code>Storage</code> object.
     *
     * @return Compact string representation.
     */
    @Override
    public String toCompactString() {
        return "D" + (this.isDone ? "1" : "0") + this.description
                + "/" + this.deadline;
    }

    /**
     * Convert <code>DeadlineTask</code> object into string.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String suffix = this.tag == null ? "" : "#" + this.tag;
        return "[D][" + (this.isDone ? "X" : " ") + "] " + this.description
                + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH)) + ")"
                + " " + suffix;
    }
}
