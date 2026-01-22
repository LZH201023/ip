public class TaskList {
    private Task[] list;
    private int length;

    public TaskList() {
        this.list = new Task[100];
        this.length = 0;
    }

    public Task getTask(int idx) {
        return this.list[idx];
    }

    public int getLength() {
        return this.length;
    }

    public void addTask(Task task){
        this.list[this.length++] = task;
    }

    public void markTaskAt(int idx) {
        this.list[idx].markAsDone();
    }

    public void unmarkTaskAt(int idx) {
        this.list[idx].markAsUndone();
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "";
        }

        String str = "1." + list[0];
        for (int i = 1; i < length; i++) {
            str += "\n" + (i + 1) + "." + list[i];
        }
        return str;
    }
}
