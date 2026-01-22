import java.util.Scanner;

public class Duck {
    private static final TaskList tasks = new TaskList();
    private static final String HLINE = "-".repeat(50);

    private static boolean isBye(String s) {
        if (s.length() != 3) {
            return false;
        }
        boolean b1 = s.charAt(0) == 'b' || s.charAt(0) == 'B';
        boolean b2 = s.charAt(1) == 'y' || s.charAt(1) == 'Y';
        boolean b3 = s.charAt(2) == 'e' || s.charAt(2) == 'E';
        return b1 && b2 && b3;
    }

    private static int parseMark(String s) {
        if (s.length() < 6) {
            return -1;
        }

        int num = 0;
        if (s.startsWith("mark")) {
            String str = s.substring(4).stripLeading();
            int idx = 0;
            while (idx < str.length() && Character.isDigit(str.charAt(idx))) {
                idx++;
            }
            if (idx == 0) {
                return -1;
            } else {
                int mult = 1;
                for (int i = idx - 1; i >= 0; i--) {
                    num += mult * (str.charAt(i) - '0');
                    mult *= 10;
                }
            }
        }

        if (num > 0 && num <= tasks.getLength()) {
            return num;
        } else {
            return -1;
        }
    }

    private static int parseUnmark(String s) {
        if (s.length() < 8) {
            return -1;
        }

        int num = 0;
        if (s.startsWith("unmark")) {
            String str = s.substring(6).stripLeading();
            int idx = 0;
            while (idx < str.length() && Character.isDigit(str.charAt(idx))) {
                idx++;
            }
            if (idx == 0) {
                return -1;
            } else {
                int mult = 1;
                for (int i = idx - 1; i >= 0; i--) {
                    num += mult * (str.charAt(i) - '0');
                    mult *= 10;
                }
            }
        }

        if (num > 0 && num <= tasks.getLength()) {
            return num;
        } else {
            return -1;
        }
    }

    private static void parseAndAdd(String s) {
        String task;
        s = s.strip();
        boolean tag = true;
        if (s.startsWith("todo")) {
            task = s.substring(4).stripLeading();
            if (task.isEmpty()) {
                tag = false;
            } else {
                tasks.addTask(new TodoTask(task));
            }
        } else if (s.startsWith("deadline")) {
            String str = s.substring(8).stripLeading();
            String[] parts = str.split("/by", 2);
            if (parts.length == 2 && !parts[0].isEmpty() && !parts[1].isEmpty()) {
                parts[0] = parts[0].strip();
                parts[1] = parts[1].strip();
                DeadlineTask deadline = new DeadlineTask(parts[0], parts[1]);
                tasks.addTask(deadline);
            } else {
                tag = false;
            }
        } else if (s.startsWith("event")) {
            String str = s.substring(5).stripLeading();
            String[] parts1 = str.split("/from");
            if (parts1.length != 2 || parts1[0].isEmpty()) {
                tag = false;
            } else {
                String[] parts2 = parts1[1].strip().split("/to");
                if (parts2.length != 2 || parts2[0].isEmpty() || parts2[1].isEmpty()) {
                    tag = false;
                } else {
                    parts1[0] = parts1[0].strip();
                    parts2[0] = parts2[0].strip();
                    parts2[1] = parts2[1].strip();
                    EventTask event = new EventTask(parts1[0], parts2[0], parts2[1]);
                    tasks.addTask(event);
                }
            }
        } else {
            tag = false;
        }

        if (!tag) {
            System.out.println(HLINE + "\n" + s + "\n" + HLINE);
            return;
        }

        System.out.println(HLINE + "\n" + "Got it. I've added this task:\n" +
                tasks.getTask(tasks.getLength() - 1) +
                "\nNow you have " + tasks.getLength() + " tasks in the list\n" + HLINE);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(HLINE + "\nHello! I'm Duck" +
                            "\nWhat can I do for you?\n" + HLINE);

        while (true) {
            String next = sc.nextLine();
            String s = next.strip();
            int idx;
            if (s.equals("list")) {
                System.out.println(HLINE + "\n" +
                        "Here are the tasks in your list:\n" + tasks);
                System.out.println("\n" + HLINE);
            } else if ((idx = parseMark(s)) > 0) {
                tasks.markTaskAt(idx - 1);
                System.out.println(HLINE + "\n" +
                        "Nice! I've marked this task as done:\n" +
                        tasks.getTask(idx - 1) + "\n" +
                        HLINE);
            } else if ((idx = parseUnmark(s)) > 0) {
                tasks.unmarkTaskAt(idx - 1);
                System.out.println(HLINE + "\n" +
                        "Ok, I've marked this task as not done yet:\n" +
                        tasks.getTask(idx - 1) + "\n" +
                        HLINE);
            } else if (isBye(s)) {
                System.out.println(HLINE + "\n" +
                        "Bye. Hope to see you again soon!\n" +
                        HLINE);
                break;
            } else { // Add task
                parseAndAdd(next);
            }
        }
    }
}
