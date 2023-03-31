/*
This class is used to validate inputs
 */
import java.util.Scanner;
import java.lang.Character;

public class InputValidation {
    private static Scanner sc;

    public static int getValidInt(String inp, int lower, int upper) {
        sc = new Scanner(System.in);
        int n = -1;
        boolean validInput = false;
        while (!validInput) {
            System.out.print(inp);
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n >= lower && n <= upper) {
                    validInput = true;
                } else {
                    System.out.println("The number is outside the range of " + lower + " - " + upper);
                }
            } else {
                System.out.println("Invalid input: not an integer");
                sc.next();
            }
        }
        return n;
    }
    public static String getValidString(String inp) {
        Scanner sc = new Scanner(System.in);
        String s = "";
        boolean validInput = false;
        while (!validInput) {
            System.out.print(inp);
                s = sc.nextLine();
                if (!s.isEmpty() && s.matches("^[a-zA-Z_]+$")) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a valid string");
                }
            }
        return s;
        }
    public static char getValidChar(String inp) {
        sc = new Scanner(System.in);
        char input = '1';
        do {
            System.out.print(inp);
            String str = sc.nextLine();
            if (str.length() == 1 && (str.charAt(0) == 'a' || Character.isLetter(str.charAt(0)))) {
                input = str.charAt(0);
            } else {
                System.out.println("Invalid character. Please enter only one valid character.");
                input = '1';
            }
        } while (input == '1');
        return input;
    }

    public static String getValidCoord(String inp){
        sc = new Scanner(System.in);

        String inputString = "";
        boolean validInput = false;
        while (!validInput) {
            System.out.print(inp);
            inputString = sc.nextLine();
            if (inputString.matches("\\d,\\d")) {
                validInput = true;
            } else {
                System.out.println("Invalid input: not in the correct format");
            }
        }
        return inputString;
    }


    }

