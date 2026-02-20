package duck.ui;

import duck.task.Task;
import duck.task.TaskList;

/**
 * Handles all user interaction for the Duck application.
 * This class is responsible for displaying messages and reading user input.
 */
public class Ui {

    /**
     * Displays a message indicating that a task has been marked as completed.
     *
     * @param task The task that was marked as done.
     */
    public String getMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Displays a message indicating that a task has been marked as not completed.
     *
     * @param task The task that was unmarked.
     */
    public String getUnmarkMessage(Task task) {
        return "Ok, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that was removed.
     */
    public String getDeleteMessage(Task task) {
        return "Noted. I've removed this task:\n" + task;
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param len The updated number of tasks in the list.
     */
    public String getAddMessage(Task task, int len) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + len + " task"
                + (len == 1 ? " " : "s ") + "in the list";
    }

    /**
     * Displays the full list of tasks to the user.
     *
     * @param tasks The task list to be displayed.
     */
    public String getListMessage(TaskList tasks) {
        return "Here is your list:\n" + tasks;
    }

    /**
     * Displays the exit message when the application terminates.
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a message indicating that stored data could not be loaded.
     */
    public String getLoadingErrorMessage() {
        return "Memory corrupted, task list cleared.";
    }

    /**
     * Displays the matching tasks on screen.
     *
     * @param sublist The list of tasks to be displayed.
     */
    public String getFindResultMessage(TaskList sublist) {
        if (sublist.getLength() == 0) {
            return "No matching task found!";
        } else {
            return "Nice, I've found it:\n" + sublist;
        }
    }

    /**
     * Returns the tagging message.
     *
     * @param task Task that is tagged.
     * @return The message corresponding to the tagging command.
     */
    public String getTagMessage(Task task) {
        return "Done! I've tagged this task:\n" + task;
    }

    /**
     * Returns the untagging message.
     *
     * @param task Task that is untagged.
     * @return The message corresponding to the untagging command.
     */
    public String getUntagMessage(Task task) {
        return "Okay. I've untagged this task:\n" + task;
    }

}
