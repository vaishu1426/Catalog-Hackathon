import java.util.Scanner;

public class ThreeLevelPasswordSystem {
    private static final int MAX_ATTEMPTS = 3;
    private static final String USERNAME = "admin";//The user must enter a valid username to proceed.
    private static final String PASSWORD = "password123";//The user must enter a valid password to proceed.
    private static final String GRAPHICAL_PASSWORD = "image123";//a string that the user must enter to complete the 
    //third level of authentication

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            if (!username.equals(USERNAME)) {
                System.out.println("Invalid username. Try again.");
                attempts++;
                continue;
            }

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (!password.equals(PASSWORD)) {
                System.out.println("Invalid password. Try again.");
                attempts++;
                continue;
            }

            System.out.print("Enter graphical password: ");
            String graphicalPassword = scanner.nextLine();

            if (!graphicalPassword.equals(GRAPHICAL_PASSWORD)) {
                System.out.println("Invalid graphical password. Try again.");
                attempts++;
                continue;
            }

            System.out.println("Login successful!");
            break;
        }

        if (attempts == MAX_ATTEMPTS) {
            System.out.println("Maximum attempts reached. Account locked.");
        }
    }
}