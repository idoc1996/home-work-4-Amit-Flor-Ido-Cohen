public class User {
    private String userName;
    private String password;
    private String phoneNumber;
    private boolean isBroker;
    private int amountPosted;

    public User(String userName, String password, String phoneNumber, boolean kindOfUser) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isBroker = kindOfUser;
        amountPosted = 0;
    }

    public boolean canPost() {
        boolean result = true;
        if (isBroker) {
            if (amountPosted >= 10) {
                result = false;
            }
        } else {
            if (amountPosted >= 3) {
                result = false;
            }
        }
        return result;
    }

    public boolean notHavePost() {
        boolean result = true;
        if (amountPosted == 0) {
            result = false;
        }
        return result;
    }


    public void incAmountPosted() {
        amountPosted++;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isBroker() {
        return isBroker;
    }

    public void setBroker(boolean broker) {
        this.isBroker = broker;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return userName + " " + phoneNumber + " " + (isBroker ? "(Real estate broker)" : "(Regular user)");
    }

    public void decPostAmount() {
        amountPosted--;
    }
}