import java.util.Scanner;

public class Duck {
    private static final Task[] texts = new Task[100];
    private static int textsLen = 0;

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
            char c = s.charAt(5);
            num = Character.isDigit(c) ? c - '0' : 0;
        }

        if (num > 0 && num <= textsLen) {
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
            char c = s.charAt(7);
            num = Character.isDigit(c) ? c - '0' : 0;
        }

        if (num > 0 && num <= textsLen) {
            return num;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        String hLine = "-".repeat(50);
        Scanner sc = new Scanner(System.in);
        System.out.println(hLine + "\nHello! I'm Duck" +
                            "\nWhat can I do for you?\n" + hLine);

        while (true) {
            String next = sc.nextLine();
            String s = next.stripLeading();
            int idx;
            if (s.equals("list")) {
                System.out.println(hLine);
                for (int i = 1; i <= textsLen; i++) {
                    System.out.println("\n" + i + "." + texts[i - 1]);
                }
                System.out.println("\n" + hLine);
            } else if ((idx = parseMark(s)) > 0) {
                texts[idx - 1].markAsDone();
                System.out.println(hLine + "\n" +
                        "Nice! I've marked this task as done:\n" +
                        texts[idx - 1] + "\n" +
                        hLine);
            } else if ((idx = parseUnmark(s)) > 0) {
                texts[idx - 1].markAsUndone();
                System.out.println(hLine + "\n" +
                        "Ok, I've marked this task as not done yet:\n" +
                        texts[idx - 1] + "\n" +
                        hLine);
            } else if (isBye(s)) {
                System.out.println(hLine + "\n" +
                        "Bye. Hope to see you again soon!\n" +
                        hLine);
                break;
            } else { // Add task
                Task task = new Task(next);
                texts[textsLen++] = task;
                System.out.println(hLine + "\nadded: " + next + "\n" + hLine);
            }
        }
    }
}
