import java.util.Scanner;

public class Main {
    public static final int CREATE_USER = 1;
    public static final int LOGIN_USER = 2;
    public static final int ENTER_NEW_POST = 1;
    public static final int REMOVE_POST = 2;
    public static final int PRINT_ALL_PROPERTIES = 3;
    public static final int PROPERTIES_OF_USER = 4;
    public static final int SORT_BY_PARAMETER = 5;
    public static final int EXIT = 3;
    public static final int INNER_EXIT = 6;

    public static void main(String[] args) {
        RealEstate realEstate = new RealEstate();
        boolean exit = true;
        while (exit) {
            switch (startMenu()) {
                case CREATE_USER:
                    realEstate.createUser();
                    break;
                case LOGIN_USER:
                    User currentUser = realEstate.login();
                    if (currentUser == null) {
                        System.out.println("User name or password doesn't match");
                    } else {
                        int choice;
                        boolean innerExit=true;
                        do {
                            choice = userMenu(currentUser.getUserName());
                            switch (choice) {
                                case ENTER_NEW_POST:
                                    realEstate.postNewProperty(currentUser);
                                    break;
                                case REMOVE_POST:
                                    realEstate.removeProperty(currentUser);
                                    break;
                                case PRINT_ALL_PROPERTIES:
                                    if (realEstate.getProperties().length == 0) {
                                        System.out.println("There are no properties in the system yet");
                                    } else {
                                        realEstate.printAllProperties();
                                    }
                                    break;
                                case PROPERTIES_OF_USER:
                                    if (realEstate.hasProperties(currentUser)) {
                                        realEstate.printUserProperties(currentUser);
                                    } else {
                                        System.out.println("User has no properties in the system yet");
                                    }
                                    break;
                                case SORT_BY_PARAMETER:
                                    Property[] properties = realEstate.search();
                                    if (properties.length == 0) {
                                        System.out.println("theres no properties that match the sorted search");
                                    } else {
                                        System.out.println("Here is the properties after sort: " + "\n");
                                        for (int i = 0; i < properties.length; i++) {
                                            System.out.println(properties[i]);
                                        }
                                    }
                                    break;
                                case INNER_EXIT:
                                    innerExit=false;
                                default:
                                    break;
                            }
                        } while (innerExit);
                    }
                    break;

                case EXIT:
                    exit = false;
                    break;
                default:
                    break;

            }
        }
    }

    private static int startMenu() {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        do {
            System.out.println("\n" + "Welcome to our web real estate" + "\n" + "What do you want to do? " + "\n" + "1: Create account" + "\n" + "2: Enter exist account " + "\n" + "3: End program");
            System.out.println("Please enter your choice");
            userChoice = scanner.nextInt();
        } while (userChoice < 1 || userChoice > 3);
        return userChoice;
    }


    private static int userMenu(String userName) {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        do {
            System.out.println("Welcome! " + userName + "\n" + "What do you want to do? " + "\n" + "1) post a new property" + "\n" + "2) remove property " + "\n" + "3) display all properties in system " + "\n" + "4) display all of your properties   " + "\n" + "5) search property by parameters " + "\n" + "6) return to the start menu");
            System.out.println("Enter your choice");
            userChoice = scanner.nextInt();
        } while (userChoice < 1 || userChoice > 6);
        return userChoice;
    }

}