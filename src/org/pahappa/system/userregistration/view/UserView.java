package org.pahappa.system.userregistration.view;

import org.pahappa.system.userregistration.model.User;
import org.pahappa.system.userregistration.service.UserService;

import java.util.List;
import java.util.Scanner;

public class UserView {
    UserService userService = new UserService();
    Scanner scanner = new Scanner(System.in);

    public void displayStart(){
        System.out.println("**************** USER REGISTRATION SYSTEM WITH JDBC *****************");
        while (true) {
            System.out.println("\nUser Registration System");
            System.out.println("1. Create User");
            System.out.println("2. Read User");
            System.out.println("3. Read All Users");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Delete All Users");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    viewUser();
                    break;
                case 3:
                    displayAllUsers();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    deleteAUser();
                    break;
                case 6:
                    deleteAllUsers();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    public void addUser() {
        User user = new User();
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Please enter your date of birth: ");
        String dateOfBirth = scanner.nextLine();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);
        boolean userAdded=userService.addUser(user);
        if(userAdded) {
            System.out.println("User Registered successfully");
        }else {
            System.out.println("User not registered");
        }
    }
    public void viewUser() {
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        User user = userService.getUser(username);
        if(user != null) {
            System.out.println("username: " + user.getUsername());
            System.out.println("First name: " + user.getFirstName());
            System.out.println("Last name: " + user.getLastName());
            System.out.println("Date of birth: " + user.getDateOfBirth());
        }else {
            System.out.println("User not found");
        }
    }

    public void deleteAllUsers() {
        System.out.println("Are you sure you want to delete all users?\n1. Delete\n2.Cancel");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice == 1) {
            boolean allUsersDeleted = userService.deleteAllUsers();
            if (allUsersDeleted) {
                System.out.println("All users have been deleted from the system successfully");
            }else{
                System.out.println("Sorry, something went wrong. Users not deleted");
            }
        }else {
            System.out.println("Deletion terminated");
        }
    }

    public void displayAllUsers(){
        List<User> users=userService.getUsers();
        if (users != null) {
            for(User user:users){
                System.out.println("username: "+user.getUsername());
                System.out.println("Name: "+user.getFirstName()+" "+user.getLastName());
                System.out.println("Date of Birth: "+user.getDateOfBirth());
                System.out.println("**************************************************************\n");
            }
        }else {
            System.out.println("There are no currently registered users");
        }
    }

    public void deleteAUser(){
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Are you sure you want to delete the user?\n1. Delete\n2. Cancel");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            boolean userDeleted=userService.deleteUser(username);
            if (userDeleted) {
                System.out.println("User deleted successfully.");
            }else{
                System.out.println("User does not exist");
            }
        }else {
            System.out.println("Deletion aborted.");
        }
    }

    public void updateUser() {
        User usr = new User();
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Please enter your date of birth: ");
        String dateOfBirth = scanner.nextLine();
        usr.setUsername(username);
        usr.setFirstName(firstName);
        usr.setLastName(lastName);
        usr.setDateOfBirth(dateOfBirth);
        boolean userAdded=userService.updateUser(usr);
        if(userAdded) {
            System.out.println("User updated successfully");
        }else {
            System.out.println("User not updated");
        }
    }
}
