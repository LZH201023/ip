import java.util.Scanner;

public class Duck {
    private static String[] texts = new String[100];
    private static int textsLen = 0;

    private static boolean isBye(String s) {
        boolean b1 = s.charAt(0) == 'b' || s.charAt(0) == 'B';
        boolean b2 = s.charAt(1) == 'y' || s.charAt(1) == 'Y';
        boolean b3 = s.charAt(2) == 'e' || s.charAt(2) == 'E';
        return (s.length() == 3) && b1 && b2 && b3;
    }

    public static void main(String[] args) {
        String hLine = "-".repeat(50);
        Scanner sc = new Scanner(System.in);
        System.out.println(hLine + "\nHello! I'm Duck" +
                            "\nWhat can I do for you?\n" + hLine);

        while (true) {
            String next = sc.nextLine();
            String s = next.stripLeading();
            if (s.length() < 3) { // Add item
                texts[textsLen++] = next;
                System.out.println(hLine + "\nadded: " + next + "\n" + hLine);
            } else if (s.equals("list")) {
                System.out.println(hLine);
                for (int i = 1; i <= textsLen; i++) {
                    System.out.println("\n" + i + ". " + texts[i - 1]);
                }
                System.out.println("\n" + hLine);
            } else if (isBye(s)) {
                System.out.println(hLine + "\n" +
                        "Bye. Hope to see you again soon!\n" +
                        hLine);
                break;
            } else {
                texts[textsLen++] = next;
                System.out.println(hLine + "\nadded: " + next + "\n" + hLine);
            }
        }
    }
}
