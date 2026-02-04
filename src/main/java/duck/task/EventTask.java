package duck.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a <code>Task</code> with a duration specified by
 * starting and ending time of type <code>LocalDate</code>.
 */
public class EventTask extends Task {
    protected LocalDate startTime;
    protected LocalDate endTime;

    /**
     * Construct a new <code>EventTask</code> object,
     * date is based on the given <code>LocalDate</code> objects.
     *
     * @param description Name of task.
     * @param from Starting time of task.
     * @param to Ending time of task.
     */
    public EventTask(String description, LocalDate from, LocalDate to) {
        super(description);
        this.startTime = from;
        this.endTime = to;
    }

    /**
     * Construct a new <code>EventTask</code> object,
     * date is based on the given <code>String</code> object.
     *
     * @param description Name of task.
     * @param from Starting time of task.
     * @param to Ending time of task.
     * @throws DateTimeParseException If <code>from</code> or
     *                                <code>to</code> cannot be parsed into a valid date.
     */
    public EventTask(String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.startTime = LocalDate.parse(from);
        this.endTime = LocalDate.parse(to);
    }

    /**
     * Convert <code>EventTask</code> object into compact string,
     * which is to be stored through a <code>Storage</code> object.
     *
     * @return Compact string representation.
     */
    @Override
    public String toCompactString() {
        return "E" + (this.isDone ? "1" : "0") + this.description
                + "/" + this.startTime + "/" + this.endTime;
    }

    /**
     * Convert <code>EventTask</code> object into string.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " + this.description
                + " (from: " + startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH)) + " to: "
                + endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH)) + ")";
    }

}
