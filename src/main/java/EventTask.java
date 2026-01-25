public class EventTask extends Task {
    protected String startTime;
    protected String endTime;

    public EventTask(String description, String from, String to) {
        super(description);
        this.startTime = from;
        this.endTime = to;
    }

    @Override
    public String toCompactString() {
        return "E" + (this.isDone ? "1" : "0") + this.description +
                "/" + this.startTime + "/" + this.endTime;
    }
    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " + this.description +
                " (from: " + startTime + " to: " + endTime + ")";
    }
}
