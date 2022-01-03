import java.util.Scanner;

public class RealEstate {
    public static final int ADDRESS_OF_REAL_ESTATE = 10;
    public static final int CHARS_IN_PHONE = 10;


    private User[] users;
    private Property[] properties;
    private final Address[] addresses;

    public Property[] getProperties() {
        return properties;
    }

    public RealEstate() {
        this.users = new User[0];
        this.properties = new Property[0];
        this.addresses = new Address[ADDRESS_OF_REAL_ESTATE];
        initAddresses();

    }

    private boolean isForRent(int isRent) {
        boolean rent = false;
        if (isRent == 1) {
            rent = true;
        }
        return rent;
    }

    private void initAddresses() {
        addresses[0] = new Address("Ashdod", "Rav Ithzhk Yossef");
        addresses[1] = new Address("Ashkelon", "Raby Goldshtein");
        addresses[2] = new Address("Ashdod", "Rav Ashar wise");
        addresses[3] = new Address("Ashdod", "Rav papa");
        addresses[4] = new Address("Tel-Aviv", "Abaya");
        addresses[5] = new Address("Tel-Aviv", "Oola");
        addresses[6] = new Address("Tel-Aviv", "Rabi yochanan");
        addresses[7] = new Address("Ashkelon", "Rabi Ovadia");
        addresses[8] = new Address("Ashkelon", "Rabi Baba Sali");
        addresses[9] = new Address("Ashkelon", "Or Hachim");
    }

    public boolean postNewProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean canPost = true;
        if (!user.canPost()) {
            System.out.println("You reached your max of post amount ");
            canPost = false;
        } else {
            System.out.println("Please choose city from list: ");
            printAllCities();
            String chosenCity = scanner.nextLine();
            if (!isCityInAddresses(chosenCity)) {
                canPost = false;
                System.out.println("City not in list! ");
            } else {
                System.out.println("Please choose street from list :");
                printAllStreetsByCity(chosenCity);
                String chosenStreet = scanner.nextLine();
                if (!isStreetInList(chosenCity, chosenStreet)) {
                    canPost = false;
                    System.out.println("Street not in addresses cities. ");
                } else {
                    Address address = new Address(chosenCity, chosenCity);
                    int kindOfProperty = getKindOfProperty();
                    int floor = 0;
                    if (kindOfProperty == 1) {
                        System.out.println("On what floor is the property");
                        floor = scanner.nextInt();
                    }
                    System.out.println("How many rooms? ");
                    int rooms = scanner.nextInt();
                    System.out.println("Number of property?");
                    int numberOfProperty = scanner.nextInt();
                    System.out.println("Is the property for rent or for sale?" + "\n" + "For rent enter 1" + "\n" + "For sale enter another number");
                    int toRent = scanner.nextInt();
                    boolean rent = isForRent(toRent);
                    System.out.println("What is the price of the property?");
                    int price = scanner.nextInt();
                    user.incAmountPosted();
                    System.out.println("The property has been added");
                    addPropertyToArray(rooms, price, kindOfProperty, rent, numberOfProperty, floor, user, address);

                }
            }
        }
        return canPost;
    }

    private int getKindOfProperty() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("What kind of property?" + "\n" + "For property in High-rise building enter 1" + "\n" + "For penthouse enter 2 " + "\n" + "For private house enter 3");
            choice = scanner.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.println("Invalid choice, please enter valid choice from the menu");
            }
        } while (choice < 1 || choice > 3);
        return choice;
    }

    public void removeProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        if (!user.notHavePost()) {
            System.out.println("You not have any post!");
        } else {
            int[] propertiesOfUser = new int[properties.length];
            int j = 0;
            for (int i = 0; i < properties.length; i++) {
                if ((properties[i].getUser().getUserName()).equals(user.getUserName())) {
                    System.out.println("");

                    System.out.println(j + 1 + ": ");
                    System.out.println(properties[i]);
                    propertiesOfUser[j] = i;
                    j++;
                }
            }
            int choice;
            do {
                System.out.println("enter a property you wish to delete");
                choice = scanner.nextInt();
            }
            while (choice > j);

            deleteProperty(propertiesOfUser[choice - 1]);
            user.decPostAmount();
            System.out.println("Your property has been removed");
        }
    }

    private void deleteProperty(int choice) {

        Property[] temp = new Property[properties.length - 1];
        int j = 0;
        for (int i = 0; i < properties.length; i++) {
            if (i != choice) {
                temp[j] = properties[i];
                j++;
            }
        }
        properties = temp;
    }

    public void printAllProperties() {
        for (int i = 0; i < properties.length; i++) {
            System.out.println(properties[i]);
        }
    }

    public void printUserProperties(User user) {
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].getUser().getUserName().equals(user.getUserName()))
                System.out.println(properties[i]);
        }
    }

    private boolean isStreetInList(String chosenCity, String chosenStreet) {
        boolean inList = false;
        for (int i = 0; i < addresses.length; i++) {
            if (chosenStreet.equals(addresses[i].getStreet())) {
                inList = true;
                break;
            }
        }
        return inList;
    }

    private void printAllStreetsByCity(String chosenCity) {
        for (int i = 0; i < addresses.length; i++) {
            if ((addresses[i].getTown().equals(chosenCity))) {
                System.out.println(addresses[i].getStreet());
            }
        }
    }

    private boolean isCityInAddresses(String chosenCity) {
        boolean inList = false;
        for (int i = 0; i < addresses.length; i++) {
            if (chosenCity.equals(addresses[i].getTown())) {
                inList = true;
            }
        }
        return inList;
    }

    private void printAllCities() {
        boolean print = true;
        for (int i = 0; i < addresses.length; i++) {
            print = true;
            for (int j = 0; j < i; j++) {
                if (addresses[i].getTown()
                        .equals(addresses[j].getTown())) {
                    print = false;
                    break;
                }
            }
            if (print) {
                System.out.println(addresses[i].getTown());
            }
        }
    }

    public void createUser() {
        Scanner scanner = new Scanner(System.in);

        String username, password;
        do {
            System.out.println("Enter username: ");
            username = scanner.nextLine();
            if (isUsernameExist(username)) {
                System.out.println("Username already exists,please try a different username");
            }
        } while (isUsernameExist(username));
        do {
            System.out.println("Enter password:" + "\n" + "The password must have at least one digit and one of the next chars: : $, % or _ .");
            password = scanner.nextLine();
            if (!isStrongPassword(password)) {
                System.out.println("The password isn't strong enough. ");
            }
        } while (!isStrongPassword(password));
        String phoneNumber;
        do {
            System.out.println("Enter phone number: ");
            phoneNumber = scanner.nextLine();
            if (!validPhoneNumber(phoneNumber)) {
                System.out.println("Phone number is invalid, the number must start at 05 and be 10 digit long");
            }
        } while (!validPhoneNumber(phoneNumber));
        System.out.println("If you a broker enter 1,else enter another number");
        int kindOfUser = scanner.nextInt();
        boolean isBroker = isBroker(kindOfUser);
        addUserToArray(username, password, phoneNumber, isBroker);
    }

    private boolean isBroker(int broker) {
        boolean isBroker = false;
        if (broker == 1) {
            isBroker = true;
        }
        return isBroker;
    }

    private boolean isUsernameExist(String usernameToCheck) {
        boolean exists = false;
        for (int i = 0; i < this.users.length; i++) {
            if (users[i].getUserName().equals(usernameToCheck)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private boolean isStrongPassword(String passwordToCheck) {
        boolean strong = false;
        boolean hasSymbol = false;
        boolean hasDigit = false;

        for (int i = 0; i < passwordToCheck.length(); i++) {
            char currentChar = passwordToCheck.charAt(i);
            if (Character.isDigit(currentChar)) {
                hasDigit = true;
            } else if (currentChar == '$' || currentChar == '%' || currentChar == '_') {
                hasSymbol = true;
            }
            if (hasDigit && hasSymbol) {
                break;
            }
        }

        if (hasSymbol && hasDigit) {
            strong = true;
        }
        return strong;
    }


    private boolean validPhoneNumber(String phoneNumber) {

        boolean validNumber = false;
        if (phoneNumber.length() == CHARS_IN_PHONE) {
            if (phoneNumber.charAt(0) == '0' && phoneNumber.charAt(1) == '5') {
                validNumber = true;
            }
        }
        return validNumber;
    }


    private void addUserToArray(String username, String password, String phoneNumber, boolean isBroker) {
        User[] newArray = new User[users.length + 1];
        for (int i = 0; i < users.length; i++) {
            newArray[i] = users[i];
        }
        User userToAdd = new User(username, password, phoneNumber, isBroker);
        newArray[users.length] = userToAdd;
        users = newArray;
    }

    private void addPropertyToArray(int rooms, int price, int whatKind, boolean rent, int houseNumber, int floor, User user, Address address) {
        Property[] newArray = new Property[this.properties.length + 1];
        for (int i = 0; i < properties.length; i++) {
            newArray[i] = properties[i];
        }
        Property propertyToAdd = new Property(rooms, price, whatKind, rent, houseNumber, floor, user, address);
        newArray[properties.length] = propertyToAdd;
        properties = newArray;
    }

    public User login() {
        Scanner scanner = new Scanner(System.in);
        String username, password;
        User user = null;
        System.out.println("Enter username: ");
        username = scanner.nextLine();
        System.out.println("Enter password: ");
        password = scanner.nextLine();
        int userIndex = isUsernameExistInArr(username);
        if (userIndex != -1) {
            if (doesPasswordMatch(userIndex, password)) {
                user = users[userIndex];
            }
        }
        return user;
    }

    private int isUsernameExistInArr(String usernameToCheck) {
        int userIndex = -1;
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getUserName().equals(usernameToCheck)) {
                userIndex = i;
                break;
            }
        }
        return userIndex;
    }

    private boolean doesPasswordMatch(int userIndex, String password) {
        boolean result = false;

        if (users[userIndex].getPassword().equals(password)) {
            result = true;
        }
        return result;
    }

    private void printIgnoreCode() {
        System.out.println("If you want to ignore this section click -999 ");
    }

    private boolean areWantToIgnore(int numToCheck) {
        boolean result = false;
        if (numToCheck == -999) {
            result = true;
        }
        return result;
    }

    public Property[] search() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("To rent or sale? for rent enter 1,for sale enter any key ");
        printIgnoreCode();
        boolean rent = isForRent(scanner.nextInt());
        System.out.println("What kind of property" + "\n" + "or property in High-rise building enter 1" + "\n" + "for penthouse enter 2 " + "\n" + "for private house enter 3");
        printIgnoreCode();
        int kindOfProperty = scanner.nextInt();
        System.out.println("How many rooms?");
        printIgnoreCode();
        int rooms = scanner.nextInt();
        System.out.println("What the minimum price?");
        printIgnoreCode();
        int minPrice = scanner.nextInt();
        System.out.println("What the max of price?");
        printIgnoreCode();
        int maxPrice = scanner.nextInt();
        Property[] tempSortPropertiesArray = new Property[properties.length];
        int j = 0;
        for (int i = 0; i < properties.length; i++) {
            if ((properties[i].isRent() == rent || areWantToIgnore(-999))
                    && (properties[i].getWhatKind() == kindOfProperty || kindOfProperty == -999)
                    && (properties[i].getRooms() == rooms || rooms == -999)
                    && (properties[i].getPrice() >= minPrice || minPrice == -999)
                    && (properties[i].getPrice() <= maxPrice || maxPrice == -999)) {
                tempSortPropertiesArray[j] = properties[i];
                j++;
            }
        }
        return shortenArray(j, tempSortPropertiesArray);
    }

    private Property[] shortenArray(int newSize, Property[] oldArr) {
        Property[] newArr = new Property[newSize];
        for (int i = 0; i < newSize; i++) {
            newArr[i] = oldArr[i];
        }
        return newArr;
    }

    public boolean hasProperties(User currentUser) {
        boolean result = false;
        for (int i = 0; i < properties.length; i++) {
            if ((properties[i].getUser().getUserName()).equals(currentUser.getUserName())) {
                result = true;
                break;
            }
        }
        return result;
    }
}