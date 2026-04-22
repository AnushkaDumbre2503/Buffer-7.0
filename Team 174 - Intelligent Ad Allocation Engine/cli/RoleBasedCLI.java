package cli;

import auth.AuthContext;
import java.util.Scanner;

public class RoleBasedCLI {
    private AuthContext authContext;
    private Scanner scanner;
    
    public RoleBasedCLI() {
        this.scanner = new Scanner(System.in);
        this.authContext = new AuthContext();
    }
    
    public void start() {
        System.out.println("\n========================================");
        System.out.println("   Intelligent Ad Allocation Engine");
        System.out.println("========================================\n");
        
        while (true) {
            if (!authContext.isAuthenticated()) {
                showAuthenticationMenu();
            } else {
                showRoleBasedMenu();
            }
        }
    }
    private void showUserAuthMenu() {
        System.out.println("\n--- User Authentication ---");
        System.out.println("\n1. User Login");
        System.out.println("2. User Signup");
        System.out.println("3. Exit");
        String choice = scanner.nextLine().trim();
        try {
            switch (choice) {
            case "1":
                userLogin();
                break;
            case "2":
                userSignup();
                break;
            case "3":
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option");
        }
        } catch (Exception e) {
        }
    }
    private void showAdvertiserAuthMenu() {
        System.out.println("\n--- Advertiser Authentication ---");
        System.out.println("\n1. Advertiser Login");
        System.out.println("2. Advertiser Signup");
        System.out.println("3. Exit");
        String choice = scanner.nextLine().trim();
        try {
            switch (choice) {
            case "1":
                advertiserLogin();
                break;
            case "2":
                advertiserSignup();
                break;
            case "3":
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option");
        }
        } catch (Exception e) {
        }
    }
    private void showAdminAuthMenu() {
        System.out.println("\n--- Admin Authentication ---");
        System.out.println("\n1. Admin Login");
        System.out.println("2. Exit");
        String choice = scanner.nextLine().trim();
        try {
            switch (choice) {
            case "1":
                adminLogin();
                break;
            case "2":
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option");
        }
        } catch (Exception e) {
        }
    }
    private void showAuthenticationMenu() {
        System.out.println("1. User Mode");
        System.out.println("2. Advertiser Mode");
        System.out.println("3. Admin Mode");
        System.out.println("4. Exit");
        System.out.print("Choose: ");
        String upperChoice = scanner.nextLine().trim();
        switch (upperChoice) {
            case "1":
                showUserAuthMenu();
                break;
            case "2":
                showAdvertiserAuthMenu();
                break;
            case "3":
                showAdminAuthMenu();
                break;
            default:
                System.out.println("Invalid option");
        }
        
        System.out.print("\nChoose option: ");
    
    }
    
    private void showRoleBasedMenu() {
        switch (authContext.getRole()) {
            case USER:
                new UserCLI(authContext, scanner).showUserMenu();
                break;
            case ADVERTISER:
                new AdvertiserCLI(authContext, scanner).showAdvertiserMenu();
                break;
            case ADMIN:
                new AdminCLI(authContext, scanner).showAdminMenu();
                break;
        }
    }
    
    private void userLogin() throws Exception {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        
        auth.UserAuthService userAuth = new auth.UserAuthService();
        AuthContext context = userAuth.login(username, password);
        
        if (context != null) {
            this.authContext = context;
            System.out.println("Login successful. Welcome, " + username + "!");
        } else {
            System.out.println("Invalid credentials");
        }
    }
    
    private void userSignup() throws Exception {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        
        auth.UserAuthService userAuth = new auth.UserAuthService();
        if (userAuth.signup(username, email, password)) {
            System.out.println("Signup successful. You can now login.");
        } else {
            System.out.println("Signup failed. Username or email may already exist.");
        }
    }
    
    private void advertiserLogin() throws Exception {
        System.out.print("Company Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        
        auth.AdvertiserAuthService advertiserAuth = new auth.AdvertiserAuthService();
        AuthContext context = advertiserAuth.login(name, password);
        
        if (context != null) {
            this.authContext = context;
            System.out.println("Login successful. Welcome, " + name + "!");
        } else {
            System.out.println("Invalid credentials");
        }
    }
    
    private void advertiserSignup() throws Exception {
        System.out.print("Company Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        System.out.print("Initial Budget: ");
        double budget = Double.parseDouble(scanner.nextLine().trim());
        
        auth.AdvertiserAuthService advertiserAuth = new auth.AdvertiserAuthService();
        if (advertiserAuth.signup(name, email, password, budget)) {
            System.out.println("Signup successful. You can now login.");
        } else {
            System.out.println("Signup failed. Company name or email may already exist.");
        }
    }
    
    private void adminLogin() throws Exception {
        System.out.print("Admin Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        
        auth.AdminAuthService adminAuth = new auth.AdminAuthService();
        AuthContext context = adminAuth.login(username, password);
        
        if (context != null) {
            this.authContext = context;
            System.out.println("Admin login successful. Welcome, " + username + "!");
        } else {
            System.out.println("Invalid admin credentials");
        }
    }
}
