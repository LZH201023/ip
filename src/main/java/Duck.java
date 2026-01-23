import java.util.Scanner;

public class Duck {
    private static final TaskList tasks = new TaskList();
    private static final String HLINE = "-".repeat(50);
    private static final String DUCK = " ____        _______  __\n" +
            "|  _ \\ _   _|  __| | / /\n" +
            "| | | | | | | |  | /  /\n" +
            "| |_| | |_| | |__| |\\ \\\n" +
            "|____/ \\__,_|____|_| \\_\\";

    private static boolean isBye(String s) {
        if (s.length() != 3) {
            return false;
        }
        boolean b1 = s.charAt(0) == 'b' || s.charAt(0) == 'B';
        boolean b2 = s.charAt(1) == 'y' || s.charAt(1) == 'Y';
        boolean b3 = s.charAt(2) == 'e' || s.charAt(2) == 'E';
        return b1 && b2 && b3;
    }

    private static void parseAndDelete(String s) throws DuckException {
        int num = 0;
        int idx = 0;
        while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
            idx++;
        }
        if (!s.isEmpty() && idx == s.length()) {
            int mult = 1;
            for (int i = idx - 1; i >= 0; i--) {
                num += mult * (s.charAt(i) - '0');
                mult *= 10;
            }
        } else {
            throw new DuckException("Oh no, please delete a proper task!");
        }


        if (num > 0 && num <= tasks.getLength()) {
            Task temp = tasks.popTaskAt(num - 1);
            System.out.println(HLINE + "\n" +
                    "Noted. I've removed this task:\n" +
                    temp + "\n" +
                    HLINE);
        } else {
            throw new DuckException("Task index out of bound.");
        }
    }

    private static void parseAndMark(String s) throws DuckException {
        int num = 0;
        int idx = 0;
        while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
            idx++;
        }
        if (idx == s.length()) {
            int mult = 1;
            for (int i = idx - 1; i >= 0; i--) {
                num += mult * (s.charAt(i) - '0');
                mult *= 10;
            }
        } else {
            throw new DuckException("Oh no, please mark a proper task!");
        }


        if (num > 0 && num <= tasks.getLength()) {
            tasks.markTaskAt(num - 1);
            System.out.println(HLINE + "\n" +
                    "Nice! I've marked this task as done:\n" +
                    tasks.getTask(num - 1) + "\n" +
                    HLINE);
        } else {
            throw new DuckException("Task index out of bound.");
        }
    }

    private static void parseAndUnmark(String s) throws DuckException {
        int num = 0;
        int idx = 0;
        while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
            idx++;
        }
        if (idx == s.length()) {
            int mult = 1;
            for (int i = idx - 1; i >= 0; i--) {
                num += mult * (s.charAt(i) - '0');
                mult *= 10;
            }
        } else {
            throw new DuckException("Oh no, please unmark a proper task!");
        }


        if (num > 0 && num <= tasks.getLength()) {
            tasks.unmarkTaskAt(num - 1);
            System.out.println(HLINE + "\n" +
                    "Ok, I've marked this task as not done yet:\n" +
                    tasks.getTask(num - 1) + "\n" +
                    HLINE);
        } else {
            throw new DuckException("Task index out of bound.");
        }
    }

    private static void parseAndAdd(String s) throws DuckException {
        String task;
        if (s.startsWith("todo")) {
            task = s.substring(4).stripLeading();
            if (task.isEmpty()) {
                throw new DuckException("Missing todo description!");
            } else {
                tasks.addTask(new TodoTask(task));
            }
        } else if (s.startsWith("deadline")) {
            String[] parts = s.substring(8).concat(" ").split("/by");
            if (parts[0].isBlank()){
                throw new DuckException("Missing deadline description!");
            } else if (parts.length == 2 && !parts[1].isBlank()) {
                parts[0] = parts[0].strip();
                parts[1] = parts[1].strip();
                DeadlineTask deadline = new DeadlineTask(parts[0], parts[1]);
                tasks.addTask(deadline);
            } else if (parts.length < 2) {
                throw new DuckException("Wrong command format.");
            } else {
                throw new DuckException("Missing deadline!");
            }
        } else if (s.startsWith("event")) {
            String[] parts1 = s.substring(5).concat(" ").split("/from");
            if (parts1[0].isBlank()) {
                throw new DuckException("Missing event description!");
            } else if (parts1.length != 2 || parts1[1].isBlank()) {
                throw new DuckException("Oops, did you mess up starting time?");
            } else {
                String[] parts2 = parts1[1].concat(" ").split("/to");
                if (parts2.length != 2 || parts2[1].isBlank()) {
                    throw new DuckException("Oops, did you mess up ending time?");
                } else if (parts2[0].isBlank()) {
                    throw new DuckException("Oops, did you mess up starting time?");
                } else {
                    parts1[0] = parts1[0].strip();
                    parts2[0] = parts2[0].strip();
                    parts2[1] = parts2[1].strip();
                    EventTask event = new EventTask(parts1[0], parts2[0], parts2[1]);
                    tasks.addTask(event);
                }
            }
        } else {
            throw new DuckException("Sorry I can't understand...");
        }

        System.out.println(HLINE + "\n" + "Got it. I've added this task:\n" +
                tasks.getTask(tasks.getLength() - 1) +
                "\nNow you have " + tasks.getLength() + " tasks in the list\n" + HLINE);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(HLINE + "\nHello! I'm\n" + DUCK +
                            "\nWhat can I do for you?\n" + HLINE);

        while (true) {
            String next = sc.nextLine();
            String command = next.strip();
            if (command.equals("list")) {
                System.out.println(HLINE + "\n" +
                        "Here are the tasks in your list:\n" + tasks);
                System.out.println(HLINE);
            } else if (command.startsWith("mark")) {
                try {
                    parseAndMark(command.substring(4).stripLeading());
                } catch (DuckException e) {
                    System.out.println(HLINE + "\n" + e.getMessage() + "\n" + HLINE);
                }
            } else if (command.startsWith("unmark")) {
                try {
                    parseAndUnmark(command.substring(6).stripLeading());
                } catch (DuckException e) {
                    System.out.println(HLINE + "\n" + e.getMessage() + "\n" + HLINE);
                }
            } else if (command.startsWith("delete")) {
                try {
                    parseAndDelete(command.substring(6).stripLeading());
                } catch (DuckException e) {
                    System.out.println(HLINE + "\n" + e.getMessage() + "\n" + HLINE);
                }
            } else if (isBye(command)) {
                System.out.println(HLINE + "\n" +
                        "Bye. Hope to see you again soon!\n" +
                        HLINE);
                break;
            } else { // Add task
                try {
                    parseAndAdd(command);
                } catch (DuckException e) {
                    System.out.println(HLINE + "\n" + e.getMessage() + "\n" + HLINE);
                }
            }
        }
    }
}
