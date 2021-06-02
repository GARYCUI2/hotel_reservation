import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
    private static Scanner scanner = new Scanner(System.in);

    public static char readMenuSelection(boolean isAdmin) {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1);
            c = str.charAt(0);
            if (!isAdmin && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' ) {
                System.out.print("Invalid menu selection, please select again!!!\n");
            } else if(isAdmin && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6'){
                System.out.print("Invalid menu selection, please select again!!!\n");
            }else break;
        }
        return c;
    }

    public static String readKeyBoard(int limit) {
        String line = "";
        for(;;){ ;
            line = readKeyBoard();
            if(line.length() > limit){
                System.out.print("Input length should be less than " + limit + "，please input again：\n");
                continue;
            }
            break;
        }
        return line;
    }

    //read and return input until it's not empty
    public static String readKeyBoard() {
        String line = "";
        for(;;){
            line = scanner.nextLine();
            if (line.length() < 1) {
                System.out.print("Nothing input，please input again：\n");
                continue;
            }
            break;
        }
        return line;
    }

    public static String readKeyBoard(Pattern pattern) {
        String line = "";
        for(;;){
            line = readKeyBoard();
            Matcher matcher = pattern.matcher(line);
            if(!matcher.matches()){
                System.out.print("Wrong input format，please input again：\n");
                continue;
            }
            break;
        }
        return line;
    }

    public static char readConfirmSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("(y/n), please input again：\n");
            }
        }
        return c;
    }
}
