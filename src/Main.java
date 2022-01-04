import java.util.Scanner;
public class Main {
    public static RealEstate realEstate = new RealEstate();


    public static void main(String[] args) {
        firstMenu();
    }
    public static void firstMenu () {
        final int createAccount = 1;
        final int logIn = 2;
        final int endPlan = 3;
        Scanner scanner = new Scanner(System.in);
        int userChosie;
        String openTalk = "Hello dear user\n" +
                "What do you want to do:\n" +
                "1) Create an account\n" +
                "2) Log in to an existing user\n" +
                "3) Finish the program";
        do {
            System.out.println(openTalk);
            userChosie = scanner.nextInt();
        } while (!isBetween(userChosie, endPlan, createAccount));
        switch (userChosie) {
            case createAccount:
                realEstate.createUser();
                firstMenu();
                break;
            case logIn:
                User user= realEstate.login();
                realEstate.secondMenu(user);
                break;
            case endPlan:
                System.out.println("goodbye");
                break;
        }

    }
    public static boolean isBetween ( int userchoise, int highNumber, int lowNumber){
        boolean isNotBetween = true;
        if (userchoise > highNumber || userchoise < lowNumber) {
            isNotBetween = false;
        }
        return isNotBetween;
    }
}
