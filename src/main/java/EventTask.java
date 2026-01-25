import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    protected LocalDate startTime;
    protected LocalDate endTime;

    public EventTask(String description, LocalDate from, LocalDate to) {
        super(description);
        this.startTime = from;
        this.endTime = to;
    }

    public EventTask(String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.startTime = LocalDate.parse(from);
        this.endTime = LocalDate.parse(to);
    }

    @Override
    public String toCompactString() {
        return "E" + (this.isDone ? "1" : "0") + this.description +
                "/" + this.startTime + "/" + this.endTime;
    }
    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " + this.description +
                " (from: " + startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " +
                endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
