public class Property {
    private Address address;
    private int rooms;
    private int price;
    private int whatKind;
    private boolean rent;
    private int houseNumber;
    private int floor;
    private User user;

    public Property(int rooms, int price, int whatKind, boolean rent, int houseNumber, int floor, User user, Address address) {
        this.rooms = rooms;
        this.price = price;
        this.whatKind = whatKind;
        this.rent = rent;
        this.houseNumber = houseNumber;
        this.floor = floor;
        this.user = user;
        this.address = address;
    }

    private String whatKindToString(int whatKind) {
        String kindOfProperty;
        if (whatKind == 1) {
            kindOfProperty = "Property in High-rise building";
        } else if (whatKind == 2) {
            kindOfProperty = "Penthouse";

        } else {
            kindOfProperty = "Private house";
        }
        return kindOfProperty;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {

        return address;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWhatKind() {
        return whatKind;
    }

    public void setWhatKind(int whatKind) {
        this.whatKind = whatKind;
    }

    public boolean isRent() {
        return rent;
    }

    @Override
    public String toString() {
        return whatKindToString(whatKind) + " - " + (rent ? "to rent" : "for sale") + ":" + rooms + " rooms" + (floor == 0 ? "." : ", floor " + floor + ".") + "\n" + "Price: " + price + "$" + "\n" + "Contact info: " + user +"\n";
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}