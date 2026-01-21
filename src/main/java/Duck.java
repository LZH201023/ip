import java.util.Scanner;

public class Duck {

    public static boolean isBye(String s) {
        boolean b1 = s.charAt(0) == 'b' || s.charAt(0) == 'B';
        boolean b2 = s.charAt(1) == 'y' || s.charAt(1) == 'Y';
        boolean b3 = s.charAt(2) == 'e' || s.charAt(2) == 'E';
        return b1 && b2 && b3;
    }
    public static void main(String[] args) {
        String hLine = "-".repeat(50);
        Scanner sc = new Scanner(System.in);
        System.out.println(hLine + "\nHello! I'm Duck" +
                            "\nWhat can I do for you?\n" + hLine + "\n");

        while (true) {
            String next = sc.nextLine();
            String s = next.stripLeading();
            if (s.length() < 3) {
                System.out.println(hLine + "\n" + next + "\n" + hLine);
            } else if (isBye(s)){
                System.out.println(hLine + "\n" +
                        "Bye. Hope to see you again soon!\n" +
                        hLine);
                break;
            } else {
                System.out.println(hLine + "\n" + next + "\n" + hLine);
            }
        }
//                            "\nBye. Hope to see you again soon!\n\n" + hLine);
    }
}
