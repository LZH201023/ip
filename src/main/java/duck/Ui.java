package duck;

import duck.task.Task;
import duck.task.TaskList;
import java.util.Scanner;

public class Ui {

    private static final String HLINE = "-".repeat(50);
    private static final String DUCK = " ____        ____ _   __\n" +
            "|  _ \\ _   _|  __| | / /\n" +
            "| | | | | | | |  | /  /\n" +
            "| |_| | |_| | |__| |\\ \\\n" +
            "|____/ \\__,_|____|_| \\_\\";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void greet() {
        System.out.println(HLINE + "\nHello! I'm\n" + DUCK +
                "\nWhat can I do for you?\n(btw, plz specify time in yyyy-mm-dd)\n" + HLINE);
    }

    public void showLine() {
        System.out.println(HLINE);
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void showUnmark(Task task) {
        System.out.println("Ok, I've marked this task as not done yet:\n" + task);
    }

    public void showDelete(Task task) {
        System.out.println("Noted. I've removed this task:\n" + task);
    }

    public void showAdd(Task task, int len) {
        System.out.println("Got it. I've added this task:\n" + task +
                "\nNow you have " + len + " tasks in the list");
    }

    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:\n" + tasks);
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showUnsuccessfulStarting() {
        System.out.println("Memory setup unsuccessful, chatbot closing, bye!");
    }

    public void showLoadingError() {
        System.out.println("Memory corrupted, task list cleared.");
    }

    public void closeScanner() {
        this.sc.close();
    }
}
