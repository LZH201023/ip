import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>(100);
    }

    public Task getTask(int idx) {
        return this.list.get(idx);
    }

    public int getLength() {
        return this.list.size();
    }

    public void addTask(Task task){
        this.list.add(task);
    }

    public void markTaskAt(int idx) {
        this.list.get(idx).markAsDone();
    }

    public void unmarkTaskAt(int idx) {
        this.list.get(idx).markAsUndone();
    }

    public Task popTaskAt(int idx) {
        Task temp = this.list.get(idx);
        this.list.remove(idx);
        return temp;
    }

    @Override
    public String toString() {
        if (this.list.isEmpty()) {
            return "***EMPTY LIST***";
        }

        int len = this.list.size();
        String str = "1." + list.get(0);
        for (int i = 1; i < len; i++) {
            str += "\n" + (i + 1) + "." + list.get(i);
        }
        return str;
    }
}
