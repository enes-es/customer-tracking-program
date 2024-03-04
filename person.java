package Homework1;


public class person {
    public person(String name, String surname, String address, String phone, int ID) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.ID = ID;
    }

    // TODO check if protected is valid for these variables
    protected String name;
    protected String surname;
    protected String address;
    protected String phone;
    protected int ID;
}
