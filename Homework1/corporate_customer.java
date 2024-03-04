package Homework1;

public class corporate_customer extends customer {

    public corporate_customer(String name, String surname, String address, String phone, int ID, int operator_ID, String company_name) {
        super(name, surname, address, phone, ID, operator_ID);
        this.company_name = company_name;
    }
    @Override
    public void print_customer() {
        super.print_customer();
        System.out.println("Company name: " + company_name);

    }

    private String company_name;

}
