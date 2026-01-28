package duck;

import java.util.Scanner;

import duck.task.Task;
import duck.task.TaskList;

/**
 * Handles all user interaction for the Duck application.
 * This class is responsible for displaying messages and reading user input.
 */
public class Ui {

    private static final String HLINE = "-".repeat(50);
    private static final String DUCK = " ____        ____ _   __\n" +
            "|  _ \\ _   _|  __| | / /\n" +
            "| | | | | | | |  | /  /\n" +
            "| |_| | |_| | |__| |\\ \\\n" +
            "|____/ \\__,_|____|_| \\_\\";

    private Scanner sc;

    /**
     * Constructs a {@code Ui} instance and initializes the input scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays the greeting message and application logo.
     * This method also provides brief instructions to the user.
     */
    public void greet() {
        System.out.println(HLINE + "\nHello! I'm\n" + DUCK +
                "\nWhat can I do for you?\n(btw, plz specify time in yyyy-mm-dd)\n" + HLINE);
    }

    /**
     * Displays a horizontal separator line.
     */
    public void showLine() {
        System.out.println(HLINE);
    }

    /**
     * Reads the next line of user input from standard input.
     *
     * @return The next input line entered by the user.
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Displays a message indicating that a task has been marked as completed.
     *
     * @param task The task that was marked as done.
     */
    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Displays a message indicating that a task has been marked as not completed.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmark(Task task) {
        System.out.println("Ok, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that was removed.
     */
    public void showDelete(Task task) {
        System.out.println("Noted. I've removed this task:\n" + task);
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param len The updated number of tasks in the list.
     */
    public void showAdd(Task task, int len) {
        System.out.println("Got it. I've added this task:\n" + task +
                "\nNow you have " + len + " tasks in the list");
    }

    /**
     * Displays the full list of tasks to the user.
     *
     * @param tasks The task list to be displayed.
     */
    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:\n" + tasks);
    }

    /**
     * Displays the exit message when the application terminates.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays a message indicating that application startup was unsuccessful.
     */
    public void showUnsuccessfulStarting() {
        System.out.println("Memory setup unsuccessful, chatbot closing, bye!");
    }


    /**
     * Displays a message indicating that stored data could not be loaded.
     */
    public void showLoadingError() {
        System.out.println("Memory corrupted, task list cleared.");
    }

    /**
     * Displays the matching tasks on screen.
     *
     * @param sublist The list of tasks to be displayed.
     */
    public void displayFindResult(TaskList sublist) {
        if (sublist.getLength() == 0) {
            System.out.println("No matching task found!");
        } else {
            System.out.println("Here are the found tasks:\n" + sublist);
        }
    }

    /**
     * Closes the input scanner and releases associated resources.
     */
    public void closeScanner() {
        this.sc.close();
    }
}
