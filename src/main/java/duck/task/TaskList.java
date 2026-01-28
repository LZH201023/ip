package duck.task;

import java.util.ArrayList;

/**
 * Represents a list of <code>Task</code> objects.
 * Provides methods to add, remove, retrieve, and modify tasks.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructs an empty {@code TaskList} with an initial capacity of 100.
     */
    public TaskList() {
        this.list = new ArrayList<Task>(100);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param idx Index of the task to retrieve (0-based).
     * @return Task at the specified index.
     */
    public Task getTask(int idx) {
        return this.list.get(idx);
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return Number of tasks.
     */
    public int getLength() {
        return this.list.size();
    }

    /**
     * Adds a task to the end of this task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task){
        this.list.add(task);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param idx Index of the task to mark (0-based).
     */
    public void markTaskAt(int idx) {
        this.list.get(idx).markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param idx Index of the task to unmark (0-based).
     */
    public void unmarkTaskAt(int idx) {
        this.list.get(idx).markAsUndone();
    }

    /**
     * Deletes and returns the task at the specified index.
     *
     * @param idx Index of the task to delete (0-based).
     * @return The deleted task.
     */
    public Task deleteTaskAt(int idx) {
        Task temp = this.list.get(idx);
        this.list.remove(idx);
        return temp;
    }

    /**
     * Returns a list of tasks with matching keyword.
     *
     * @param keyword The keyword to find tasks.
     * @return A <code>TaskList</code> of matching tasks.
     */
    public TaskList findAllMatch(String keyword) {
        TaskList sublist = new TaskList();
        for (Task task : this.list) {
            if (task.getDescription().contains(keyword)) {
                sublist.addTask(task);
            }
        }
        return sublist;
    }

    /**
     * Returns a string representation of this task list.
     * Each task is displayed on a new line and numbered starting from 1.
     *
     * @return A formatted string representing the task list.
     */
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
